package com.example.notesapp.repository;

import android.support.annotation.NonNull;

import com.example.notesapp.model.Note;
import com.example.notesapp.network.API;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class InMemoryNotesRepository implements NotesRepository {

    List<Note> mCachedNotes;

    public InMemoryNotesRepository() { }

    @Override
    public void getNotes(@NonNull LoadNotesCallback callback) {
        checkNotNull(callback);
        if (mCachedNotes == null) {
            API.getInstance().getService().getNotes(new NotesResponse(callback));
        } else {
            callback.onNotesLoaded(mCachedNotes);
        }
    }

    @Override
    public void saveNote(int position, @NonNull Note note, @NonNull ActionNotesCallback callback) {
        checkNotNull(note);
        checkNotNull(callback);
        ArrayList<Note> notes = new ArrayList<>(mCachedNotes);
        notes.set(position, note);
        API.getInstance().getService().saveNotes(notes, new ActionResponse(callback, position, note));
    }

    @Override
    public void addNote(@NonNull Note note, @NonNull ActionNotesCallback callback) {
        checkNotNull(note);
        checkNotNull(callback);
        ArrayList<Note> notes = new ArrayList<>(mCachedNotes);
        notes.add(note);
        API.getInstance().getService().saveNotes(notes, new ActionResponse(callback, note));
    }

    @Override
    public void deleteNote(int position, @NonNull ActionNotesCallback callback) {
        checkNotNull(callback);
        ArrayList<Note> notes = new ArrayList<>(mCachedNotes);
        notes.remove(position);
        API.getInstance().getService().saveNotes(notes, new ActionResponse(callback, position));
    }

    private class NotesResponse implements Callback<ArrayList<Note>> {

        LoadNotesCallback callback;

        NotesResponse(LoadNotesCallback callback) {
            this.callback = callback;
        }

        @Override
        public void success(ArrayList<Note> notes, Response response) {
            mCachedNotes = ImmutableList.copyOf(notes);
            callback.onNotesLoaded(mCachedNotes);
        }

        @Override
        public void failure(RetrofitError error) {
            callback.onFailed(error.getMessage());
        }
    }

    private class ActionResponse implements Callback<Object> {

        private ActionNotesCallback callback;
        int position = -1;
        Note note;

        ActionResponse(ActionNotesCallback callback, int position, Note note) {
            this.callback = callback;
            this.position = position;
            this.note = note;
        }

        ActionResponse(ActionNotesCallback callback, int position) {
            this.callback = callback;
            this.position = position;
        }

        ActionResponse(ActionNotesCallback callback, Note note) {
            this.callback = callback;
            this.note = note;
        }

        @Override
        public void success(Object o, Response response) {
            if (note == null)
                mCachedNotes.remove(position);
            else if (position >= 0)
                mCachedNotes.set(position, note);
            else mCachedNotes.add(note);
            callback.onSuccess();
        }

        @Override
        public void failure(RetrofitError error) {
            callback.onFailed(error.getMessage());
        }
    }

}

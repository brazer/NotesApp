package com.example.notesapp.repository;

import android.support.annotation.NonNull;

import com.example.notesapp.model.Note;

import java.util.List;

public interface NotesRepository {

    interface LoadNotesCallback {
        void onNotesLoaded(List<Note> notes);
        void onFailed(String message);
    }

    interface ActionNotesCallback {
        void onSuccess();
        void onFailed(String message);
    }

    void getNotes(@NonNull LoadNotesCallback callback);
    void saveNote(int position, @NonNull Note note, @NonNull ActionNotesCallback callback);
    void addNote(@NonNull Note note, @NonNull ActionNotesCallback callback);
    void deleteNote(int position, @NonNull ActionNotesCallback callback);
    boolean isLimitOfNotesReached();

}

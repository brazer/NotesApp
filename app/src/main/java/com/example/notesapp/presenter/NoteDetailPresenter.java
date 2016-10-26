package com.example.notesapp.presenter;

import android.support.annotation.NonNull;

import com.example.notesapp.contract.NoteDetailContract;
import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NotesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Admin on 26.10.2016.
 */

public class NoteDetailPresenter implements NoteDetailContract.UserActionsListener {

    @NonNull
    private NotesRepository mNotesRepository;
    @NonNull
    private NoteDetailContract.View mNoteDetailView;

    public NoteDetailPresenter(@NonNull NotesRepository notesRepository,
                               @NonNull NoteDetailContract.View noteDetailView) {
        mNotesRepository = checkNotNull(notesRepository);
        mNoteDetailView = checkNotNull(noteDetailView);
    }

    @Override
    public void deleteNote(int position) {
        mNotesRepository.deleteNote(position, new NotesRepository.ActionNotesCallback() {
            @Override
            public void onSuccess() {
                mNoteDetailView.showNotesList();
            }

            @Override
            public void onFailed(String message) {
                mNoteDetailView.onFailed(message);
            }
        });
    }

    @Override
    public void saveNote(int position, Note note) {
        mNotesRepository.saveNote(position, note, new NotesRepository.ActionNotesCallback() {
            @Override
            public void onSuccess() {
                mNoteDetailView.showNotesList();
            }

            @Override
            public void onFailed(String message) {
                mNoteDetailView.onFailed(message);
            }
        });
    }

}

package com.example.notesapp.presenter;

import android.support.annotation.NonNull;

import com.example.notesapp.contract.AddNoteContract;
import com.example.notesapp.contract.NotesContract;
import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NotesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddNotePresenter implements AddNoteContract.UserActionsListener {

    @NonNull
    private final NotesRepository mNotesRepository;
    @NonNull
    private final AddNoteContract.View mAddNoteView;

    public AddNotePresenter(@NonNull NotesRepository notesRepository,
                            @NonNull AddNoteContract.View addNoteView) {
        mNotesRepository = checkNotNull(notesRepository);
        mAddNoteView = checkNotNull(addNoteView);
    }

    @Override
    public void saveNote(String title, String description) {
        Note note = new Note(title, description);
        mNotesRepository.addNote(note, new NotesRepository.ActionNotesCallback() {
            @Override
            public void onSuccess() {
                mAddNoteView.showNotes();
            }

            @Override
            public void onFailed(String message) {
                mAddNoteView.onFailed(message);
            }
        });
    }
}

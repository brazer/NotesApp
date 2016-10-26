package com.example.notesapp.presenter;

import android.support.annotation.NonNull;

import com.example.notesapp.contract.NotesContract;
import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NotesRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class NotesPresenter implements NotesContract.UserActionsListener {

    private final NotesContract.View mNotesView;
    private final NotesRepository mNotesRepository;

    public NotesPresenter(NotesContract.View notesView, NotesRepository notesRepository) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null");
        mNotesView = checkNotNull(notesView, "notesView cannot be null!");
    }

    @Override
    public void loadNotes() {
        mNotesView.setProgressIndicator(true);
        mNotesRepository.getNotes(new NotesRepository.LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                mNotesView.setProgressIndicator(false);
                mNotesView.showNotes(notes);
            }

            @Override
            public void onFailed(String message) {
                mNotesView.setProgressIndicator(false);
                mNotesView.onFailed(message);
            }
        });
    }

    @Override
    public void addNewNote() {
        mNotesView.showAddNote();
    }

    @Override
    public void openNoteDetails(int position) {
        mNotesView.showNoteDetailUi(position);
    }

    @Override
    public boolean isLimitOfNotesReached() {
        return mNotesRepository.isLimitOfNotesReached();
    }

}

package com.example.notesapp.contract;

import android.support.annotation.NonNull;

import com.example.notesapp.model.Note;

import java.util.List;

public interface NotesContract {

    interface View {
        void setProgressIndicator(boolean active);
        void showNotes(List<Note> notes);
        void showAddNote();
        void showNoteDetailUi(int position);
        void onFailed(String message);
    }

    interface UserActionsListener {
        void loadNotes();
        void addNewNote();
        void openNoteDetails(int position);
    }

}

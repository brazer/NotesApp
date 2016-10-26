package com.example.notesapp.contract;

public interface AddNoteContract {

    interface View {
        void showEmptyNoteError(String message);
        void showNotesList();
    }

    interface UserActionsListener {
        void saveNote(String title, String description);
    }

}

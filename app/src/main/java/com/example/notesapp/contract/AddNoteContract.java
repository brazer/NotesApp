package com.example.notesapp.contract;

public interface AddNoteContract {

    interface View {
        void showNotes();
        void onFailed(String message);
    }

    interface UserActionsListener {
        void saveNote(String title, String description);
    }

}

package com.example.notesapp.contract;

import com.example.notesapp.model.Note;

import java.util.List;

/**
 * Created by Admin on 26.10.2016.
 */

public interface NoteDetailContract {

    interface View {
        void showNotesList();
        void onFailed(String message);
    }

    interface UserActionsListener {
        void deleteNote(int position);
        void saveNote(int position, Note note);
    }

}

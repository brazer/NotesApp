package com.example.notesapp.repository;

import android.support.annotation.NonNull;

import com.example.notesapp.network.NotesServiceApi;

import static com.google.common.base.Preconditions.checkNotNull;

public class NoteRepositories {

    private NoteRepositories() {
        // no instance
    }

    private static NotesRepository repository = null;

    public synchronized static NotesRepository getInMemoryRepoInstance() {
        if (null == repository) {
            repository = new InMemoryNotesRepository();
        }
        return repository;
    }

}

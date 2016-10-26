package com.example.notesapp.network;

import com.example.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.PUT;

public interface NotesServiceApi {

    @GET("/")
    void getNotes(Callback<ArrayList<Note>> callback);

    @PUT("/")
    void saveNotes(List<Note> notes, Callback<Object> callback);

}

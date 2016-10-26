package com.example.notesapp;

import android.app.Application;

import com.example.notesapp.network.API;

/**
 * Created by Admin on 25.10.2016.
 */

public class NotesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        API.initialize();
    }
}

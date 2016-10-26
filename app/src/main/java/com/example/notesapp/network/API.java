package com.example.notesapp.network;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class API {

    private static API sInstance;
    private NotesServiceApi mService;

    private API() {
        Gson gson = new GsonBuilder().create();
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.BASE_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setConverter(new GsonConverter(gson))
                .build();
        mService = adapter.create(NotesServiceApi.class);
    }

    public static void initialize() {
        sInstance = new API();
    }

    public static API getInstance() {
        return sInstance;
    }

    public NotesServiceApi getService() {
        return mService;
    }

}

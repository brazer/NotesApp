package com.example.notesapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.notesapp.R;
import com.example.notesapp.ui.FragmentUtils;
import com.example.notesapp.ui.fragment.AddNoteFragment;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentUtils.replaceContent(
                this, R.id.contentFrame, AddNoteFragment.newInstance(), AddNoteFragment.TAG);
    }
}

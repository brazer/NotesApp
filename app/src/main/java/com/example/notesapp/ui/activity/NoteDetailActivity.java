package com.example.notesapp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.notesapp.R;
import com.example.notesapp.model.Note;
import com.example.notesapp.ui.FragmentUtils;
import com.example.notesapp.ui.fragment.NoteDetailFragment;

public class NoteDetailActivity extends AppCompatActivity {

    public final static String EXTRA_NOTE_ID = "note_id";
    public final static String EXTRA_NOTE = "note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int noteId = getIntent().getIntExtra(EXTRA_NOTE_ID, -1);
        Note note = (Note) getIntent().getSerializableExtra(EXTRA_NOTE);

        Bundle args = new Bundle();
        args.putInt(NoteDetailFragment.ARG_POSITION, noteId);
        args.putSerializable(NoteDetailFragment.ARG_NOTE, note);
        Fragment frag = FragmentUtils.instantiateFragment(this, NoteDetailFragment.class, args);
        FragmentUtils.replaceContent(this, R.id.contentFrame, frag, NoteDetailFragment.TAG);
    }
}

package com.example.notesapp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notesapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteDetailFragment extends Fragment {

    public static final String ARG_POSITION = "position";
    public static final String TAG = NoteDetailFragment.class.getSimpleName();

    private int position;

    public NoteDetailFragment() {
        // Required empty public constructor
    }

    public static NoteDetailFragment newInstance() {
        return new NoteDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

}

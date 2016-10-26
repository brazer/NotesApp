package com.example.notesapp.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.contract.NotesContract;
import com.example.notesapp.model.Note;
import com.example.notesapp.presenter.NotesPresenter;
import com.example.notesapp.repository.NoteRepositories;
import com.example.notesapp.ui.activity.AddNoteActivity;
import com.example.notesapp.ui.activity.NoteDetailActivity;
import com.example.notesapp.ui.adapter.NotesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements NotesContract.View {

    public final static String TAG = MainFragment.class.getSimpleName();

    private NotesContract.UserActionsListener mActionsListener;

    private NotesAdapter mListAdapter;

    NotesAdapter.NoteItemListener mItemListener = new NotesAdapter.NoteItemListener() {
        @Override
        public void onNoteClick(int position) {
            mActionsListener.openNoteDetails(position);
        }
    };

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new NotesAdapter(new ArrayList<Note>(0), mItemListener);
        mActionsListener = new NotesPresenter(this, NoteRepositories.getInMemoryRepoInstance());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        mActionsListener.loadNotes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.notes_list);
        recyclerView.setAdapter(mListAdapter);

        int numColumns = getContext().getResources().getInteger(R.integer.num_notes_columns);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        // Set up floating action button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab);

        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionsListener.addNewNote();
            }
        });

        // Pull-to-refresh
        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.loadNotes();
            }
        });
        return root;
    }

    @Override
    public void setProgressIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showNotes(List<Note> notes) {
        mListAdapter.replaceData(notes);
        if (notes.size() == 0)
            Snackbar.make(getView(), "Add a note", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showAddNote() {
        if (mActionsListener.isLimitOfNotesReached())
            Snackbar.make(getView(), "Limit of amount of notes is reached.", Snackbar.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(getContext(), AddNoteActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showNoteDetailUi(int noteId) {
        Intent intent = new Intent(getContext(), NoteDetailActivity.class);
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, noteId);
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, mListAdapter.getItem(noteId));
        startActivity(intent);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

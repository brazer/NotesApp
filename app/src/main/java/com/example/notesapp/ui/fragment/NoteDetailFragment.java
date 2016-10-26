package com.example.notesapp.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.contract.NoteDetailContract;
import com.example.notesapp.model.Note;
import com.example.notesapp.presenter.NoteDetailPresenter;
import com.example.notesapp.repository.NoteRepositories;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteDetailFragment extends BaseFragment implements NoteDetailContract.View {

    public static final String ARG_POSITION = "mPosition";
    public static final String ARG_NOTE = "note";
    public static final String TAG = NoteDetailFragment.class.getSimpleName();

    private int mPosition;
    private Note mNote;

    private EditText mTitleEt;
    private EditText mDescEt;
    private Button mSaveBtn;
    private Button mDeleteBtn;

    private ProgressDialog mProgressDialog;
    private NoteDetailContract.UserActionsListener mListener;

    public NoteDetailFragment() {
        // Required empty public constructor
    }

    public static NoteDetailFragment newInstance() {
        return new NoteDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mPosition = args.getInt(ARG_POSITION);
            mNote = (Note) args.getSerializable(ARG_NOTE);
        } else throw new IllegalArgumentException("Empty args");
        mListener = new NoteDetailPresenter(NoteRepositories.getInMemoryRepoInstance(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note_detail, container, false);
        mTitleEt = (EditText) root.findViewById(R.id.title_edit_text);
        mTitleEt.addTextChangedListener(mWatcherForTitle);
        mTitleEt.setText(mNote.getTitle());
        mDescEt = (EditText) root.findViewById(R.id.desc_edit_text);
        mDescEt.addTextChangedListener(mWatcherForDesc);
        mDescEt.setText(mNote.getDescription());
        mDeleteBtn = (Button) root.findViewById(R.id.delete_button);
        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.deleteNote(mPosition);
                mProgressDialog = ProgressDialog.show(getActivity(), "", "Loading...");
            }
        });
        mSaveBtn = (Button) root.findViewById(R.id.save_button);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mTitleEt.getText().toString())) {
                    Toast.makeText(getActivity(), "Title must not be empty", Toast.LENGTH_LONG).show();
                } else {
                    Note note = new Note(mTitleEt.getText().toString(), mDescEt.getText().toString());
                    mListener.saveNote(mPosition, note);
                    mProgressDialog = ProgressDialog.show(getActivity(), "", "Loading...");
                }
            }
        });
        return root;
    }

    @Override
    public void showNotesList() {
        if (mProgressDialog != null) mProgressDialog.dismiss();
        getActivity().finish();
    }

    @Override
    public void onFailed(String message) {
        if (mProgressDialog != null) mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}

package com.example.notesapp.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.contract.AddNoteContract;
import com.example.notesapp.presenter.AddNotePresenter;
import com.example.notesapp.repository.NoteRepositories;

public class AddNoteFragment extends BaseFragment implements AddNoteContract.View {

    public static final String TAG =  AddNoteFragment.class.getSimpleName();

    private EditText mTitleEt;
    private EditText mDescEt;
    private Button mAddBtn;

    private ProgressDialog mProgressDialog;
    private AddNoteContract.UserActionsListener mListener;

    public AddNoteFragment() {

    }

    public static AddNoteFragment newInstance() {
        return new AddNoteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener = new AddNotePresenter(NoteRepositories.getInMemoryRepoInstance(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_note, container, false);
        mTitleEt = (EditText) root.findViewById(R.id.title_edit_text);
        mTitleEt.addTextChangedListener(mWatcherForTitle);
        mDescEt = (EditText) root.findViewById(R.id.desc_edit_text);
        mDescEt.addTextChangedListener(mWatcherForDesc);
        mAddBtn = (Button) root.findViewById(R.id.add_button);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mTitleEt.getText().toString())) {
                    Toast.makeText(getActivity(), "Title must not be empty", Toast.LENGTH_LONG).show();
                } else {
                    mListener.saveNote(mTitleEt.getText().toString(), mDescEt.getText().toString());
                    mProgressDialog = ProgressDialog.show(getActivity(), "", "Loading...");
                }
            }
        });
        return root;
    }

    @Override
    public void showNotes() {
        if (mProgressDialog != null) mProgressDialog.dismiss();
        getActivity().finish();
    }

    @Override
    public void onFailed(String message) {
        if (mProgressDialog != null) mProgressDialog.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}

package com.example.notesapp.ui.fragment;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Admin on 26.10.2016.
 */

public abstract class BaseFragment extends Fragment {

    protected MyTextWatcher mWatcherForTitle = new MyTextWatcher(250);
    protected MyTextWatcher mWatcherForDesc = new MyTextWatcher(1000);

    class MyTextWatcher implements TextWatcher {

        private int mSymbolsCountLimit;

        public MyTextWatcher(int textLimit) {
            mSymbolsCountLimit = textLimit;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Log.d("MyTextWatcher", "s.length=" + s.length());
            if (s.length() >= mSymbolsCountLimit) {
                Toast.makeText(getActivity(), "Too much symbols", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}

package com.study.tindernews.ui.save;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.tindernews.R;

public class SaveFragment extends Fragment {

    public SaveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment -- 这个inflater的context应该是activity了。
        return inflater.inflate(R.layout.fragment_save, container, false);
    }
}
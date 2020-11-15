package com.alphazuluzero.dbugstation.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.databinding.FragmentContentBinding;

public class ContentFragment extends Fragment {
    private AppCompatActivity activity;
    private String title;
    private static final String TAG = "ContentFragment";


    public ContentFragment(AppCompatActivity activity, String title) {
        this();
        this.activity = activity;
        this.title = title;
    }

    public ContentFragment() {
    }

    @SuppressLint("LogNotTimber")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_content, container, false);
        FragmentContentBinding contentBinding = DataBindingUtil.bind(view);
        assert contentBinding != null;


        return contentBinding.getRoot();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

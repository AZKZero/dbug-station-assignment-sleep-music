package com.alphazuluzero.dbugstation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alphazuluzero.dbugstation.DummyDataGenerator;
import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.adapters.MusicListAdapter;
import com.alphazuluzero.dbugstation.interfaces.OnFragmentBackPressListener;

public class AlbumFragment extends Fragment {

    private AppCompatActivity activity;
    private String title;
    private OnFragmentBackPressListener onFragmentBackPressListener;

    public AlbumFragment(AppCompatActivity activity, String title, OnFragmentBackPressListener onFragmentBackPressListener) {
        this();
        this.activity = activity;
        this.title = title;
        this.onFragmentBackPressListener = onFragmentBackPressListener;
    }

    private AlbumFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_album, container, false);
        // DataBinding for this fragment
        com.alphazuluzero.dbugstation.databinding.FragmentAlbumBinding albumBinding = DataBindingUtil.bind(view);
        assert albumBinding != null;
        assert activity != null;

        if (title != null) albumBinding.title.setText(String.format("%s Music", title));

        albumBinding.testList.setLayoutManager(new LinearLayoutManager(activity));
        albumBinding.testList.setAdapter(new MusicListAdapter(DummyDataGenerator.generateModels(), activity));

        albumBinding.back.setOnClickListener(v -> onFragmentBackPressListener.onFragmentBackPressed());

        return albumBinding.getRoot();
    }
}
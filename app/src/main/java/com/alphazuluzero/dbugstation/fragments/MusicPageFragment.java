package com.alphazuluzero.dbugstation.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alphazuluzero.dbugstation.DummyDataGenerator;
import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.activities.TestRecyclerViewActivity;
import com.alphazuluzero.dbugstation.adapters.MusicListAdapter;
import com.alphazuluzero.dbugstation.databinding.TabItemTopBinding;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class MusicPageFragment extends Fragment {
    private AppCompatActivity activity;
    private String title;
    private static final String TAG = "MusicPageFragment";


    public MusicPageFragment(AppCompatActivity activity, String title) {
        this();
        this.activity = activity;
        this.title = title;
    }

    public MusicPageFragment() {
    }

    @SuppressLint("LogNotTimber")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        com.alphazuluzero.dbugstation.databinding.FragmentHomeBinding homeBinding = DataBindingUtil.bind(view);
        assert homeBinding != null;
        assert activity != null;

        WindowManager manager = (WindowManager) activity.getBaseContext().getSystemService(Context.WINDOW_SERVICE);

        Point point = new Point();
        manager.getDefaultDisplay().getSize(point);

        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                Log.i(TAG, "transform: " + container.getWidth());

                int targetWidth = point.x;

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        Picasso.get().load(R.drawable._228_1_enlarged).transform(transformation).into(homeBinding.homeCard.homeCardImage);
        for (int i = 0; i < 5; i++) {
            TabItemTopBinding tabItemTopBinding = DataBindingUtil.bind(View.inflate(activity, R.layout.tab_item_top, null));
            assert tabItemTopBinding != null;
            switch (i) {
                default:
                case 0:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.tab_all));
                    tabItemTopBinding.text1.setText(R.string.tab_all);
                    break;
                case 1:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.tab_my));
                    tabItemTopBinding.text1.setText(R.string.my);
                    break;
                case 2:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.tab_anxious));
                    tabItemTopBinding.text1.setText(R.string.anxious);
                    break;
                case 3:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.tab_sleep));
                    tabItemTopBinding.text1.setText(R.string.sleep);
                    break;
                case 4:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.tab_kids));
                    tabItemTopBinding.text1.setText(R.string.kids);
                    break;
            }
            TabLayout.Tab tabAt = homeBinding.tabsTop.newTab();
//            if (tabAt != null) {
            tabAt.setCustomView(tabItemTopBinding.getRoot());
            homeBinding.tabsTop.addTab(tabAt);

//            }
        }

        homeBinding.tabsTop.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view2 = tab.getCustomView();
                assert view2 != null;
                startActivity(new Intent(activity, TestRecyclerViewActivity.class).putExtra("tag", ((TextView) view2.findViewById(R.id.text1)).getText()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        RecyclerView recyclerView = homeBinding.music;
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new MusicListAdapter(DummyDataGenerator.generateModels(), activity));
//        CustomDividerDecoration dividerDecoration = new CustomDividerDecoration(ContextCompat.getDrawable(activity, R.drawable.line_red_thin));
//        recyclerView.addItemDecoration(dividerDecoration);
//        recyclerView.setAdapter(hotelItemAdapter);


        return homeBinding.getRoot();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

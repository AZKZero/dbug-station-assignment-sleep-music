package com.alphazuluzero.dbugstation.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.adapters.MusicPagerAdapter;
import com.alphazuluzero.dbugstation.databinding.ActivityHomeBinding;
import com.alphazuluzero.dbugstation.databinding.TabItemTopBinding;
import com.alphazuluzero.dbugstation.fragments.ContentFragment;
import com.alphazuluzero.dbugstation.fragments.MusicPageFragment;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        WindowManager manager = (WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE);

        Point point = new Point();
        manager.getDefaultDisplay().getSize(point);

        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
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

        Picasso.get().load(R.drawable.clound_background).transform(transformation).into(activityHomeBinding.cloud);

        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        titles.add("Home");
        titles.add("Sleep");
        titles.add("Meditate");
        titles.add("Music");
        titles.add("Afsar");

        fragments.add(new MusicPageFragment(this, titles.get(0)));
        fragments.add(new ContentFragment(this, titles.get(1)));
        fragments.add(new ContentFragment(this, titles.get(2)));
        fragments.add(new ContentFragment(this, titles.get(3)));
        fragments.add(new ContentFragment(this, titles.get(4)));

        activityHomeBinding.container.setAdapter(new MusicPagerAdapter(getSupportFragmentManager(), fragments));
        activityHomeBinding.tabsBottom.setupWithViewPager(activityHomeBinding.container);

        for (int i = 0; i < 5; i++) {
            TabItemTopBinding tabItemTopBinding = DataBindingUtil.bind(View.inflate(this, R.layout.tab_item_top, null));
            assert tabItemTopBinding != null;
            switch (i) {
                default:
                case 0:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home));
                    tabItemTopBinding.text1.setText(titles.get(0));
                    break;
                case 1:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tab_sleep));
                    tabItemTopBinding.text1.setText(titles.get(1));
                    break;
                case 2:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.meditate));
                    tabItemTopBinding.text1.setText(titles.get(2));
                    break;
                case 3:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.music));
                    tabItemTopBinding.text1.setText(titles.get(3));
                    break;
                case 4:
                    tabItemTopBinding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.afsar));
                    tabItemTopBinding.text1.setText(titles.get(4));
                    break;
            }
            TabLayout.Tab tabAt = activityHomeBinding.tabsBottom.getTabAt(i);
            if (tabAt != null) {
                tabAt.setCustomView(tabItemTopBinding.getRoot());
            }
        }
    }
}
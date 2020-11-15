package com.alphazuluzero.dbugstation.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alphazuluzero.dbugstation.DummyDataGenerator;
import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.adapters.MusicListAdapter;
import com.alphazuluzero.dbugstation.databinding.ActivityTestRecyclerViewBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class TestRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestRecyclerViewBinding activityTestRecyclerViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_recycler_view);

        String tag = getIntent().getStringExtra("tag");
        if (tag != null) activityTestRecyclerViewBinding.title.setText(String.format("%s Music", tag));

        activityTestRecyclerViewBinding.testList.setLayoutManager(new LinearLayoutManager(this));
        activityTestRecyclerViewBinding.testList.setAdapter(new MusicListAdapter(DummyDataGenerator.generateModels(), this));

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
//        Picasso.get().load(R.drawable.clound_background).transform(transformation).into(activityTestRecyclerViewBinding.cloud);
        activityTestRecyclerViewBinding.back.setOnClickListener(v -> finish());
    }
}
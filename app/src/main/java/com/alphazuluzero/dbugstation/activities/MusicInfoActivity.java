package com.alphazuluzero.dbugstation.activities;

import android.content.Context;
import android.content.Intent;
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
import com.alphazuluzero.dbugstation.databinding.ActivityMusicInfoBinding;
import com.alphazuluzero.dbugstation.models.MusicListModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.Locale;

public class MusicInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMusicInfoBinding musicInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_music_info);

        WindowManager manager = (WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE);

        Point point = new Point();
        manager.getDefaultDisplay().getSize(point);

        Intent callingIntent = getIntent();

        MusicListModel musicModel = DummyDataGenerator.getMusicModel(callingIntent.getIntExtra("musicId", 0));
        if (musicModel != null) {
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
            Picasso.get().load(musicModel.getMusicImageResInt()).transform(transformation).into(musicInfoBinding.musicImage);

            musicInfoBinding.musicName.setText(musicModel.getMusicName());
            musicInfoBinding.musicDuration.setText(String.format(Locale.US, "%d MIN", musicModel.getDurationInSeconds() / 60));
            musicInfoBinding.musicAlbum.setText(musicModel.getAlbumName());

            musicInfoBinding.favorites.setText(String.format(Locale.US, "%,d Favorites", DummyDataGenerator.getRandomRatings()));
            musicInfoBinding.listening.setText(String.format(Locale.US, "%,d Listening", DummyDataGenerator.getRandomRatings()));

            musicInfoBinding.playMusic.setOnClickListener(v -> startActivity(new Intent(this, PlayerActivity.class).putExtra("musicId", musicModel.getId())));
            musicInfoBinding.related.setLayoutManager(new LinearLayoutManager(this));
            musicInfoBinding.related.setAdapter(new MusicListAdapter(DummyDataGenerator.generateModels(), this));
        }
    }
}
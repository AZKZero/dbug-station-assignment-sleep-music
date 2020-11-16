package com.alphazuluzero.dbugstation.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alphazuluzero.dbugstation.DummyDataGenerator;
import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.databinding.ActivityPlayerBinding;
import com.alphazuluzero.dbugstation.models.MusicListModel;

import java.util.Locale;

public class PlayerActivity extends AppCompatActivity {

    private static final String TAG = "PlayerActivity";

    int currentSeconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlayerBinding activityPlayerBinding = DataBindingUtil.setContentView(this, R.layout.activity_player);

        Intent callingIntent = getIntent();

        MusicListModel musicModel = DummyDataGenerator.getMusicModel(callingIntent.getIntExtra("musicId", 0));
        if (musicModel != null) {
            activityPlayerBinding.musicName.setText(musicModel.getMusicName());
            activityPlayerBinding.catAlbumSubtext.setText(musicModel.getAlbumName());
            double seconds = musicModel.getDurationInSeconds();
            activityPlayerBinding.startTime.setText(R.string._00_00);
            activityPlayerBinding.endTime.setText(String.format(Locale.US, "%02d:%02d", (int) seconds / 60, (int) seconds % 60));

            activityPlayerBinding.back15.setOnClickListener(v -> {
                currentSeconds = currentSeconds < 15 ? 0 : currentSeconds - 15;
                activityPlayerBinding.startTime.setText(String.format(Locale.US, "%02d:%02d", (int) currentSeconds / 60, (int) currentSeconds % 60));
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) activityPlayerBinding.playbar.setProgress(currentSeconds * 100 / musicModel.getDurationInSeconds());
                else activityPlayerBinding.playbar.setProgress(currentSeconds * 100 / musicModel.getDurationInSeconds(), true);
            });
            activityPlayerBinding.front15.setOnClickListener(v -> {
                currentSeconds = Math.min(currentSeconds + 15, musicModel.getDurationInSeconds());
                Log.i(TAG, "onCreate: " + currentSeconds + " " + currentSeconds + " " + currentSeconds * 100 / musicModel.getDurationInSeconds());
                activityPlayerBinding.startTime.setText(String.format(Locale.US, "%02d:%02d", (int) currentSeconds / 60, (int) currentSeconds % 60));
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) activityPlayerBinding.playbar.setProgress(currentSeconds * 100 / musicModel.getDurationInSeconds());
                else activityPlayerBinding.playbar.setProgress(currentSeconds * 100 / musicModel.getDurationInSeconds(), true);
            });

            activityPlayerBinding.playbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        currentSeconds = musicModel.getDurationInSeconds() * progress / 100;
                        activityPlayerBinding.startTime.setText(String.format(Locale.US, "%02d:%02d", (int) currentSeconds / 60, (int) currentSeconds % 60));
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

    }
}
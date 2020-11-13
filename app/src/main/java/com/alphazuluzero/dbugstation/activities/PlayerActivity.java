package com.alphazuluzero.dbugstation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alphazuluzero.dbugstation.DummyDataGenerator;
import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.databinding.ActivityPlayerBinding;
import com.alphazuluzero.dbugstation.models.MusicListModel;

import java.util.Locale;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlayerBinding activityPlayerBinding = DataBindingUtil.setContentView(this, R.layout.activity_player);

        Intent callingIntent = getIntent();

        MusicListModel musicModel = DummyDataGenerator.musicListModels.get(callingIntent.getIntExtra("musicId", 0));
        activityPlayerBinding.musicName.setText(musicModel.getMusicName());
        activityPlayerBinding.catAlbumSubtext.setText(musicModel.getAlbumName());
        double seconds = musicModel.getDurationInSeconds();
        activityPlayerBinding.startTime.setText("00:00");
        activityPlayerBinding.endTime.setText(String.format(Locale.US, "%02d:%02d", (int) seconds / 60, (int) seconds % 60));

        activityPlayerBinding.playbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double currentSeconds = musicModel.getDurationInSeconds() * progress / 100;
                activityPlayerBinding.startTime.setText(String.format(Locale.US, "%02d:%02d", (int) currentSeconds / 60, (int) currentSeconds % 60));
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
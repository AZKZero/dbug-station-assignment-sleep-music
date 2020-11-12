package com.alphazuluzero.dbugstation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.databinding.ActivityIntroBinding;
import com.alphazuluzero.dbugstation.databinding.ActivityIntroBindingImpl;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityIntroBinding activityIntroBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro);
        activityIntroBinding.getStarted.setOnClickListener(v -> {
            startActivity(new Intent(this, PlayerActivity.class));
        });
    }
}
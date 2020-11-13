package com.alphazuluzero.dbugstation.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alphazuluzero.dbugstation.DummyDataGenerator;
import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityIntroBinding activityIntroBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro);
        DummyDataGenerator.generateModels();
        activityIntroBinding.getStarted.setOnClickListener(v -> {
            startActivity(new Intent(this, TestRecyclerViewActivity.class));
        });
    }
}
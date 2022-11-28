package com.example.app;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.databinding.ActivityScreenBinding;

import java.util.Random;

public class screen extends AppCompatActivity implements View.OnClickListener {
    ActivityScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bup:
                RelativeLayout background = findViewById(R.id.background);
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                background.setBackgroundColor(color);
        }
    }
}
package com.example.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.databinding.ActivityMainBinding;

public class login extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();

                if ((email.indexOf("@") == -1) && (password.length() < 8)) {
                    binding.hint1.setTextColor(Color.parseColor("#9E0000"));
                    binding.hint2.setTextColor(Color.parseColor("#9E0000"));
                } else if ((email.indexOf("@") == -1) || (password.length() < 8)) {
                    if (email.indexOf("@") == -1) {
                        binding.hint1.setTextColor(Color.parseColor("#9E0000"));
                        binding.hint2.setTextColor(Color.parseColor("#182A6E"));
                    } else {
                        binding.hint2.setTextColor(Color.parseColor("#9E0000"));
                        binding.hint1.setTextColor(Color.parseColor("#182A6E"));
                    }
                    break;
                } else {
                    Intent intent1 = new Intent(login.this, showing.class);
                    intent1.putExtra("email", binding.email.getText().toString());
                    startActivity(intent1);
                    finish();
                }
                break;
        }

    }
}
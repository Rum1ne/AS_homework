package com.example.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.databinding.ActivityShowingBinding;


public class showing extends AppCompatActivity implements View.OnClickListener {
    ActivityShowingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonToMainScreen.setOnClickListener(this);
        binding.takeAPicture.setOnClickListener(this);

        Intent intent_add = getIntent();
        String email = intent_add.getStringExtra("email");
        binding.yourEmail.setText(email);

        imageView = (ImageView) findViewById(binding.picture);

    }

    private Object findViewById(EditText picture) {
        return R.id.picture;
    }


    private static final int REQUEST_TAKE_PHOTO = 1;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 2;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_to_main_screen:
                Intent intent_to_main_screen = new Intent(showing.this, screen.class);
                startActivity(intent_to_main_screen);
                finish();
                break;
            case R.id.take_a_picture:
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent intent_to_result = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent_to_result, REQUEST_TAKE_PHOTO);
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("gosh", "request code = " + requestCode + " result Code = " + resultCode);
        if (resultCode == RESULT_OK && requestCode == REQUEST_TAKE_PHOTO) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }

    }
}


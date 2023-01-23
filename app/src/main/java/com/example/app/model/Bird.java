package com.example.app.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.app.R;

public class Bird extends GameObject {
    private static final float Y_ACCELERATION = 6f;
    private Bitmap sprite;
    private final Bitmap up;
    private final Bitmap straight;
    private final Bitmap down;
    private float ySpeed = 0;

    public Bitmap getSprite() {
        return sprite;
    }

    public Bird(Context context, float x, float y) {
        super(x, y);
        up = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluebird_3);
        straight = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluebird_2);
        down = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluebird_1);

        sprite = straight;
    }

    public void fly() {
        ySpeed = -50;
    }

    @Override
    public void update() {
        y += ySpeed;
        ySpeed += Y_ACCELERATION;
        if (ySpeed < -20) sprite = down;
        else if (ySpeed > 20) sprite = up;
        else sprite = straight;
    }
}

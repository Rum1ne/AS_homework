package com.example.app.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.app.R;

public class Pipe extends GameObject {
    private Bitmap topPipe;
    private Bitmap bottomPipe;

    private static final float X_SPEED = 10;
    private static final float SPACER_SIZE = 400;


    private final float height;
    private final float width;

    private int currentScore = 0;

    public int getCurrentScore() {
        return currentScore;
    }

    public Pipe(Context context, float height, float width) {
        super(width, 0);
        this.height = height;
        this.width = width;
        topPipe = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe_rotated);
        bottomPipe = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe);
    }

    private void generatePipes() {
        y = random(height / 4f, height * 3 / 4f);

        topPipe = Bitmap.createScaledBitmap(topPipe, 50, (int) (y - SPACER_SIZE / 2), false);
        bottomPipe = Bitmap.createScaledBitmap(topPipe, 50, (int) (height - y - SPACER_SIZE / 2), false);
    }

    @Override
    public void update() {
        x -= X_SPEED;
        if (x < bottomPipe.getWidth()) {
            x = width;
            generatePipes();
            ++currentScore;
        }
    }

    public boolean isCollision(GameObject object) {
        if (x - 10 < object.x && x + bottomPipe.getWidth() > object.x) {
            if (object.y < topPipe.getHeight()) return true;
            if (object.y > height - bottomPipe.getHeight()) return true;
        }
        return false;
    }

    private float random(float min, float max) {
        return (float) (min + (Math.random() * (max - min)));
    }

    public Bitmap getTopPipe() {
        return topPipe;
    }

    public Bitmap getBottomPipe() {
        return bottomPipe;
    }
}

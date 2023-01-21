package com.example.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final float SPEED = 20;
    private final Paint paint = new Paint();
    private SurfaceHolder surfaceHolder;
    private final DrawThread drawThread;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        drawThread = new DrawThread();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.surfaceHolder = holder;
        drawThread.start();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    class DrawThread extends Thread {
        private volatile boolean running = true;
        private Canvas canvas;
        private float towardX;
        private float towardY;
        float speedX;
        float speedY;

        public void setTowardPoint(float towardX, float towardY) {
            this.towardX = towardX;
            this.towardY = towardY;
        }

        private void calculatePosition(float positionX, float positionY) {
            float dx = positionX - drawThread.towardX;
            float dx_r = drawThread.towardX - positionX;
            float dy = positionY - drawThread.towardY;
            float dy_r = drawThread.towardY - positionY;
            float movement = 0;
            if (dx > 0 && dy > 0) {
                movement = (float) Math.sqrt((double) dx * dx + dy * dy);
            } else if (dx > 0 && dy < 0) {
                movement = (float) Math.sqrt((double) dx * dx + dy_r * dy_r);
            } else if (dx < 0 && dy > 0) {
                movement = (float) Math.sqrt((double) dx_r * dx_r + dy * dy);
            } else if (dx < 0 && dy < 0) {
                movement = (float) Math.sqrt((double) dx_r * dx_r + dy_r * dy_r);
            }
            float time = movement / SPEED;
            if (dx > 0) {
                speedX = dx / time;
            } else {
                speedX = dx_r / time;
            }

            if (dy > 0) {
                speedY = dy / time;
            } else {
                speedY = dy_r / time;
            }

        }

        @Override
        public void run() {
            float positionX = getWidth() / 2f;
            float positionY = getHeight() / 2f;

            calculatePosition(positionX, positionY);
            while (running) {
                try {
                    canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(getResources().getColor(R.color.light_blue));
                    paint.setColor(Color.RED);
                    canvas.drawCircle(positionX, positionY, 50, paint);

                    if (positionX < towardX) positionX += speedX;
                    if (positionX > towardX) positionX -= speedX;
                    if (positionY < towardY) positionY += speedY;
                    if (positionY > towardY) positionY -= speedY;


                } catch (Exception e) {
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        drawThread.setTowardPoint(event.getX(), event.getY());
        return super.onTouchEvent(event);
    }
}
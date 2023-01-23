package com.example.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.app.model.Bird;
import com.example.app.model.Pipe;
import com.example.app.ui.Numbers;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private static final int FPS = 300;
    private final Bitmap background;
    private final Bitmap restart;
    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread;
    private Bird bird;
    private Pipe pipe;


    public GameView(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
        restart = BitmapFactory.decodeResource(context.getResources(), R.drawable.restart);
        drawThread = new DrawThread();
        getHolder().addCallback(this);
    }

    private void init() {
        bird = new Bird(getContext(), 200, getHeight() / 2f);
        pipe = new Pipe(getContext(), getHeight(), getWidth());
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        init();
        drawThread.start();
    }

    private void drawFrames(Canvas canvas) {
        Rect backgroundRect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawBitmap(background, null, backgroundRect, null);
        canvas.drawBitmap(bird.getSprite(), bird.x, bird.y, null);
        canvas.drawBitmap(pipe.getTopPipe(), pipe.x, 0, null);
        canvas.drawBitmap(pipe.getBottomPipe(), pipe.x, getHeight() - pipe.getBottomPipe().getHeight(), null);
        Rect buttonRect = new Rect(getWidth() / 2 - 200, getHeight() - 400, getWidth() / 2 + 200, getHeight());
        canvas.drawBitmap(restart, null, buttonRect, null);


    }


    private void update() {
        bird.update();
        pipe.update();
        if (pipe.isCollision(bird) || bird.y <= 0 || bird.y >= getHeight()) {
            drawThread.running = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ((event.getX() > getWidth() / 2f - 150 && event.getX() < getWidth() / 2f + 150) && (event.getY() > getHeight() - 250 && event.getY() < getHeight() - 150)) {
            drawThread = new DrawThread();
            init();
            drawThread.start();
            return super.onTouchEvent(event);
        } else {
            bird.fly();
            if (!drawThread.running) {
                drawThread = new DrawThread();
                pipe.setCurrentScore(0);
                init();
                drawThread.start();
            }
            return super.onTouchEvent(event);
        }

    }

    private class DrawThread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = surfaceHolder.lockCanvas();
                try {
                    sleep(1000 / FPS);
                    drawFrames(canvas);
                    showCurrentScore(canvas);
                    update();
                } catch (Exception e) {
                    Log.e("RRR", "run: ", e);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void showCurrentScore(Canvas canvas) {
        Rect firstNumRect = new Rect(getWidth() / 2 - 30, 10, getWidth() / 2 - 2, 60);
        Rect secondNumRect = new Rect(getWidth() / 2 + 2, 10, getWidth() / 2 + 30, 60);
        switch (pipe.getCurrentScore() / 10) {
            case 0:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);
                        break;
                }
            case 1:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.one, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.one, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.one, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.one, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.one, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.one, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.one, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.one, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.zero, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.one, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);
                        break;
                }

            case 2:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);
                        break;
                }
            case 3:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.three, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);
                        break;
                }
            case 4:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.four, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);
                        break;
                }
            case 5:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.five, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);
                        break;
                }
            case 6:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.six, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);
                        break;
                }
            case 7:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.seven, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);
                        break;
                }
            case 8:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.eight, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);
                        break;
                }
            case 9:
                switch (pipe.getCurrentScore() % 10) {
                    case 0:
                        canvas.drawBitmap(Numbers.nine, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.zero, null, secondNumRect, null);
                        break;
                    case 1:
                        canvas.drawBitmap(Numbers.nine, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.one, null, secondNumRect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(Numbers.nine, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.two, null, secondNumRect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(Numbers.nine, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.three, null, secondNumRect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(Numbers.nine, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.four, null, secondNumRect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(Numbers.nine, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.five, null, secondNumRect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(Numbers.nine, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.six, null, secondNumRect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(Numbers.two, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.seven, null, secondNumRect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(Numbers.nine, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.eight, null, secondNumRect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(Numbers.nine, null, firstNumRect, null);
                        canvas.drawBitmap(Numbers.nine, null, secondNumRect, null);                      //for god`s sake what`s that
                        break;
                }
        }
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
    }
}

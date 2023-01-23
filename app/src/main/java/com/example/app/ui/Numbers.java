package com.example.app.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.app.R;

public class Numbers {
    public static Bitmap zero;
    public static Bitmap one;
    public static Bitmap two;
    public static Bitmap three;
    public static Bitmap four;
    public static Bitmap five;
    public static Bitmap six;
    public static Bitmap seven;
    public static Bitmap eight;
    public static Bitmap nine;

    public Numbers(Context context) {
        zero = BitmapFactory.decodeResource(context.getResources(), R.drawable.zero);
        one = BitmapFactory.decodeResource(context.getResources(), R.drawable.one);
        two = BitmapFactory.decodeResource(context.getResources(), R.drawable.two);
        three = BitmapFactory.decodeResource(context.getResources(), R.drawable.three);
        four = BitmapFactory.decodeResource(context.getResources(), R.drawable.four);
        five = BitmapFactory.decodeResource(context.getResources(), R.drawable.five);
        six = BitmapFactory.decodeResource(context.getResources(), R.drawable.six);
        seven = BitmapFactory.decodeResource(context.getResources(), R.drawable.seven);
        eight = BitmapFactory.decodeResource(context.getResources(), R.drawable.eight);
        nine = BitmapFactory.decodeResource(context.getResources(), R.drawable.nine);

    }
}

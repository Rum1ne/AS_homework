package com.example.app;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText editTextA;
    private EditText editTextB;
    private EditText editTextC;
    private TextView textView1;
    private TextView textView2;
    private TextView textViewEQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextA = findViewById(R.id.edit_text_a);
        editTextB = findViewById(R.id.edit_text_b);
        editTextC = findViewById(R.id.edit_text_c);
        textView1 = findViewById(R.id.text_view_x1);
        textView2 = findViewById(R.id.text_view_x2);
        textViewEQ = findViewById(R.id.text_view_eq);
    }

    private int getIntValue(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())/*editText.getText().toString().equals(" ")*/) {
            Toast.makeText(this, "Поля не могут быть пустыми, коэффициент равен 1", Toast.LENGTH_SHORT).show();
            return 1;
        }
        return Integer.parseInt((editText.getText().toString()));
    }

    public void click(View view) {
        int a = getIntValue(editTextA);
        int b = getIntValue(editTextB);
        int c = getIntValue(editTextC);
        textViewEQ.setText(a + "x^2 + " + b + "x + " + c + " = 0");
        double d = b * b - 4 * a * c;
        if (a == 0) {
            if (b == 0) {
                textView1.setText("Бесконечное множество корней");
                //Toast.makeText(this, "Бесконечно много", Toast.LENGTH_SHORT).show();
                textView2.setText("");
            } else {
                double x = -c / b;
                textView1.setText("x = " + String.format("%.2f", x));
                //Toast.makeText(this, "x = " + x, Toast.LENGTH_SHORT).show();
                textView2.setText("");
            }
        } else if (b == 0) {
            if (-c > 0) {
                textView1.setText("x1 = " + String.format("%.2f", Math.sqrt(-c)));
                //Toast.makeText(this, "x1 = " + Math.sqrt(-c), Toast.LENGTH_SHORT).show();
                textView2.setText("x2 = " + String.format("%.2f", -Math.sqrt(-c)));
                //Toast.makeText(this, "x2 = " + (-Math.sqrt(-c)), Toast.LENGTH_SHORT).show();
            } else if (c == 0) {
                textView1.setText("x = " + 0);
                //Toast.makeText(this, "x = " + 0, Toast.LENGTH_SHORT).show();
                textView2.setText("");
            } else {
                textView1.setText("Решения нет");
                //Toast.makeText(this, "Корней нет", Toast.LENGTH_SHORT).show();
                textView2.setText("");
            }
        } else if (d < 0) {
            textView1.setText("Решения нет");
            //Toast.makeText(this, "Корней нет", Toast.LENGTH_SHORT).show();
            textView2.setText("");
        } else if (d == 0) {
            double x = -b / (2 * a);
            textView1.setText("x = " + String.format("%.2f", x));
            //Toast.makeText(this, "x = " + x, Toast.LENGTH_SHORT).show();
            textView2.setText("");
        } else {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            textView1.setText("x1 = " + String.format("%.2f", x1));
            //Toast.makeText(this, "x1 = " + x1, Toast.LENGTH_SHORT).show();
            textView2.setText("x2 = " + String.format("%.2f", x2));
            //Toast.makeText(this, "x2 = " + x2, Toast.LENGTH_SHORT).show();
        }
    }
}

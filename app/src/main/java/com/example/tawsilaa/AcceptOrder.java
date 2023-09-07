package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class AcceptOrder extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_accept_order);

        final TextView mTextField = findViewById(R.id.mTextField);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("done!");
            }
        }.start();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(AcceptOrder.this, HomeDAO.class);
                startActivity(intent);
                finish();
            }
        }, 30000);

        double seconds = 30;
        double pricePerSecond = 0.7;
        double initprice = 0.9;
        double result = (seconds * pricePerSecond) + initprice;
        String resultText = "Result: " + result + " DT";

        // Display the result in a text view
        TextView pricetxt1 = findViewById(R.id.uni2);
        pricetxt1.setText(resultText);

        ImageView backarrow = findViewById(R.id.backarrow7);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chkoun el classe lokhra????? lwin yerjaa?
                Intent checkout = new Intent(AcceptOrder.this, SeebookD.class);
                startActivity(checkout);
            }
        });
    }
}
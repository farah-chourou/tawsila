package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class Checkout extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_checkout);

        double seconds = 30;
        double pricePerSecond = 0.7;
        double initprice = 0.9;
        double result = (seconds * pricePerSecond) + initprice;
        String resultText = "Result: " + result + " DT";

        // Display the result in a text view
        TextView pricetxt1 = findViewById(R.id.pricetxt1);
        pricetxt1.setText(resultText);


        ImageView backarrow = findViewById(R.id.backarrow2);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chkoun el classe lokhra????? lwin yerjaa?
                Intent checkout = new Intent(Checkout.this, OrderC.class);
                startActivity(checkout);
            }
        });

        View pay2 = findViewById(R.id.pay2);
        pay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chkoun el classe lokhra????? lwin yerjaa?
                Intent pay2 = new Intent(Checkout.this, AddMastercard.class);
                startActivity(pay2);
            }
        });

        View pay3 = findViewById(R.id.pay);
        pay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chkoun el classe lokhra????? lwin yerjaa?
                Intent pay3 = new Intent(Checkout.this, AddVisa.class);
                startActivity(pay3);
            }
        });


    }
}

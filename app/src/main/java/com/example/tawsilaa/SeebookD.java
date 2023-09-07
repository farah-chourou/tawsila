package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class SeebookD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_seebook_d);

        double seconds = 30;
        double pricePerSecond = 0.7;
        double initprice = 0.9;
        double result = (seconds * pricePerSecond) + initprice;
        String resultText = "Result: " + result + " DT";

        // Display the result in a text view
        TextView pricetxt1 = findViewById(R.id.pricetxt6);
        pricetxt1.setText(resultText);

        ImageView backarrow = findViewById(R.id.backarrow8);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkout = new Intent(SeebookD.this, HomeD.class);
                startActivity(checkout);
            }
        });

        Button accept = findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accept = new Intent(SeebookD.this, AcceptOrder.class);
                startActivity(accept);
            }
        });

        Button reject = findViewById(R.id.accept);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reject = new Intent(SeebookD.this, RejectOrder.class);
                startActivity(reject);
            }
        });
    }
}
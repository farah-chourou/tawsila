package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

public class MoreinfoC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_moreinfo_c);

        ImageView backarrow = findViewById(R.id.backarrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backarrowIntent = new Intent(MoreinfoC.this, HomeC.class);
                startActivity(backarrowIntent);
            }
        });

        Button btnorder2 = findViewById(R.id.order);
        btnorder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MoreInfoIntent = new Intent(MoreinfoC.this, AddBooking.class);
                startActivity(MoreInfoIntent);
            }
        });
    }
}
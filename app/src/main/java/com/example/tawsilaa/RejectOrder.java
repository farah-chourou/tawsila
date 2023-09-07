package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Objects;

public class RejectOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_reject_order);

        //intent home
        ImageView Dhomelogo1 = findViewById(R.id.dhomelogo2);
        Dhomelogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(RejectOrder.this, RejectOrder.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView Dprofi1 = findViewById(R.id.dprofi2);
        Dprofi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(RejectOrder.this, ProfileD.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView Dhistory1 = findViewById(R.id.dhisto2);
        Dhistory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(RejectOrder.this, HistoryD.class);
                startActivity(History2);
            }
        });

        ImageView Dsettings1 = findViewById(R.id.dsett2);
        Dsettings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(RejectOrder.this, SettingsD.class);
                startActivity(Settings2);
            }
        });
    }
}
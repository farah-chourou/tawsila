package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.Objects;

public class HomeC extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_home_c);

        ImageView driver = findViewById(R.id.ActiveDriver);
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driver = new Intent(HomeC.this, OrderC.class);
                startActivity(driver);
            }
        });

        //intent home
        ImageView homelogo2 = findViewById(R.id.homelogoHC);
        homelogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(HomeC.this, HomeC.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView profi2 = findViewById(R.id.profiHC);
        profi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(HomeC.this, ProfileC.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView history2 = findViewById(R.id.histoHC);
        history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(HomeC.this, HistoryC.class);
                startActivity(History2);
            }
        });

        ImageView settings2 = findViewById(R.id.settHC);
        settings2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(HomeC.this, SettingsC.class);
                startActivity(Settings2);
            }
        });
    }
}
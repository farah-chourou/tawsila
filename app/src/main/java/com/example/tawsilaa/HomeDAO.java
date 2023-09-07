package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Objects;

public class HomeDAO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_home_dao);

        //intent home
        ImageView Dhomelogo1 = findViewById(R.id.dhomelogo);
        Dhomelogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(HomeDAO.this, HomeDAO.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView Dprofi1 = findViewById(R.id.dprofi);
        Dprofi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(HomeDAO.this, ProfileD.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView Dhistory1 = findViewById(R.id.dhisto);
        Dhistory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(HomeDAO.this, HistoryD.class);
                startActivity(History2);
            }
        });

        ImageView Dsettings1 = findViewById(R.id.dsett);
        Dsettings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(HomeDAO.this, SettingsD.class);
                startActivity(Settings2);
            }
        });

    }
}
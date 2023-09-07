package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Objects;

public class HistoryDAO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_history_dao);

        //intent home
        ImageView homelogo1 = findViewById(R.id.homelogohd2);
        homelogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(HistoryDAO.this, HomeDAO.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView profi1 = findViewById(R.id.profihd2);
        profi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(HistoryDAO.this, ProfileD.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView history1 = findViewById(R.id.histohd2);
        history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(HistoryDAO.this, HistoryD.class);
                startActivity(History2);
            }
        });

        ImageView settings1 = findViewById(R.id.setthd2);
        settings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(HistoryDAO.this, SettingsD.class);
                startActivity(Settings2);
            }
        });
    }
}
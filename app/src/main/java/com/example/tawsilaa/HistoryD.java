package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Objects;

public class HistoryD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_history_d);


        //intent home
        ImageView homelogo1 = findViewById(R.id.homelogohd);
        homelogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(HistoryD.this, HomeD.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView profi1 = findViewById(R.id.profihd);
        profi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(HistoryD.this, ProfileD.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView history1 = findViewById(R.id.histohd);
        history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(HistoryD.this, HistoryD.class);
                startActivity(History2);
            }
        });

        ImageView settings1 = findViewById(R.id.setthd);
        settings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(HistoryD.this, SettingsD.class);
                startActivity(Settings2);
            }
        });
    }

}

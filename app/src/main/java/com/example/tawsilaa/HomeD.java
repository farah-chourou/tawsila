package com.example.tawsilaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Objects;

public class HomeD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_home_d);

        View tr1 = findViewById(R.id.tr1);
        tr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tr1 = new Intent(HomeD.this, SeebookD.class);
                startActivity(tr1);
            }
        });

        //intent home
        ImageView Dhomelogo1 = findViewById(R.id.Dhomelogo);
        Dhomelogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(HomeD.this, HomeD.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView Dprofi1 = findViewById(R.id.Dprofi);
        Dprofi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(HomeD.this, ProfileD.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView Dhistory1 = findViewById(R.id.Dhisto);
        Dhistory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(HomeD.this, HistoryD.class);
                startActivity(History2);
            }
        });

        ImageView Dsettings1 = findViewById(R.id.Dsett);
        Dsettings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(HomeD.this, SettingsD.class);
                startActivity(Settings2);
            }
        });

    }
}
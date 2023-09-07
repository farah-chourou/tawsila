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

public class OrderC extends AppCompatActivity {

    Button moreinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_order_c);

        moreinfo = findViewById(R.id.moreinfo);
        moreinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MoreinfoIntent = new Intent(OrderC.this, MoreinfoC.class);
                startActivity(MoreinfoIntent);
            }
        });


        //intent home
        ImageView homelogo2 = findViewById(R.id.homelogoOr);
        homelogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(OrderC.this, HomeC.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView profi2 = findViewById(R.id.profiOr);
        profi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(OrderC.this, ProfileC.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView history2 = findViewById(R.id.histoOr);
        history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(OrderC.this, HistoryC.class);
                startActivity(History2);
            }
        });


        ImageView settings2 = findViewById(R.id.settOr);
        settings2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(OrderC.this, SettingsC.class);
                startActivity(Settings2);
            }
        });


    }
}

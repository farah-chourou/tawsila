package com.example.tawsilaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class HistoryC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_history_c);

        View t1 = findViewById(R.id.t1);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t1 = new Intent(HistoryC.this, ReadBookPay.class);
                startActivity(t1);
            }
        });


        FirebaseFirestore db = FirebaseFirestore.getInstance();


        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (currentUserUid != null) {
            db.collection("Bookings").document(currentUserUid).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Bookings bookings = documentSnapshot.toObject(Bookings.class);
                                if (bookings != null) {
                                    TextView departure = findViewById(R.id.dep6);
                                    TextView arrival = findViewById(R.id.arri6);
                                    TextView dateTime = findViewById(R.id.date6);

                                    departure.setText(bookings.getDeparture());
                                    arrival.setText(bookings.getArrival());
                                    dateTime.setText(bookings.getDateTime());
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(HistoryC.this, "Error fetching trip data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        //intent home
        ImageView homelogo = findViewById(R.id.homelogoHis);
        homelogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home1 = new Intent(HistoryC.this, HomeC.class);
                startActivity(Home1);
            }
        });

        //intent profile
        ImageView profi = findViewById(R.id.profiHis);
        profi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile1 = new Intent(HistoryC.this, ProfileC.class);
                startActivity(Profile1);
            }
        });

        //intent history
        ImageView history = findViewById(R.id.histoHis);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History1 = new Intent(HistoryC.this, HistoryC.class);
                startActivity(History1);
            }
        });


        ImageView settings = findViewById(R.id.settHis);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings1 = new Intent(HistoryC.this, SettingsC.class);
                startActivity(Settings1);
            }
        });


    }
}
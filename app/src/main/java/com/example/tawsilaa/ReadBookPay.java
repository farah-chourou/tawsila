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

public class ReadBookPay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_read_book_pay);


        ImageView arrowmc = findViewById(R.id.arrowmc);
        arrowmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkout = new Intent(ReadBookPay.this, ReadMastercard.class);
                startActivity(checkout);
            }
        });

        ImageView arrowvs = findViewById(R.id.arrowvs);
        arrowvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkout = new Intent(ReadBookPay.this, ReadVisa.class);
                startActivity(checkout);
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
                                    TextView departure = findViewById(R.id.dep16);
                                    TextView arrival = findViewById(R.id.arri16);
                                    TextView dateTime = findViewById(R.id.date);

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
                            Toast.makeText(ReadBookPay.this, "Error fetching trip data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
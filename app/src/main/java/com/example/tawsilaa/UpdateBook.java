package com.example.tawsilaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_update_book);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText bookDateET = findViewById(R.id.bookDateU);
        EditText deptBookET = findViewById(R.id.deptBookU);
        EditText arriBookET = findViewById(R.id.arriBookU);
        Button update = findViewById(R.id.updbook);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        db.collection("Bookings").document(currentUserUid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Bookings bookings = documentSnapshot.toObject(Bookings.class);
                        if (bookings != null) {
                            bookDateET.setText((bookings.getDateTime()).toString());
                            deptBookET.setText(bookings.getDeparture());
                            arriBookET.setText(bookings.getArrival());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(UpdateBook.this, "Error while fetching trip data", Toast.LENGTH_SHORT).show();
                });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateTime = bookDateET.getText().toString().trim();
                String departure = deptBookET.getText().toString().trim();
                String arrival = arriBookET.getText().toString().trim();

                Map<String, Object> updatedBook = new HashMap<>();
                updatedBook.put("dateTime", dateTime);
                updatedBook.put("departure", departure);
                updatedBook.put("arrival", arrival);

                db.collection("Bookings").document(currentUserUid).update(updatedBook)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateBook.this, "Trip Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateBook.this, ReadBook.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateBook.this, "Error while updating trip", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}

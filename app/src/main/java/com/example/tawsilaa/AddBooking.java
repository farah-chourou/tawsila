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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddBooking extends AppCompatActivity {

    private static final String COLLECTION_NAME = "Bookings"; // Firestore collection name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_add_booking);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText bookdateET = findViewById(R.id.bookDate);
        EditText deptbookET = findViewById(R.id.deptBook);
        EditText arribookET = findViewById(R.id.arriBook);
        Button addBook = findViewById(R.id.addbook);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateTime = bookdateET.getText().toString().trim();
                String departure = deptbookET.getText().toString().trim();
                String arrival = arribookET.getText().toString().trim();

                if (dateTime.isEmpty() || departure.isEmpty() || arrival.isEmpty()) {
                    Toast.makeText(AddBooking.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                DocumentReference bookDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                bookDocRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Toast.makeText(AddBooking.this, "User with the same ID already exists", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> booking = new HashMap<>();
                                    booking.put("dateTime", dateTime);
                                    booking.put("departure", departure);
                                    booking.put("arrival", arrival);

                                    DocumentReference newBookDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                                    newBookDocRef.set(booking)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(AddBooking.this, "Trip booked successfully", Toast.LENGTH_SHORT).show();
                                                    bookdateET.getText().clear();
                                                    deptbookET.getText().clear();
                                                    arribookET.getText().clear();
                                                    Intent intent = new Intent(AddBooking.this, ReadBook.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(AddBooking.this, "Error booking trip", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddBooking.this, "Error checking user existence", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



    }
}

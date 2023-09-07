package com.example.tawsilaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ReadBook extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_read_book);


        FirebaseFirestore db = FirebaseFirestore.getInstance();


        Button update = findViewById(R.id.updateBook);
        update.setOnClickListener(view -> startActivity(new Intent(ReadBook.this, UpdateBook.class)));

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (currentUserUid != null) {
            db.collection("Bookings").document(currentUserUid).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Bookings bookings = documentSnapshot.toObject(Bookings.class);
                                if (bookings != null) {
                                    TextView departure = findViewById(R.id.dept);
                                    TextView arrival = findViewById(R.id.arri);
                                    TextView dateTime = findViewById(R.id.dateBook);

                                    departure.setText("Departure: " + bookings.getDeparture());
                                    arrival.setText("Arrival: " + bookings.getArrival());
                                    dateTime.setText(bookings.getDateTime());
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ReadBook.this, "Error fetching trip data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        ImageView delete = findViewById(R.id.deletebook);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (currentUserUid == null) {
                    Toast.makeText(ReadBook.this, "No user authenticated", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("Bookings").document(currentUserUid).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ReadBook.this, "Trip Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ReadBook.this, AddBooking.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ReadBook.this, "Error while deleting trip", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        final TextView mTextField = findViewById(R.id.mTextField2);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("done!");
            }
        }.start();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(ReadBook.this, Checkout.class);
                startActivity(intent);
                finish();
            }
        }, 30000);
    }
}
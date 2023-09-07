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
import android.widget.ImageView;
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

public class SettingsD extends AppCompatActivity {

    private static final String COLLECTION_NAME = "DriverComplaints"; // Firestore collection name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_settings_d);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText compET = findViewById(R.id.comp2);
        Button sendcomp = findViewById(R.id.sendcomp2);


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();

        sendcomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comp = compET.getText().toString().trim();

                DocumentReference complaintDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                complaintDocRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Toast.makeText(SettingsD.this, "Driver with the same ID already exists", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> complaint = new HashMap<>();
                                    complaint.put("Complaint", comp);
                                    DocumentReference newCompDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                                    newCompDocRef.set(complaint)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(SettingsD.this, "Complaint sent successfully", Toast.LENGTH_SHORT).show();
                                                    compET.getText().clear();
                                                    Intent intent = new Intent(SettingsD.this, SettingsD.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(SettingsD.this, "Error sending complaint", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SettingsD.this, "Error checking driver existence", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        Button logoff = findViewById(R.id.logoff2);
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(SettingsD.this, "You've been logged off. Login again", Toast.LENGTH_SHORT).show();
                Intent logoff = new Intent(SettingsD.this, MainActivity.class);
                startActivity(logoff);
            }
        });

        //intent home
        ImageView Dhomelogo1 = findViewById(R.id.homelogosd);
        Dhomelogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(SettingsD.this, HomeD.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView Dprofi1 = findViewById(R.id.profisd);
        Dprofi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(SettingsD.this, ProfileD.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView Dhistory1 = findViewById(R.id.histosd);
        Dhistory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(SettingsD.this, HistoryD.class);
                startActivity(History2);
            }
        });

        ImageView Dsettings1 = findViewById(R.id.settsd);
        Dsettings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(SettingsD.this, SettingsD.class);
                startActivity(Settings2);
            }
        });
    }
}
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

public class AddDriver extends AppCompatActivity {

    private static final String COLLECTION_NAME = "Drivers"; // Firestore collection name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_add_driver);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText fullNameET = findViewById(R.id.fullnameD);
        EditText phoneET = findViewById(R.id.phoneNumD);
        EditText emailET = findViewById(R.id.emailD);
        Button addDriver = findViewById(R.id.add5);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = fullNameET.getText().toString().trim();
                String phonenum = phoneET.getText().toString().trim();
                String email = emailET.getText().toString().trim();

                if (fullname.isEmpty() || phonenum.isEmpty() || email.isEmpty()) {
                    Toast.makeText(AddDriver.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                DocumentReference DriverDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                DriverDocRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Toast.makeText(AddDriver.this, "Driver with the same ID already exists", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> driver = new HashMap<>();
                                    driver.put("fullname", fullname);
                                    driver.put("phonenum", phonenum);
                                    driver.put("email", email);

                                    DocumentReference newDriverDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                                    newDriverDocRef.set(driver)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(AddDriver.this, "Driver profile added successfully", Toast.LENGTH_SHORT).show();
                                                    fullNameET.getText().clear();
                                                    phoneET.getText().clear();
                                                    emailET.getText().clear();
                                                    Intent intent = new Intent(AddDriver.this, ProfileD.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(AddDriver.this, "Error adding driver profile", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddDriver.this, "Error checking driver existence", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



    }
}

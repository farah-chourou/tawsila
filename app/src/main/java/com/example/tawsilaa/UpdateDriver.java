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

public class UpdateDriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_update_driver);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText fullNameET = findViewById(R.id.fullname2);
        EditText emailET = findViewById(R.id.email3);
        EditText phoneET = findViewById(R.id.phonenum2);
        Button update = findViewById(R.id.updtBtn2);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        db.collection("Drivers").document(currentUserUid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Drivers drivers = documentSnapshot.toObject(Drivers.class);
                        if (drivers != null) {
                            fullNameET.setText(drivers.getFullname());
                            emailET.setText(drivers.getEmail());
                            phoneET.setText(drivers.getPhonenum());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(UpdateDriver.this, "Error while fetching driver data", Toast.LENGTH_SHORT).show();
                });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = fullNameET.getText().toString().trim();
                String email = emailET.getText().toString().trim();
                String phonenum = phoneET.getText().toString().trim();

                Map<String, Object> updatedDriver = new HashMap<>();
                updatedDriver.put("fullname", fullname);
                updatedDriver.put("email", email);
                updatedDriver.put("phonenum", phonenum);

                db.collection("Drivers").document(currentUserUid).update(updatedDriver)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateDriver.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateDriver.this, ProfileD.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateDriver.this, "Error while updating profile", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}

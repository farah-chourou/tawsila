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

public class UpdateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_update_user);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText fullNameET = findViewById(R.id.fullname);
        EditText emailET = findViewById(R.id.email2);
        EditText phoneET = findViewById(R.id.phonenum);
        Button update = findViewById(R.id.updtBtn);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        db.collection("Users").document(currentUserUid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Users users = documentSnapshot.toObject(Users.class);
                        if (users != null) {
                            fullNameET.setText(users.getFullname());
                            emailET.setText(users.getEmail());
                            phoneET.setText(users.getPhoneNumber());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(UpdateUser.this, "Error while fetching user data", Toast.LENGTH_SHORT).show();
                });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = fullNameET.getText().toString().trim();
                String email = emailET.getText().toString().trim();
                String phonenum = phoneET.getText().toString().trim();

                Map<String, Object> updatedUser = new HashMap<>();
                updatedUser.put("Fullname", fullname);
                updatedUser.put("Email", email);
                updatedUser.put("PhoneNumber", phonenum);

                db.collection("Users").document(currentUserUid).update(updatedUser)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateUser.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateUser.this, ProfileC.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateUser.this, "Error while updating profile", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}

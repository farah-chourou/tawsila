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

public class AddCar extends AppCompatActivity {

    private static final String COLLECTION_NAME = "Car"; // Firestore collection name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_add_car);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText modelET = findViewById(R.id.model2);
        EditText sizeET = findViewById(R.id.size2);
        Button addCar = findViewById(R.id.addcar);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String model = modelET.getText().toString().trim();
                String size = sizeET.getText().toString().trim();

                if (model.isEmpty() || size.isEmpty()) {
                    Toast.makeText(AddCar.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                DocumentReference CarDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                CarDocRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Toast.makeText(AddCar.this, "Driver with the same ID already exists", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> car = new HashMap<>();
                                    car.put("model", model);
                                    car.put("size", size);

                                    DocumentReference newCarDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                                    newCarDocRef.set(car)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(AddCar.this, "Car information added successfully", Toast.LENGTH_SHORT).show();
                                                    modelET.getText().clear();
                                                    sizeET.getText().clear();
                                                    Intent intent = new Intent(AddCar.this, CarSection.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(AddCar.this, "Error adding car information", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddCar.this, "Error checking driver existence", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



    }
}

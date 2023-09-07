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

public class UpdateCar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_update_car);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText modelET = findViewById(R.id.model);
        EditText sizeET = findViewById(R.id.size);
        Button update = findViewById(R.id.updtBtn3);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        db.collection("Car").document(currentUserUid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Car car = documentSnapshot.toObject(Car.class);
                        if (car != null) {
                            modelET.setText(car.getModel());
                            sizeET.setText(car.getSize());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(UpdateCar.this, "Error while fetching car data", Toast.LENGTH_SHORT).show();
                });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String model = modelET.getText().toString().trim();
                String size = sizeET.getText().toString().trim();

                Map<String, Object> updatedCar = new HashMap<>();
                updatedCar.put("model", model);
                updatedCar.put("size", size);

                db.collection("Car").document(currentUserUid).update(updatedCar)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateCar.this, "Car Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateCar.this, CarSection.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateCar.this, "Error while updating car", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
package com.example.tawsilaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class CarSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_car_section);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Button add = findViewById(R.id.addCar);
        add.setOnClickListener(view -> startActivity(new Intent(CarSection.this, AddCar.class)));
        Button update = findViewById(R.id.updateCar);
        update.setOnClickListener(view -> startActivity(new Intent(CarSection.this, UpdateCar.class)));

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (currentUserUid != null) {
            db.collection("Car").document(currentUserUid).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Car car = documentSnapshot.toObject(Car.class);
                                if (car != null) {
                                    TextView model = findViewById(R.id.carModel);
                                    TextView size = findViewById(R.id.carSize);

                                    model.setText(car.getModel());
                                    size.setText(car.getSize());
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CarSection.this, "Error fetching car data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        ImageView delete = findViewById(R.id.delete6);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (currentUserUid == null) {
                    Toast.makeText(CarSection.this, "No driver authenticated", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("Car").document(currentUserUid).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(CarSection.this, "Car Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CarSection.this, CarSection.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CarSection.this, "Error while deleting car", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        //intent home
        ImageView homelogo1 = findViewById(R.id.homelogocs);
        homelogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(CarSection.this, HomeD.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView profi1 = findViewById(R.id.profics);
        profi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(CarSection.this, ProfileD.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView history1 = findViewById(R.id.histocs);
        history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(CarSection.this, HistoryD.class);
                startActivity(History2);
            }
        });

        ImageView settings1 = findViewById(R.id.settcs);
        settings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(CarSection.this, SettingsD.class);
                startActivity(Settings2);
            }
        });
    }

}

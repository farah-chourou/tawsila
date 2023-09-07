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

public class ProfileD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_profile_d);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Button add = findViewById(R.id.add4);
        add.setOnClickListener(view -> startActivity(new Intent(ProfileD.this, AddDriver.class)));
        Button update = findViewById(R.id.updateProfile2);
        update.setOnClickListener(view -> startActivity(new Intent(ProfileD.this, UpdateDriver.class)));

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (currentUserUid != null) {
            db.collection("Drivers").document(currentUserUid).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Drivers drivers = documentSnapshot.toObject(Drivers.class);
                                if (drivers != null) {
                                    TextView fullname = findViewById(R.id.driverFullName);
                                    TextView phonenum = findViewById(R.id.driverPhoneNumber);
                                    TextView email = findViewById(R.id.driverEmail);

                                    fullname.setText(drivers.getFullname());
                                    phonenum.setText(drivers.getPhonenum());
                                    email.setText(drivers.getEmail());
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProfileD.this, "Error fetching driver data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        ImageView delete = findViewById(R.id.delete4);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (currentUserUid == null) {
                    Toast.makeText(ProfileD.this, "No driver authenticated", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("Drivers").document(currentUserUid).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ProfileD.this, "Driver Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProfileD.this, ProfileD.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProfileD.this, "Error while deleting driver", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        //intent car section
        Button carsection = findViewById(R.id.carsection);
        carsection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent carsectionIntent = new Intent(ProfileD.this, CarSection.class);
                startActivity(carsectionIntent);
            }
        });

        //intent home
        ImageView homelogo1 = findViewById(R.id.homelogopd);
        homelogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(ProfileD.this, HomeD.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView profi1 = findViewById(R.id.profipd);
        profi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(ProfileD.this, ProfileD.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView history1 = findViewById(R.id.histopd);
        history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(ProfileD.this, HistoryD.class);
                startActivity(History2);
            }
        });

        ImageView settings1 = findViewById(R.id.settpd);
        settings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(ProfileD.this, SettingsD.class);
                startActivity(Settings2);
            }
        });
    }

}

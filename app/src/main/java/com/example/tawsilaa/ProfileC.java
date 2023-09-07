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

public class ProfileC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_profile_c);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Button add = findViewById(R.id.add2);
        add.setOnClickListener(view -> startActivity(new Intent(ProfileC.this, AddUser.class)));
        Button update = findViewById(R.id.updateProfile);
        update.setOnClickListener(view -> startActivity(new Intent(ProfileC.this, UpdateUser.class)));

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (currentUserUid != null) {
            db.collection("Users").document(currentUserUid).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Users users = documentSnapshot.toObject(Users.class);
                                if (users != null) {
                                    TextView fullname = findViewById(R.id.userFullName);
                                    TextView phonenum = findViewById(R.id.userPhoneNumber);
                                    TextView email = findViewById(R.id.userEmail);

                                    fullname.setText(users.getFullname());
                                    phonenum.setText(users.getPhoneNumber());
                                    email.setText(users.getEmail());
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProfileC.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        ImageView delete = findViewById(R.id.delete3);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (currentUserUid == null) {
                    Toast.makeText(ProfileC.this, "No user authenticated", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("Users").document(currentUserUid).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ProfileC.this, "User Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProfileC.this, ProfileC.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProfileC.this, "Error while deleting user", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        //intent home
        ImageView homelogo1 = findViewById(R.id.homelogopc);
        homelogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home2 = new Intent(ProfileC.this, HomeC.class);
                startActivity(Home2);
            }
        });

        //intent profile
        ImageView profi1 = findViewById(R.id.profipc);
        profi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile2 = new Intent(ProfileC.this, ProfileC.class);
                startActivity(Profile2);
            }
        });

        //intent history
        ImageView history1 = findViewById(R.id.histopc);
        history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent History2 = new Intent(ProfileC.this, HistoryC.class);
                startActivity(History2);
            }
        });

        ImageView settings1 = findViewById(R.id.settpc);
        settings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings2 = new Intent(ProfileC.this, SettingsC.class);
                startActivity(Settings2);
            }
        });
    }

}

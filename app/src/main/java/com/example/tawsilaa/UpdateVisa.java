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

public class UpdateVisa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_update_visa);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText paydateET = findViewById(R.id.paydateU);
        EditText accountholderET = findViewById(R.id.accountholderU);
        EditText accountnumET = findViewById(R.id.accountnumU);
        EditText cvvET = findViewById(R.id.cvvU);
        EditText expirationET = findViewById(R.id.expirationU);
        Button update = findViewById(R.id.update);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        db.collection("Visa").document(currentUserUid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Visa visa = documentSnapshot.toObject(Visa.class);
                        if (visa != null) {
                            paydateET.setText(visa.getDate());
                            accountholderET.setText(visa.getName());
                            accountnumET.setText(visa.getAccNumb());
                            cvvET.setText(visa.getCVV());
                            expirationET.setText(visa.getExpDate());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(UpdateVisa.this, "Error while fetching card data", Toast.LENGTH_SHORT).show();
                });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String paydate = paydateET.getText().toString().trim();
                String name = accountholderET.getText().toString().trim();
                String accnum = accountnumET.getText().toString().trim();
                String cvv = cvvET.getText().toString().trim();
                String expdate = expirationET.getText().toString().trim();

                Map<String, Object> updatedVisa = new HashMap<>();
                updatedVisa.put("PaymentDate", paydate);
                updatedVisa.put("AccountHolder", name);
                updatedVisa.put("AccountNumber", accnum);
                updatedVisa.put("Cvv", cvv);
                updatedVisa.put("Expiration", expdate);

                db.collection("Visa").document(currentUserUid).update(updatedVisa)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateVisa.this, "Payment Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateVisa.this, ReadVisa.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateVisa.this, "Error while updating payment", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
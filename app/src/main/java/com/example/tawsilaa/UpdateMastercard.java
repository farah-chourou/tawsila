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

public class UpdateMastercard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_update_mastercard);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText paydateET = findViewById(R.id.paydateU2);
        EditText accountholderET = findViewById(R.id.accountholderU2);
        EditText accountnumET = findViewById(R.id.accountnumU2);
        EditText cvcET = findViewById(R.id.cvcU2);
        EditText expirationET = findViewById(R.id.expirationU2);
        Button update = findViewById(R.id.update2);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        db.collection("Mastercard").document(currentUserUid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Mastercard mastercard = documentSnapshot.toObject(Mastercard.class);
                        if (mastercard != null) {
                            paydateET.setText(mastercard.getDate());
                            accountholderET.setText(mastercard.getName());
                            accountnumET.setText(mastercard.getAccNumb());
                            cvcET.setText(mastercard.getCVC());
                            expirationET.setText(mastercard.getExpDate());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(UpdateMastercard.this, "Error while fetching card data", Toast.LENGTH_SHORT).show();
                });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = paydateET.getText().toString().trim();
                String name = accountholderET.getText().toString().trim();
                String AccNumb = accountnumET.getText().toString().trim();
                String CVC = cvcET.getText().toString().trim();
                String ExpDate = expirationET.getText().toString().trim();

                Map<String, Object> updatedMastercard = new HashMap<>();
                updatedMastercard.put("Payment Date", date);
                updatedMastercard.put("Account Holder", name);
                updatedMastercard.put("Account Number", AccNumb);
                updatedMastercard.put("Cvc", CVC);
                updatedMastercard.put("Expiration", ExpDate);

                db.collection("Mastercard").document(currentUserUid).update(updatedMastercard)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateMastercard.this, "Payment Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateMastercard.this, ReadMastercard.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateMastercard.this, "Error while updating payment", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
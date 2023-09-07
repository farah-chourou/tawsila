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
import android.widget.ImageView;
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

public class AddVisa extends AppCompatActivity {

    private static final String COLLECTION_NAME = "Visa"; // Firestore collection name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_add_visa);

        ImageView backarrow = findViewById(R.id.backarrow3);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkout = new Intent(AddVisa.this, Checkout.class);
                startActivity(checkout);
            }
        });


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText paydateET = findViewById(R.id.paydatev);
        EditText accountholderET = findViewById(R.id.accountholderv);
        EditText accountnumET = findViewById(R.id.accountnumv);
        EditText cvvET = findViewById(R.id.cvvv);
        EditText expirationET = findViewById(R.id.expirationv);
        Button addPayment = findViewById(R.id.btnpayv);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = paydateET.getText().toString().trim();
                String name = accountholderET.getText().toString().trim();
                String AccNumb = accountnumET.getText().toString().trim();
                String CVV = cvvET.getText().toString().trim();
                String ExpDate = expirationET.getText().toString().trim();

                if (date.isEmpty() || name.isEmpty() || AccNumb.isEmpty() ||
                        CVV.isEmpty() || ExpDate.isEmpty()) {
                    Toast.makeText(AddVisa.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }



                DocumentReference payDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                payDocRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Toast.makeText(AddVisa.this, "Payment already exists", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> payment = new HashMap<>();
                                    payment.put("PaymentDate", date);
                                    payment.put("AccountHolder", name);
                                    payment.put("AccountNumber", AccNumb);
                                    payment.put("Cvv", CVV);
                                    payment.put("Expiration", ExpDate);

                                    DocumentReference newPaymentDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                                    newPaymentDocRef.set(payment)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(AddVisa.this, "Payment added successfully", Toast.LENGTH_SHORT).show();
                                                    paydateET.getText().clear();
                                                    accountholderET.getText().clear();
                                                    accountnumET.getText().clear();
                                                    cvvET.getText().clear();
                                                    expirationET.getText().clear();
                                                    Intent intent = new Intent(AddVisa.this, HomeC.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(AddVisa.this, "Error adding payment", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddVisa.this, "Error checking payment existence", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
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

public class AddMastercard extends AppCompatActivity {

    private static final String COLLECTION_NAME = "Mastercard"; // Firestore collection name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_add_mastercard);

        ImageView backarrow = findViewById(R.id.backarrow4);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkout = new Intent(AddMastercard.this, Checkout.class);
                startActivity(checkout);
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText paydateET = findViewById(R.id.paydatem);
        EditText accountholderET = findViewById(R.id.accountholderm);
        EditText accountnumET = findViewById(R.id.accountnumm);
        EditText cvcET = findViewById(R.id.cvcm);
        EditText expirationET = findViewById(R.id.expirationm);
        Button addPayment = findViewById(R.id.btnpaym);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = paydateET.getText().toString().trim();
                String name = accountholderET.getText().toString().trim();
                String AccNumb = accountnumET.getText().toString().trim();
                String CVC = cvcET.getText().toString().trim();
                String ExpDate = expirationET.getText().toString().trim();

                if (date.isEmpty() || name.isEmpty() || AccNumb.isEmpty() ||
                        CVC.isEmpty() || ExpDate.isEmpty()) {
                    Toast.makeText(AddMastercard.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }



                DocumentReference payDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                payDocRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Toast.makeText(AddMastercard.this, "Payment already exists", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> paymentmastercard = new HashMap<>();
                                    paymentmastercard.put("PaymentDate", date);
                                    paymentmastercard.put("AccountHolder", name);
                                    paymentmastercard.put("AccountNumber", AccNumb);
                                    paymentmastercard.put("Cvc", CVC);
                                    paymentmastercard.put("Expiration", ExpDate);

                                    DocumentReference newPaymentDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                                    newPaymentDocRef.set(paymentmastercard)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(AddMastercard.this, "Payment added successfully", Toast.LENGTH_SHORT).show();
                                                    paydateET.getText().clear();
                                                    accountholderET.getText().clear();
                                                    accountnumET.getText().clear();
                                                    cvcET.getText().clear();
                                                    expirationET.getText().clear();
                                                    Intent intent = new Intent(AddMastercard.this, HomeC.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(AddMastercard.this, "Error adding payment", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddMastercard.this, "Error checking payment existence", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
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

public class ReadVisa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_read_visa);

        ImageView backarrow = findViewById(R.id.backarrow5);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkout = new Intent(ReadVisa.this, HistoryC.class);
                startActivity(checkout);
            }
        });


        double seconds = 30;
        double pricePerSecond = 0.7;
        double initprice = 0.9;
        double result = (seconds * pricePerSecond) + initprice;
        String resultText = result + " DT";

        // Display the result in a text view
        TextView pricetxt1 = findViewById(R.id.pricetxt4);
        pricetxt1.setText(resultText);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Button update = findViewById(R.id.updatePay);
        update.setOnClickListener(view -> startActivity(new Intent(ReadVisa.this, UpdateVisa.class)));

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (currentUserUid != null) {
            db.collection("Visa").document(currentUserUid).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Visa payment = documentSnapshot.toObject(Visa.class);
                                if (payment != null) {
                                    TextView AccNumb = findViewById(R.id.cnum1);
                                    TextView name = findViewById(R.id.name1);
                                    TextView date = findViewById(R.id.paymdate);
                                    TextView ExpDate = findViewById(R.id.expirationdate1);

                                    AccNumb.setText(payment.getAccNumb());
                                    name.setText(payment.getName());
                                    ExpDate.setText(payment.getExpDate());
                                    date.setText(payment.getDate());
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ReadVisa.this, "Error fetching card data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        ImageView delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (currentUserUid == null) {
                    Toast.makeText(ReadVisa.this, "No user authenticated", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("Visa").document(currentUserUid).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ReadVisa.this, "Card Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ReadVisa.this, AddVisa.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ReadVisa.this, "Error while deleting card", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
package com.lec.customerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lec.customerapp.mdel.Product;

public class CheckOutActivity extends AppCompatActivity {

    EditText name,number,date,cvv;
    Button order;
    private String pro_id,pro_name,pro_price;
    FirebaseAuth fAuth;
    private String uid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getUid();
        Bundle bundle = getIntent().getExtras();
        pro_id = bundle.getString("pro_id");
        pro_name = bundle.getString("pro_name");
        pro_price = bundle.getString("pro_price");

        name=findViewById(R.id.card_name);
        number=findViewById(R.id.card_number);
        date=findViewById(R.id.card_date);
        cvv=findViewById(R.id.card_cvv);

        order=findViewById(R.id.order_place);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cName=name.getText().toString().trim();
                String cNumber=number.getText().toString().trim();
                String cDate=date.getText().toString().trim();
                String cCVV=cvv.getText().toString().trim();

                Product product = new Product();
                product.setId(pro_id);
                product.setName(pro_name);
                product.setPrice(pro_price);
                product.setState("order done");
                product.setUserId(uid);

                final ProgressDialog progressDialog = new ProgressDialog(CheckOutActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                db.collection("product").add(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CheckOutActivity.this, "Order placed Successful!", Toast.LENGTH_SHORT).show();
                        finishActivity(102);

                        Intent intent = new Intent(CheckOutActivity.this, ProductActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CheckOutActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
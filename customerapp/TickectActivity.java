package com.lec.customerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lec.customerapp.mdel.Tickect;

public class TickectActivity extends AppCompatActivity {

    EditText title,body;
    Spinner type;
    String type_text;
    Button save;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseAuth fAuth;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickect);
        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getUid();
        type=findViewById(R.id.type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        type_text="Information Request";
                    case 1:
                        type_text="Complain";
                    case 2:
                        type_text="Compliment";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        title=findViewById(R.id.title);
        body=findViewById(R.id.body);

        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tTitle=title.getText().toString().trim();
                String tBody=body.getText().toString().trim();

                Tickect tickect = new Tickect();
                tickect.setType(type_text);
                tickect.setTitle(tTitle);
                tickect.setBody(tBody);
                tickect.setUserId(uid);
                tickect.setUserName(ProductActivity.user_name);

                final ProgressDialog progressDialog = new ProgressDialog(TickectActivity.this);
                progressDialog.setMessage("Saving Your Ticket...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                db.collection("Ticket").add(tickect).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(TickectActivity.this, "saved Your Ticket", Toast.LENGTH_SHORT).show();
                        finishActivity(102);

                        Intent intent = new Intent(TickectActivity.this, ProductActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TickectActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}
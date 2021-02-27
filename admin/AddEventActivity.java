package com.lec.rider;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lec.rider.model.Event;

public class AddEventActivity extends AppCompatActivity {

    public static Button addEvent_1,addEvent_2;
    EditText title;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        addEvent_1=findViewById(R.id.add_event_1);
        addEvent_2=findViewById(R.id.add_event_2);
        title=findViewById(R.id.event_name);

        addEvent_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(AddEventActivity.this);
                progressDialog.setMessage("Adding Event...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Event event = new Event();
                event.setName(title.getText().toString().trim());

                db.collection("event").add(event).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddEventActivity.this, "Event Added ", Toast.LENGTH_SHORT).show();
                        finishActivity(102);


                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddEventActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        addEvent_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(AddEventActivity.this);
                progressDialog.setMessage("Adding Event...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Event event = new Event();
                event.setName(title.getText().toString().trim());
                event.setLat(MapsFragment.latitude1);
                event.setLog(MapsFragment.longitude1);

                db.collection("event").add(event).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddEventActivity.this, "Event Added ", Toast.LENGTH_SHORT).show();
                        finishActivity(102);


                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddEventActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}
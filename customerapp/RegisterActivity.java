package com.lec.customerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lec.customerapp.mdel.Customer;
import com.squareup.picasso.Picasso;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, phone, nic;
    String text_gender;
    Spinner gender;
    ImageView profile;
    Button register_btn,chooseProfile;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int File_CHOOSER_RESULT_CODE = 100;
    private Uri customerPhoto;
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.mobile);
        nic = findViewById(R.id.nic);
        profile=findViewById(R.id.imageView);
        chooseProfile=findViewById(R.id.choose);
        chooseProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fileChooser = new Intent();
                fileChooser.setAction(Intent.ACTION_GET_CONTENT);
                fileChooser.setType("image/*");
                startActivityForResult(Intent.createChooser(fileChooser, "Select Driver photo"), File_CHOOSER_RESULT_CODE);
            }
        });

        gender=findViewById(R.id.gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        text_gender="Male";
                    case 1:
                        text_gender="Female";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Bundle bundle = getIntent().getExtras();
        String user_name = bundle.getString("auth_name");
        String user_email = bundle.getString("auth_email");
        final String user_UID = bundle.getString("auth_ID");
        name.setText(user_name);
        email.setText(user_email);

        register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = name.getText().toString().trim();
                String uEmail = email.getText().toString().trim();
                String uPhone = phone.getText().toString().trim();
                String uNic=nic.getText().toString().trim();



                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("Registering...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                final Customer customer = new Customer();
                customer.setName(uName);
                customer.setEmail(uEmail);
                customer.setGender(text_gender+" ");
                customer.setGoogleUId(user_UID);
                customer.setNic(uNic);
                customer.setPhone(uPhone);
                customer.setState("1");
                String driverImagePath = "DriverImage_" + nic.getText().toString() + ".jpg";
                mStorageRef.child(driverImagePath).putFile(customerPhoto)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(RegisterActivity.this, "driver photo done", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        String imageUrl = task.getResult().toString();
                        customer.setProfile(imageUrl);
                    }
                });

                db.collection("customers").add(customer).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        finishActivity(102);

                        Intent intent = new Intent(RegisterActivity.this, ProductActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == File_CHOOSER_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                customerPhoto = data.getData();
                Picasso.with(RegisterActivity.this).load(customerPhoto).into(profile);
            }
        }


    }
}
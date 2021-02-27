package com.lec.customerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.lec.customerapp.mdel.Customer;
import com.lec.customerapp.mdel.Job;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActvity extends AppCompatActivity {
    TextView name, email, price, distance,status;
   public static Button logout,job;
    private Fragment fragmentMap;
    private LatLng customerLocation;
    private LatLng dropLocation;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FragmentManager fm=getSupportFragmentManager();
    private String user_name;
    private String user_email;
    private String user_id;
    private String customerDocId;

    String riderAppStatus = "";
    public CollectionReference JobCollection;
    public CollectionReference customerCollection;
    private Customer currentCustomer;
    private Job currentJobObject;
    private String currentJobDocId;
    public DocumentReference jobReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_actvity);
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        price = findViewById(R.id.price);
        distance = findViewById(R.id.distance);
        job = findViewById(R.id.job);
        status = findViewById(R.id.status);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Job newJob = new Job();
                newJob.setCusterName(user.getDisplayName());

                newJob.setDurationStringAttr(distance.getText().toString());
                newJob.setStartLocation_lat(customerLocation.latitude);
                newJob.setStartLocation_log(customerLocation.longitude);
                newJob.setEndLocation_lat(dropLocation.latitude);
                newJob.setEndLocation_log(dropLocation.longitude);
                newJob.setEstimationPrice(price.getText().toString());
                newJob.setJobCreatedAt(new Date());
                newJob.setStatus("Job Placed");
                newJob.setCurrentCustomer_lat(customerLocation.latitude);
                newJob.setCurrentCustomer_log(customerLocation.longitude);

                String pattern1 = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern1);
                String date1 = simpleDateFormat.format(new Date());
                newJob.setDate(date1);
                newJob.setCusterEmail(user_email);
                newJob.setCustomer_phone("phone");
                newJob.setCustomerDocId(customerDocId);

                db.collection("Job").add(newJob).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(HomeActvity.this, "Success!", Toast.LENGTH_SHORT).show();
                        // job.setText("Cancel");
                        job.setEnabled(false);

                        jobEngine(documentReference);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        job.setText("PLACE A JOB");
                    }
                });
            }
        });
    }
    private void jobEngine(final DocumentReference jobReference) {
        this.jobReference=jobReference;
        jobReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Job job1 = value.toObject(Job.class);

                if(job1.getStatus().equals("Job Placed")){
                    status.setText("Job Placed");
                    Toast.makeText(HomeActvity.this, "Job Placed", Toast.LENGTH_SHORT).show();
                }else if(job1.getStatus().equals("Rider Accepted")){
                    status.setText("Rider Accepted");
                    double currentRider_lat = job1.getCurrentRider_lat();
                    double currentCustomer_log = job1.getCurrentCustomer_log();

                    ((MapsFragment)fragmentMap).showRiderLocation(currentRider_lat,currentCustomer_log);

                    Toast.makeText(HomeActvity.this, "Rider Accepted"+currentRider_lat+"-"+currentCustomer_log, Toast.LENGTH_SHORT).show();

                }else if(job1.getStatus().equals("Pick Up")){
                    Toast.makeText(HomeActvity.this, "Customer Pick up ok..", Toast.LENGTH_SHORT).show();
                    status.setText("Rider pickUp");
                }else if(job1.getStatus().equals("Drop off")){
                    Toast.makeText(HomeActvity.this, "Customer drop off Successful: Tank You!", Toast.LENGTH_SHORT).show();
                    job.setEnabled(true);
                    status.setText("Rider Drop Off");
                    startActivity(new Intent(HomeActvity.this,MainActivity.class));
                }

            }
        });
    }

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...


                        Intent intent = new Intent(HomeActvity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                        finish();

                    }
                });
        // [END auth_fui_signout]
    }
    public void setEstimateValue(double estimatedTotalPrice, String d) {
        price.setText("RS." + estimatedTotalPrice);
        distance.setText(d + "");
    }
    public void setJobLatlang(LatLng customerLocation, LatLng dropLocation) {
        this.customerLocation = customerLocation;
        this.dropLocation = dropLocation;
    }
    public DocumentReference getJobReference() {
        return jobReference;
    }
}
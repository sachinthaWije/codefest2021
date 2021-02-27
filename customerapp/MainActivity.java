package com.lec.customerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.lec.customerapp.mdel.Customer;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    SignInButton signIn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn=findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSignInIntent();
            }
        });

    }
    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_create_intent]
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
            if(resultCode==RESULT_OK){
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                final String email = currentUser.getEmail();
                final String uid = currentUser.getUid();
                final String displayName = currentUser.getDisplayName();

                db.collection("customers").whereEqualTo("email",email).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                List<Customer> customerList = queryDocumentSnapshots.toObjects(Customer.class);
                                if(customerList.size()>0){
                                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                                    String id = documentSnapshot.getId();
                                    Customer customer = customerList.get(0);
                                    Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                                    intent.putExtra("customerDocId",id);
                                    intent.putExtra("auth_name",customer.getName()+"");
                                    intent.putExtra("auth_email",email+"");
                                    intent.putExtra("auth_ID",uid+"");
                                    intent.putExtra("user_phone",customer.getPhone()+"");

                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                }else{
                                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                                    intent.putExtra("auth_name",displayName+"");
                                    intent.putExtra("auth_email",email+"");
                                    intent.putExtra("auth_ID",uid+"");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }else{
                Toast.makeText(this, "Sign in Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
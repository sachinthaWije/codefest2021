package com.lec.customerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BuyProduct extends AppCompatActivity {
    TextView des;
    Button buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);
        des=findViewById(R.id.productDescription);
        des.setText("simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic");


        buy=findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyProduct.this,CheckOutActivity.class);
                intent.putExtra("pro_id","card2");
                intent.putExtra("pro_name","Black bag");
                intent.putExtra("pro_price","Rs.1500");
                startActivity(intent);
            }
        });
    }
}
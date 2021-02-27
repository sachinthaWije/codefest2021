package com.lec.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    Button view_ticket,add_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        view_ticket=findViewById(R.id.view_ticket);
        add_news=findViewById(R.id.add_news);

        view_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,TicketViewActivity.class));
            }
        });

        add_news=findViewById(R.id.add_news);
        add_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,AddEventActivity.class));
            }
        });
    }
}
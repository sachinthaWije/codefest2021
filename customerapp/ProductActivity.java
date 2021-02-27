package com.lec.customerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    CardView card2;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    public static String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        card2 = findViewById(R.id.card2);

        Bundle bundle = getIntent().getExtras();
        user_name = bundle.getString("auth_name");


        //        nav bar --------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

//        toolbar----------------------
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        //        -------------------------------------

        card2.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_tickets:
                Intent intent = new Intent(this, TickectActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_news:
                Intent intent2 = new Intent(this, NewViewActivity.class);
                startActivity(intent2);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.card2:
                i = new Intent(this, BuyProduct.class);
                startActivity(i);
                break;
        }
    }
}
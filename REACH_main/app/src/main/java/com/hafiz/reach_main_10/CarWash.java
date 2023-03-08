package com.hafiz.reach_main_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CarWash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash);

        Button b = findViewById(R.id.back3);
        Button b1 = findViewById(R.id.Confirm);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CarWash.this , homepage_activity.class);
                startActivity(in);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarWash.this,CarwashConfirmActivity.class));
            }
        });

        //MenuBar


        ImageView home = findViewById(R.id.Home_home);
        ImageView prof = findViewById(R.id.Home_prof);
        ImageView cart = findViewById(R.id.home_cart);
        ImageView supp = findViewById(R.id.Home_Supp);
        ImageView sett = findViewById(R.id.Home_sett);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarWash.this,homepage_activity.class));

            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarWash.this,profile_activity.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarWash.this,CartListActivity.class));
            }
        });
        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarWash.this,Support_activity.class));
            }
        });
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarWash.this,Settings_activity.class));
            }
        });

    }

}
package com.hafiz.reach_main_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class car_repairing_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_repairing);

        Button b = findViewById(R.id.back3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(car_repairing_activity.this, homepage_activity.class);
                startActivity(in);
            }
        });
        Button b1 = findViewById(R.id.ConfirmButton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(car_repairing_activity.this,CarrepairConfirmActivity.class));
            }
        });
    }
}
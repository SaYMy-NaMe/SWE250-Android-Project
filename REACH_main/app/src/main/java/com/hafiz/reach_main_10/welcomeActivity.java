package com.hafiz.reach_main_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button b = findViewById(R.id.nextbutton); // Connect The Next Button in The Welcome Page

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent in = new Intent(welcomeActivity.this , login_activity.class); // Go to Login Page
//                startActivity(in);
                Intent in = new Intent(welcomeActivity.this , SelectionCustomerOrWorkerActivity.class); // Go to Login Page
                startActivity(in);
            }
        });

    }

}
package com.hafiz.reach_main_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionCustomerOrWorkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_customer_or_worker);


        Button workr = findViewById(R.id.Workerbutton);
        Button cutomr= findViewById(R.id.customerbutton);

        workr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionCustomerOrWorkerActivity.this,LoginForWorkerActivity.class));
            }
        });
        cutomr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionCustomerOrWorkerActivity.this, login_activity_customer.class));
            }
        });

    }
}
package com.hafiz.reach_main_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class JobDetailActivity extends AppCompatActivity {

    TextView JobDetTitle,JobDetFair,JobDetInArea,JobDetNid,JobDetAddr,JobDetCnt;
    ImageView JobDetImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        JobDetTitle = findViewById(R.id.JobDetTitle);
        JobDetFair = findViewById(R.id.JobDetFair);
        JobDetInArea = findViewById(R.id.JobDetInArea);
        JobDetNid = findViewById(R.id.JobDetNid);
        JobDetAddr = findViewById(R.id.JobDetAddr);
        JobDetCnt = findViewById(R.id.JobDetCnt);

        JobDetImage = findViewById(R.id.JobDetImage);

        Picasso.get().load(getIntent().getStringExtra("JobDetImage"))
                .placeholder(R.drawable.ashraf)
                .into(JobDetImage);

        JobDetTitle.setText(getIntent().getStringExtra("JobDetTitle"));
        JobDetFair.setText(getIntent().getStringExtra("JobDetFair"));
        JobDetInArea.setText(getIntent().getStringExtra("JobDetInArea"));
        JobDetNid.setText(getIntent().getStringExtra("JobDetNid"));
        JobDetAddr.setText(getIntent().getStringExtra("JobDetAddr"));
        JobDetCnt.setText(getIntent().getStringExtra("JobDetCnt"));





        //button

        Button b = findViewById(R.id.back3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(JobDetailActivity.this, homepage_activity.class);
                startActivity(in);
            }
        });
        Button b1 = findViewById(R.id.Confirm);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobDetailActivity.this,CarrepairConfirmActivity.class));
            }
        });



    }
}
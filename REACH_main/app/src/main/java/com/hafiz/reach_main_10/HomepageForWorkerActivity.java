package com.hafiz.reach_main_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomepageForWorkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_for_worker);

        Button workerProfile = findViewById(R.id.worker_SeeProfile);
        Button workerMessage = findViewById(R.id.worker_message);
        Button workerPostJob = findViewById(R.id.worker_postJob);
        Button workerDeleteJob = findViewById(R.id.worker_postJob);
        Button workerEditProfile = findViewById(R.id.worker_editProfile);

        workerPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomepageForWorkerActivity.this,WorkerPostJobActivity.class));

            }
        });

    }
}
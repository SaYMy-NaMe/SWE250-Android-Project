package com.hafiz.reach_main_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaintAndDryWallRepairConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_and_dry_wall_repair_confirm);

        Button b = findViewById(R.id.back4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaintAndDryWallRepairConfirmActivity.this,PaintAndDrywallRepairerActivity.class));
            }
        });
    }
}
package com.hafiz.reach_main_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.logging.Logger;

public class homepage_activity extends AppCompatActivity {


    // For Option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // for Menu Option

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.popup_menu,menu);

        // for Search Option
//        MenuItem menuItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Search Here");

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.profile)
        {
            // go to profile
            startActivity(new Intent(homepage_activity.this, profile_activity.class));

        }
        if(id == R.id.aboutUs)
        {
            startActivity(new Intent(homepage_activity.this, aboutUs_activity.class));
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.activity_homepage);




        ImageView im1 = findViewById(R.id.plunmbing);





        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(homepage_activity.this , plumbing_activity.class);
                startActivity(in);
            }
        });
                ImageView im2 = findViewById(R.id.carWashing);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =  new Intent(homepage_activity.this , CarWash.class);
                startActivity(in);
            }
        });

        ImageView im3 = findViewById(R.id.carRepairing);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(homepage_activity.this , car_repairing_activity.class);
                startActivity(in);
            }
        });


    }
}
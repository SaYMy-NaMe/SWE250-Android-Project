package com.hafiz.reach_main_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hafiz.reach_main_10.adapters.GigAdapter;
import com.hafiz.reach_main_10.classes.RecyclerItemClickListener;
import com.hafiz.reach_main_10.model.GigModel;

import java.util.ArrayList;
import java.util.logging.Logger;

public class homepage_activity extends AppCompatActivity {

    //Firebase
    private FirebaseUser user;
    private DatabaseReference reference;
    private  String userID;

    RecyclerView recyclerView ;
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
//        if(id == R.id.profile)
//        {
//            // go to profile
//            startActivity(new Intent(homepage_activity.this, profile_activity.class));
//
//        }
        if(id == R.id.aboutUs)
        {
            startActivity(new Intent(homepage_activity.this, aboutUs_activity.class));
        }
        if(id == R.id.history)
        {
            startActivity(new Intent(homepage_activity.this, OrderHistoryActivity.class));
        }
        if(id == R.id.logout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(homepage_activity.this, welcomeActivity.class));
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.activity_homepage);


        //Get Data from Firebase for initial Greetings with UsserName!!

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        final TextView greeting = (TextView) findViewById(R.id.text1);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null) {
                    String fullname = userProfile.fullname;
                    greeting.setText("Hi!"+fullname + "! , What Are You Looking For?");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(homepage_activity.this,"Something Wrong",Toast.LENGTH_LONG).show();
            }
        });


        // NavigationBar

    ImageView home = findViewById(R.id.Home_home);
    ImageView prof = findViewById(R.id.Home_prof);
    ImageView cart = findViewById(R.id.home_cart);
    ImageView supp = findViewById(R.id.Home_Supp);
    ImageView sett = findViewById(R.id.Home_sett);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage_activity.this,homepage_activity.class));

            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage_activity.this,profile_activity.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage_activity.this,CartListActivity.class));
            }
        });
        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage_activity.this,Support_activity.class));
            }
        });
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage_activity.this,Settings_activity.class));
            }
        });

    //RecycleView

        recyclerView = findViewById(R.id.recycleView);
        ArrayList<GigModel> list = new ArrayList<>();
        list.add(new GigModel(R.drawable.car_repairing,"Car Repairing"));
        list.add(new GigModel(R.drawable.car_washer,"Car Washer"));
        list.add(new GigModel(R.drawable.delivery_man,"Delivery Man"));
        list.add(new GigModel(R.drawable.electrician,"Electrician"));
        list.add(new GigModel(R.drawable.garden3,"Gardener"));
        list.add(new GigModel(R.drawable.interior,"Interior Designer"));
        list.add(new GigModel(R.drawable.pet3,"Pet Trainer"));
        list.add(new GigModel(R.drawable.plumber,"Plumbing "));
        list.add(new GigModel(R.drawable.paint2,"Painter"));


        GigAdapter adapter = new GigAdapter(list,this);
        recyclerView.setAdapter(adapter);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
//        recyclerView.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);

        //RecycleItemclickListener

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener
                (this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        switch (position)
                        {
                            case 0:
                                Intent intent = new Intent(homepage_activity.this,car_repairing_activity.class);
                                startActivity(intent);
                                break;
                            case 1:

                                startActivity(new Intent(homepage_activity.this,CarWash.class));
                                break;
                            case 2:

                                startActivity(new Intent(homepage_activity.this,DeliveryMan.class));
                                break;
                            case 3:
                                Intent intent1 = new Intent(homepage_activity.this,ElectricianActivity.class);
                                startActivity(intent1);
                                break;
                            case 4:

                                startActivity(new Intent(homepage_activity.this,InteriorDecoratorActivity.class));
                                break;
                            case 5:

                                startActivity(new Intent(homepage_activity.this,PetTrainerActivity.class));
                                break;
                            case 6:
                                Intent intent2 = new Intent(homepage_activity.this,PetTrainerActivity.class);
                                startActivity(intent2);
                                break;
                            case 7:

                                startActivity(new Intent(homepage_activity.this,plumbing_activity.class));
                                break;
                            case 8:

                                startActivity(new Intent(homepage_activity.this,PaintAndDrywallRepairerActivity.class));
                                break;

                            default:


                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }


                ));
        
        //Recycle View
    }
}
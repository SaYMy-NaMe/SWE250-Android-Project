package com.hafiz.reach_main_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile_activity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private  String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button b = findViewById(R.id.back2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile_activity.this, homepage_activity.class));
            }
        });



        //Get Data from Firebase
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greeting = (TextView) findViewById(R.id.Pr_greeting);
        final TextView name = (TextView) findViewById(R.id.Pr_name);
        final TextView addr = (TextView) findViewById(R.id.Pr_Addr);
        final TextView nID = (TextView) findViewById(R.id.Pr_Nid);
        final TextView contact = (TextView) findViewById(R.id.Pr_Cont);
        final TextView eMail = (TextView) findViewById(R.id.Pr_email);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 User userProfile = snapshot.getValue(User.class);

                 if(userProfile != null) {
                     String fullname = userProfile.fullname;
                     String NID = userProfile.Nid;
                     String adr = userProfile.address;
                     String cont = userProfile.contact;
                     String email = userProfile.email;

                     greeting.setText("   Hi!,"+fullname + "!");
                     name.setText(" Name : "+fullname);
                     addr.setText(" Address : "+adr);
                     nID.setText(" NID No : "+NID);
                     contact.setText(" Contact No : "+cont);
                     eMail.setText(" Email Address : "+email);
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile_activity.this,"Something Wrong",Toast.LENGTH_LONG).show();
            }
        });

    }
}
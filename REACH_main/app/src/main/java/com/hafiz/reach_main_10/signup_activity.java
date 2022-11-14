package com.hafiz.reach_main_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class signup_activity extends AppCompatActivity {

    private EditText editFUllname, editNID, editAddress, editCont, editEmail, editPass;
    Button b ;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        b = findViewById(R.id.signup);
        editFUllname = findViewById(R.id.fullname);
        editNID = findViewById(R.id.nid);
        editAddress = findViewById(R.id.address);
        editCont = findViewById(R.id.number);
        editEmail = findViewById(R.id.email);
        editPass = findViewById(R.id.pass);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerListener();

            }
        });


    }

    private void registerListener() {
        String fullname = editFUllname.getText().toString().trim();
        String nID = editNID.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        String contact = editCont.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String pass = editPass.getText().toString().trim();

        if(fullname.isEmpty())
        {
            editFUllname.setError("Full Name is Required");
            editFUllname.requestFocus();
            return;
        }
        if(nID.isEmpty())
        {
            editFUllname.setError("NID number is Required");
            editNID.requestFocus();
            return;
        }
        if(address.isEmpty())
        {
            editAddress.setError("Address is Required");
            editAddress.requestFocus();
            return;
        }
        if(contact.isEmpty())
        {
            editCont.setError("Contact Number is Required");
            editCont.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            editEmail.setError("Email is Required");
            editEmail.requestFocus();
            return;
        }
        if( !Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editEmail.setError("Please input a valid Email");
            editEmail.requestFocus();
            return;
        }
        if(pass.isEmpty())
        {
            editPass.setError("Password is Required (at least 6 Character)");
            editPass.requestFocus();
            return;
        }
        if(pass.length() < 6)
        {
            editPass.setError("Password Length must be 6 or more");
            editPass.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user = new User(fullname,address,nID,contact,email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(signup_activity.this, "Signup Scuccesfull",Toast.LENGTH_LONG).show();

                                            }
                                            else
                                            {
                                                Toast.makeText(signup_activity.this, "Signup Failed,Try Again",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                            startActivity(new Intent(signup_activity.this,login_activity.class));

                        }
                        else
                        {
                            Toast.makeText(signup_activity.this, "Signup Failed,Try Again",Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }
}
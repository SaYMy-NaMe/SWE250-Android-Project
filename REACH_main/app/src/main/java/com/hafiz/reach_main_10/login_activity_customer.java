package com.hafiz.reach_main_10;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_activity_customer extends AppCompatActivity {

    private TextView tb ;
    private EditText edtEmail , editPass;// Input
    private Button logIn;
    private ProgressBar pr; // Will be used Later
    private FirebaseAuth mAuth;// Firebase Reference


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);

        mAuth = FirebaseAuth.getInstance(); // Firebase Initialization
        tb = findViewById(R.id.GoToSignup);
        logIn = findViewById(R.id.lo);
        edtEmail = findViewById(R.id.emaiL);
        editPass = findViewById(R.id.pasS);




        tb.setOnClickListener(new View.OnClickListener() { // If USer do not Have account , Go to Signup Page
            @Override
            public void onClick(View view) {
                Intent in = new Intent(login_activity_customer.this , signup_activity.class);
                startActivity(in);
            }
        });


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                user_login(); // If Already Have account or After SignUp
            }
        });


    }

    private void user_login() {
        String email = edtEmail.getText().toString().trim(); // User Input Email
        String pass = editPass.getText().toString().trim();// USer input Password

        if(email.isEmpty())
        {
            edtEmail.setError("Email is Required"); // Give message to user after incorrect mail
            edtEmail.requestFocus();
            return;
        }
        if( !Patterns.EMAIL_ADDRESS.matcher(email).matches())// input format should be like an Email
        {
            edtEmail.setError("Please input a valid Email");
            edtEmail.requestFocus();
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
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(login_activity_customer.this,homepage_activity.class)); // Login Success , Advance to The Homepage
                }
                else
                {
                    Toast.makeText(login_activity_customer.this,"Failed to login , PLease Try again",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
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

import java.util.PrimitiveIterator;

public class login_activity extends AppCompatActivity {

    private TextView tb ;
    private EditText edtEmail , editPass;
    private Button logIn;
    private ProgressBar pr;
    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        tb = findViewById(R.id.GoToSignup);
        logIn = findViewById(R.id.lo);
        edtEmail = findViewById(R.id.emaiL);
        editPass = findViewById(R.id.pasS);




        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(login_activity.this , signup_activity.class);
                startActivity(in);
            }
        });


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_login();
            }
        });


    }

    private void user_login() {
        String email = edtEmail.getText().toString().trim();
        String pass = editPass.getText().toString().trim();

        if(email.isEmpty())
        {
            edtEmail.setError("Email is Required");
            edtEmail.requestFocus();
            return;
        }
        if( !Patterns.EMAIL_ADDRESS.matcher(email).matches())
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
                    startActivity(new Intent(login_activity.this,homepage.class));
                }
                else
                {
                    Toast.makeText(login_activity.this,"Failed to login , PLease Try again",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
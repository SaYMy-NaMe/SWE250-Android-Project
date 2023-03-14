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

public class signup_activity extends AppCompatActivity {

    private EditText editFUllname, editNID, editAddress, editCont, editEmail, editPass; // Variabels for Taking Input from signup Form
    Button b ;
    private FirebaseAuth mAuth; // Reference to FireBase
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance(); // Initiate Firbase Connection

        b = findViewById(R.id.signup); // Connect to Signup Button
        editFUllname = findViewById(R.id.fullname); // Connect to SignUp Form Inputs
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
        String fullname = editFUllname.getText().toString().trim(); // Get Text From the Form and Store it in The variables declared Before, Trim for Avoiding Space input
        String nID = editNID.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        String contact = editCont.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String pass = editPass.getText().toString().trim();

        if(fullname.isEmpty())
        {
            editFUllname.setError("Full Name is Required"); // Incase of Wrong Input , Give a message to the users.
            editFUllname.requestFocus();// Focus on the input
            return;
        }
        if(nID.isEmpty())// Same
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
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() { // Store the inputed data On Firebase
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user = new User(fullname,address,nID,contact,email); // Passing all the Data as USER OBject on Firebase
                            FirebaseDatabase.getInstance().getReference("Users") // Getting The Storing Address
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() { // Passing 'user' on SetValue() Method
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(!task.isSuccessful())
                                            {
                                                Toast.makeText(signup_activity.this, "Signup Failed,Try Again",Toast.LENGTH_LONG).show();// Giving Signup Success Full Messaage

                                            }
                                            else
                                            {
                                                Toast.makeText(signup_activity.this, "Signup Scuccesfull ",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                            startActivity(new Intent(signup_activity.this, login_activity_customer.class));// Advancing to the login Page again after Successful Signup

                        }
                        else
                        {
                            Toast.makeText(signup_activity.this, "Signup Failed,Try Again",Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }
}
package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {
     EditText mEmail, mPassword;
     FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEmail = findViewById(R.id.loginUsername);                         //entered email
        mPassword = findViewById(R.id.loginPassword);                     //entered password
        fAuth = FirebaseAuth.getInstance();                             //used to access firebase utilities
        FirebaseUser currentUser = fAuth.getCurrentUser();              //loads in current users session

        Button button = findViewById(R.id.btnLogin);                    //login button
        Button logout = findViewById(R.id.btnLogout);                   //this is the new logout button
        TextView passRest = findViewById(R.id.loginForgotPass);

        button.setOnClickListener(v -> {

            String email = mEmail.getText().toString().trim();          //email text box from login_activity.xml
            String password = mPassword.getText().toString().trim();    //password text box from login_activity.xml

            //check if already logged in
            //if current user is not null they are already logged in, print error message
            if (currentUser != null) {
                //reload();    load their previous session ->link to their account page
                Toast.makeText(Login.this, "Already Logged in", Toast.LENGTH_SHORT).show();
            } else {
                //if they are not logged in read in their credentials
                if (TextUtils.isEmpty(email)) {               //is email empty?
                    mEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {            //is password empty?
                    mEmail.setError("Password is required.");
                }
            }
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {   // email verification
                        if (task.isSuccessful()) {
                            //login was successful, links to main activity page via openact() function
                            // Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            //  FirebaseUser user = fAuth.getCurrentUser();  //creates a warning but is necessary so that
                            //the user stays logged in
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();    // ciara
                            //  openAct();
                            if (user.isEmailVerified()) {
                                openAct();  //go to main page if email is verified
                            } else {
                                user.sendEmailVerification();       //else send email verification
                                Toast.makeText(Login.this, "Check your email to verify you account.", Toast.LENGTH_LONG).show();

                            }
                        } else {
                            //login not successful, prints reason
                            //example: login credentials are not registered
                          //  Toast.makeText(Login.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(Login.this, "Log in failed. Please check your credentials and try again.", Toast.LENGTH_LONG).show();

                        }
                    }
            );
       // }
        });

        //this sets up the logout functionality for the button
        //could insert an if statement to check it worked?
        logout.setOnClickListener(log -> {
            //logs out the current user then returns to main activity
            if(currentUser!=null) {     //if existing user
                FirebaseAuth.getInstance().signOut();   //signout
                Toast.makeText(Login.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                openAct();  //returns to main page
            }
            else {
                Toast.makeText(Login.this, "Not signed in!", Toast.LENGTH_SHORT).show();
            }
        });

        passRest.setOnClickListener(pass -> {     //button for 'Forgot Password' on login screen
            String email = mEmail.getText().toString().trim();      //email entered on login screen text box
            if(email.equals("")){       //better than using '=='
                Toast.makeText(Login.this, "Please Enter Email Above to Reset Password", Toast.LENGTH_SHORT).show();
            }
            else {
                fAuth.sendPasswordResetEmail(email);    //sends recovery email
                Toast.makeText(Login.this, "Recovery Email Sent", Toast.LENGTH_SHORT).show();
                openAct();  //returns to main screen
            }
        });
    }

    public void openAct(){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);  //changes page to main activity
    }

    public void client_reg(View V){
        //open client registration
        Intent intent = new Intent(this, ClientRegActivity.class);
        startActivity(intent);  //changes page to client registration activity
    }

    public void service_reg(View V){
        //open service provider registration
        Intent intent = new Intent(this, ServiceRegActivity.class);
        startActivity(intent);  //changes page to service register activity
    }

    /////
}
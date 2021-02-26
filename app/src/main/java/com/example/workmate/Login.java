package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    FirebaseAuth fAuth;

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);                        //refers to activity_login.xml file

        Toolbar mToolbar = findViewById(R.id.nav_action);               //navigation bar on the side
        setSupportActionBar(mToolbar);

        DrawerLayout mDrawerLayout = findViewById(R.id.drawerLayout);       //more layout type actions
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mEmail = findViewById(R.id.etUsername);                         //entered email
        mPassword = findViewById(R.id.imgPassword);                     //entered password
        fAuth = FirebaseAuth.getInstance();                             //used to access firebase utilities
        FirebaseUser currentUser = fAuth.getCurrentUser();              //loads in current users session
        Button button = findViewById(R.id.btnLogin);                    //login button

        Button logout = findViewById(R.id.btnLogout);                   //this is the new logout button

        Button reset_password = findViewById(R.id.button3);             //forgot password button on login page

        button.setOnClickListener(v -> {

            String email = mEmail.getText().toString().trim();          //email text box from login_activity.xml
            String password = mPassword.getText().toString().trim();    //password text box from login_activity.xml

            //check if already logged in
            //if current user is not null they are already logged in, print error message
            if(currentUser != null) {
                //reload();    load their previous session ->link to their account page
                Toast.makeText(Login.this, "Already Logged in", Toast.LENGTH_SHORT).show();
            } else {
                //if they are not logged in read in their credentials
                if(TextUtils.isEmpty(email)){               //is email empty?
                    mEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){            //is password empty?
                    mEmail.setError("Password is required.");
                }
                //run the firebase login functions
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        //login was successful, links to main activity page via openact() function
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                      //  FirebaseUser user = fAuth.getCurrentUser();  //creates a warning but is necessary so that
                                                                        //the user stays logged in

                        openAct();
                    }else {
                        //login not succesful, prints reason
                        //example: login credentials are not registered
                        Toast.makeText(Login.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
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

        reset_password.setOnClickListener(pass -> {     //button for 'Forgot Password' on login screen
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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

}
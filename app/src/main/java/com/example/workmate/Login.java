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

<<<<<<< HEAD
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private androidx.appcompat.widget.Toolbar mToolbar;

=======
    private ActionBarDrawerToggle mToggle;

>>>>>>> af7f96c639965113ec889ffedcc2af9ba6807c05
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

<<<<<<< HEAD
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
=======
        Toolbar mToolbar = findViewById(R.id.nav_action);
>>>>>>> af7f96c639965113ec889ffedcc2af9ba6807c05
        setSupportActionBar(mToolbar);

        DrawerLayout mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mEmail = findViewById(R.id.etUsername);
        mPassword = findViewById(R.id.imgPassword);
        fAuth = FirebaseAuth.getInstance();
<<<<<<< HEAD
        Button button = (Button) findViewById(R.id.btnLogin);

        Button logout = (Button) findViewById(R.id.button3);
=======
        FirebaseUser currentUser = fAuth.getCurrentUser();
        Button button = findViewById(R.id.btnLogin);

        Button logout = findViewById(R.id.btnLogout);  //this is the new logout button
>>>>>>> af7f96c639965113ec889ffedcc2af9ba6807c05

        button.setOnClickListener(v -> {

            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            //check if already logged in
            //if current user is not null they are already logged in, print error message
            if(currentUser != null) {
                //reload();    load their previous session ->link to their account page
                Toast.makeText(Login.this, "Already Logged in", Toast.LENGTH_SHORT).show();
            } else {
<<<<<<< HEAD

=======
                //if they are not logged in read in their credentials
>>>>>>> af7f96c639965113ec889ffedcc2af9ba6807c05
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mEmail.setError("Password is required.");
                }
<<<<<<< HEAD

=======
                //run the firebase login functions
>>>>>>> af7f96c639965113ec889ffedcc2af9ba6807c05
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        //login was succesful, links to main activity page via openact() function
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
            if(currentUser!=null) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Login.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                openAct();
            }
            else {
                Toast.makeText(Login.this, "Not signed in!", Toast.LENGTH_SHORT).show();
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
        startActivity(intent);
    }

    public void client_reg(View V){
        //open client registration
        Intent intent = new Intent(this, ClientRegActivity.class);
        startActivity(intent);
    }

    public void service_reg(View V){
        //open service provider registration
        Intent intent = new Intent(this, ServiceRegActivity.class);
        startActivity(intent);
    }

}
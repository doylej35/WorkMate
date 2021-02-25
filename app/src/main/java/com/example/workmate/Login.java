package com.example.workmate;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

=======
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

>>>>>>> design_josh
public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    FirebaseAuth fAuth;

<<<<<<< HEAD
=======
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private androidx.appcompat.widget.Toolbar mToolbar;

>>>>>>> design_josh
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

<<<<<<< HEAD
=======
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

>>>>>>> design_josh
        mEmail = findViewById(R.id.etUsername);
        mPassword = findViewById(R.id.imgPassword);
        fAuth = FirebaseAuth.getInstance();
        Button button = (Button) findViewById(R.id.btnLogin);

<<<<<<< HEAD
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //check if already logged in
                FirebaseUser currentUser = fAuth.getCurrentUser();
                if(currentUser != null) {
                    //reload();    load their previous session
                    //Toast.makeText(Login.this, "Already Logged in", Toast.LENGTH_SHORT).show();
                } else {
=======
        Button logout = (Button) findViewById(R.id.button3);

        button.setOnClickListener(v -> {

            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            //check if already logged in
            FirebaseUser currentUser = fAuth.getCurrentUser();
            if(currentUser != null) {
                //reload();    load their previous session
                Toast.makeText(Login.this, "Already Logged in", Toast.LENGTH_SHORT).show();
            } else {
>>>>>>> design_josh

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mEmail.setError("Password is required.");
                }

<<<<<<< HEAD
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = fAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            openAct();
                        }else {
                            Toast.makeText(Login.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                }
            }
        });

    }
=======
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = fAuth.getCurrentUser();
                        //startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        openAct();
                    }else {
                        Toast.makeText(Login.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        logout.setOnClickListener(log -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(Login.this , "Logout Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

>>>>>>> design_josh
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
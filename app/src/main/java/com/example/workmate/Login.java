package com.example.workmate;

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

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.etUsername);
        mPassword = findViewById(R.id.imgPassword);
        fAuth = FirebaseAuth.getInstance();
        Button button = (Button) findViewById(R.id.btnLogin);

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

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mEmail.setError("Password is required.");
                }

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
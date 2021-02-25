package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ServiceRegActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_reg);

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


<<<<<<< HEAD
        final EditText Fname = (EditText) findViewById(R.id.etFirstName);
        final EditText Lname = (EditText) findViewById(R.id.etLastName);
        final EditText Email1 = (EditText) findViewById(R.id.etEmail1);
        final EditText Email2 = (EditText) findViewById(R.id.etEmail2);
        final EditText Addr1 = (EditText) findViewById(R.id.etAddress1);
        final EditText Phone = (EditText) findViewById(R.id.etPhone);
        final EditText Pass1 = (EditText) findViewById(R.id.etPassword1);
        final EditText Pass2 = (EditText) findViewById(R.id.etPassword2);

        final EditText Prof = (EditText) findViewById(R.id.etProfession);
        final EditText Dist = (EditText) findViewById(R.id.etProfession);


        Button button = (Button) findViewById(R.id.button);
        final CheckBox Cons = (CheckBox) findViewById(R.id.cbTermsOfServices);
        CheckBox Cons1 = (CheckBox) findViewById(R.id.cbTermsOfServices);
=======
        final EditText Fname = findViewById(R.id.etFirstName);
        final EditText Lname =  findViewById(R.id.etLastName);
        final EditText Email1 = findViewById(R.id.etEmail1);
        final EditText Email2 =  findViewById(R.id.etEmail2);
        final EditText Addr1 =  findViewById(R.id.etAddress1);
        final EditText Phone =  findViewById(R.id.etPhone);
        final EditText Pass1 = findViewById(R.id.etPassword1);
        final EditText Pass2 =  findViewById(R.id.etPassword2);

        final EditText Prof = findViewById(R.id.etProfession);
        final EditText Dist = findViewById(R.id.etDistance);


        Button button = findViewById(R.id.button);

        //why is this repeated?? are there two boxes?? the terms of service box is cbTermsOfServices2
        //but it looks like there is another box on the xml layout page.....
        final CheckBox Cons =  findViewById(R.id.cbTermsOfServices);
        CheckBox Cons1 = findViewById(R.id.cbTermsOfServices);
>>>>>>> af7f96c639965113ec889ffedcc2af9ba6807c05

        button.setOnClickListener(v -> {
            if (Fname.length() == 0  ){
                Fname.setError("Please enter Name");
            }
            else if (Lname.length() == 0){
                Lname.setError("Please enter Name");
            }
            else if (Email1.length() == 0){
                Email1.setError("Please enter Email");
            }
            else if (Email2.length() == 0 ){
                //|| Email2 != Email1
                Email2.setError("Please enter Email");
            }
            else if (!Email1.getText().toString().equals(Email2.getText().toString())) {
                Email2.setError("Please enter Email");
            }
            else if (Addr1.length() == 0){
                Addr1.setError("Please enter Address");
            }
            else if (Phone.length() == 0){
                Phone.setError("Please enter Phone number");
            }
            else if (Prof.length() == 0){
                Prof.setError("Please enter Proffesion");
            }
            else if (!Cons.isChecked()) {
                Cons.setError("Click yes");
            }
            else if (Dist.length() == 0){
                //|| Pass1 != Pass2
                Dist.setError("Please enter Distance");
            }
            else if (Pass1.length() == 0){
                Pass1.setError("Please enter Password");
            }
            else if (Pass2.length() == 0){
                Pass2.setError("Please enter Password");
            }
            else if (!Pass1.getText().toString().equals(Pass2.getText().toString())){
                Pass2.setError("Please enter Password");
            }
            else if (!Cons.isChecked()){
                Cons.setError("Click yes");
            }
            else{
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                String email = Email1.getText().toString().trim();
                String password = Pass1.getText().toString().trim();

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task ->{
                    if(task.isSuccessful()) {
                        Toast.makeText(ServiceRegActivity.this, "Worker Created",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else
                        Toast.makeText(ServiceRegActivity.this, "Error" + Objects.
                                requireNonNull(task.getException()).getMessage(), Toast
                                .LENGTH_SHORT).show();
                });
                openAct();
            }

        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //add in registration functions details
    public void openAct(){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }
}

//Git Testing :)

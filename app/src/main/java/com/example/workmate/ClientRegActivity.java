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

public class ClientRegActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private androidx.appcompat.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_reg);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        final EditText Fname = (EditText) findViewById(R.id.etFirstName);
        final EditText Lname = (EditText) findViewById(R.id.etLastName);
        final EditText Email1 = (EditText) findViewById(R.id.etEmail1);
        final EditText Email2 = (EditText) findViewById(R.id.etEmail2);
        final EditText Addr1 = (EditText) findViewById(R.id.etAddress1);
        final EditText Phone = (EditText) findViewById(R.id.etPhone);
        final EditText Pass1 = (EditText) findViewById(R.id.etPassword1);
        final EditText Pass2 = (EditText) findViewById(R.id.etPassword2);


        Button button = (Button) findViewById(R.id.button);
        final CheckBox Cons = (CheckBox) findViewById(R.id.cbTermsOfServices);

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
                Email2.setError("Please enter Email");
            }
            else if (!Email1.getText().toString().equals(Email2.getText().toString())){
                Email2.setError("Please enter Email");
            }
            else if (Addr1.length() == 0){
                Addr1.setError("Please enter Address");
            }
            else if (Phone.length() == 0){
                Phone.setError("Please enter Phone number");
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
            else {
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                String email = Email1.getText().toString().trim();
                String password = Pass1.getText().toString().trim();

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    //sign up failed!! print error message
                    if(task.isSuccessful()) {
                        //sign up was succesful print a message
                        Toast.makeText(ClientRegActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        openAct();
                    }else
                        Toast.makeText(ClientRegActivity.this, "Error" + Objects.requireNonNull(task.getException())
                                .getMessage(), Toast.LENGTH_SHORT).show();
                });

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

    //add in registration functions details
    public void openAct(){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

}
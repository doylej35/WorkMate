package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ServiceRegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_reg);

        Toolbar mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
                //if all the data is entered correctly move to firebase functionality
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                String email = Email1.getText().toString().trim();
                String password = Pass1.getText().toString().trim();

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task ->{
                    if(task.isSuccessful()) {
                        Toast.makeText(ServiceRegActivity.this, "Worker Created",
                                Toast.LENGTH_SHORT).show();

                        //add the person to the supplier table of the database
                        SupplierModel supplierModel = new SupplierModel(-1, Fname.getText().toString(), Lname.getText().toString(),
                                Phone.getText().toString(), email,  Addr1.getText().toString(), Prof.getText().toString());

                        DatabaseHelper databaseHelper = new DatabaseHelper(ServiceRegActivity.this);

                        boolean success = databaseHelper.addSupplier(supplierModel);
                        Toast.makeText(ServiceRegActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();

                        openAct();
                    }else
                        Toast.makeText(ServiceRegActivity.this, "Error" + Objects.
                               requireNonNull(task.getException()).getMessage(), Toast
                                .LENGTH_SHORT).show();
                });
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //add in registration functions details
    public void openAct(){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }
}

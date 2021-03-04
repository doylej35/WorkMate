package com.example.workmate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ServiceRegActivity extends AppCompatActivity {

    String Prof;

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
        final CheckBox Cons =  findViewById(R.id.cbTermsOfServices);

        final Spinner spinner = (Spinner) findViewById(R.id.professions);
        String[] services = new String[]{
                "Select an service...",
                "Mechanic",
                "Plumber",
                "Electrician",
                "Gardener"
        };

        final List<String> serviceList = new ArrayList<>(Arrays.asList(services));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.item_spinner,serviceList){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Prof = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ServiceRegActivity.this,
                        "Enter Service", Toast.LENGTH_SHORT).show();
            }
        });

        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            if (Fname.length() == 0  ){
                //no first name
                Fname.setError("Please enter Name");
            }
            else if (Lname.length() == 0){
                //if no last name
                Lname.setError("Please enter Name");
            }
            else if (Email1.length() == 0){
                //if no email1
                Email1.setError("Please enter Email");
            }
            else if (Email2.length() == 0 ){
                //if no email2
                Email2.setError("Please enter Email");
            }
            else if (!Email1.getText().toString().equals(Email2.getText().toString())) {
                //if email1 and 2 not equal
                Email2.setError("Emails don't match");
            }
            else if (Addr1.length() == 0){
                //empty address
                Addr1.setError("Please enter Address");
            }
            else if (Phone.length() == 0){
                //empty phone
                Phone.setError("Please enter Phone number");
            }
            else if (!Cons.isChecked()) {
                //if cond box not checked
                Cons.setError("Must agree to terms");
            }
            else if (Pass1.length() == 0){
                //no pass1
                Pass1.setError("Please enter Password");
            }
            else if (Pass2.length() == 0){
                //no pas2
                Pass2.setError("Please enter Password");
            }
            else if (!Pass1.getText().toString().equals(Pass2.getText().toString())) {
                //pass words don't match
                Pass2.setError("Passwords don't match");
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
                                Phone.getText().toString(), email,  Addr1.getText().toString(), Prof);

                        DatabaseHelper databaseHelper = new DatabaseHelper(ServiceRegActivity.this);

                        //not working ??
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

    }

    //add in registration functions details
    public void openAct(){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

}

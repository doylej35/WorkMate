package com.example.workmate.Activities;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.workmate.DatabaseHelper;
import com.example.workmate.R;
import com.example.workmate.Models.SupplierModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ServiceRegActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;

    EditText Fname;
    EditText Lname;
    EditText Email1;
    EditText Email2;
    EditText Addr1;
    EditText Phone;
    EditText Pass1;
    EditText Pass2;
    CheckBox Cons;
    Button button;
    String Prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_reg);

        Fname = findViewById(R.id.etFirstName);
        Lname =  findViewById(R.id.etLastName);
        Email1 = findViewById(R.id.etEmail1);
        Email2 =  findViewById(R.id.etEmail2);
        Addr1 =  findViewById(R.id.etAddress1);
        Phone =  findViewById(R.id.etPhone);
        Pass1 = findViewById(R.id.etPassword1);
        Pass2 =  findViewById(R.id.etPassword2);
        Cons =  findViewById(R.id.cbTermsOfServices);

        auth = FirebaseAuth.getInstance();

        Toolbar mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner spinner = findViewById(R.id.professions);
        String[] services = new String[]{
                "Select a service...",
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

        button = findViewById(R.id.service_reg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    String username = Fname.getText().toString();
                    String email = Email1.getText().toString();
                    String password = Pass1.getText().toString();

                    register(username,email,password);
                }
            }
        });

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    public void register(String username,String email, String password){
        auth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            SupplierModel supplierModel = new SupplierModel(-1, Fname.getText().toString(), Lname.getText().toString(),
                                    Phone.getText().toString(), email,  Addr1.getText().toString(), Prof);
                            DatabaseHelper databaseHelper = new DatabaseHelper(ServiceRegActivity.this);
                            boolean success = databaseHelper.addSupplier(supplierModel);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(ServiceRegActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else{
                            Toast.makeText(ServiceRegActivity.this,
                                    "You can't register with this email or password"
                                    ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ClientRegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_reg);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText Fname = (EditText) findViewById(R.id.etFirstName);
        EditText Lname = (EditText) findViewById(R.id.etLastName);
        EditText Email1 = (EditText) findViewById(R.id.etEmail1);
        EditText Email2 = (EditText) findViewById(R.id.etEmail2);
        EditText Addr1 = (EditText) findViewById(R.id.etAddress1);
        EditText Phone = (EditText) findViewById(R.id.etPhone);
        EditText Pass1 = (EditText) findViewById(R.id.etPassword1);
        EditText Pass2 = (EditText) findViewById(R.id.etPassword2);




        Button button = (Button) findViewById(R.id.button);
        CheckBox Cons = (CheckBox) findViewById(R.id.cbTermsOfServices);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //sign up was succesful print a message
                            Toast.makeText(ClientRegActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            openAct();
                        }else {
                            //sign up failed!! print error message
                            Toast.makeText(ClientRegActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    });

                }

            }
        });

    }

    //add in registration functions details
    public void openAct(){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

}

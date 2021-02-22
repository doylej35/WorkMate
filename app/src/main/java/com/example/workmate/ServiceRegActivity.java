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

public class ServiceRegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_reg);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ServiceRegActivity.this, "Worker Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }else {
                                Toast.makeText(ServiceRegActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    openAct();
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

//Git Testing :)

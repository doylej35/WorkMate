package com.example.workmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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
                    //|| Email2 != Email1
                    Email2.setError("Please enter Email");
                }
                else if (Email2.equals(Email1)){
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
                    //|| Pass1 != Pass2
                    Pass2.setError("Please enter Password");
                }
                else if (Pass2.equals(Pass1)){
                    //|| Pass1 != Pass2
                    Pass2.setError("Please enter Password");
                }
                else if (!Cons.isChecked()){
                    Cons.setError("Click yes");
                }
                //if (Fname.length() && Lname.length() && Email1.length() && Email2.length() && Addr1.length() && Phone.length() && Pass1.length() !=0 && Pass2.length() != 0) {
                else    openAct();
                //}
            }
        });

    }

    //add in registration functions details
    public void openAct(){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

}
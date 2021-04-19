package com.example.workmate.activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.workmate.database.DatabaseHelper;
import com.example.workmate.R;
import com.example.workmate.models.SupplierModel;
import com.example.workmate.models.ClientModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditProfileActivity extends AppCompatActivity {
    private EditText Fname, Lname, Address1, Phone;
    private DatabaseHelper databaseHelper;
    private Button button;
    String firstned, lastned, addressed, serviceed, postcodeed, phoneed; //edited string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Toolbar mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = new Intent( this, MainActivity.class);

        button = findViewById(R.id.edbtnEditProfile);

        Fname = findViewById(R.id.edFName);
        Lname = findViewById(R.id.edLName);
        Address1 = findViewById(R.id.edAddress1);
        Phone = findViewById(R.id.edPhone);

        databaseHelper = new DatabaseHelper(EditProfileActivity.this);
        String email = user.getEmail();
        String service;

        SupplierModel supplier = databaseHelper.searchSupplier(email);
        if(supplier != null){
            addressed = supplier.getSupplierAddr();
            phoneed = supplier.getSupplierPhone();
            firstned = supplier.getSupplierFname();
            lastned = supplier.getSupplierLname();
            service = supplier.getSupplierService();

        }else {
            ClientModel client = databaseHelper.searchClient(email);
            service = "Regular User";
            firstned = client.getClientFname();
            lastned = client.getClientLname();
            addressed = client.getClientAddr();
            phoneed = client.getClientPhone();

        }

        String uid = user.getUid();

        TextView textView;
        textView = findViewById(R.id.edEmail);
        textView.setText(email);

        textView = findViewById(R.id.edService);
        textView.setText(service);

        Fname.setText(firstned);
        Lname.setText(lastned);
        Address1.setText(addressed);
        Phone.setText(phoneed);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getEmail();
                if(supplier != null){
                    databaseHelper.updateSupplier(email, Fname.getText().toString(), Lname.getText().toString(), Address1.getText().toString(), Phone.getText().toString());
                    startActivity(intent);

                }else {
                    databaseHelper.updateClient(email, Fname.getText().toString(), Lname.getText().toString(), Address1.getText().toString(), Phone.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
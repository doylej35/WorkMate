package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.workmate.database.DatabaseHelper;
import com.example.workmate.models.SupplierModel;

public class SupplierProfileActivity extends AppCompatActivity {

    public final static String EMAIL ="com.example.message_key";
    private DatabaseHelper databaseHelper;
    String address1, phone, service ,fname, lname;
    Button RateUser, Viewrating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_profile);

        Toolbar mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(SupplierProfileActivity.this);

        Intent intent = getIntent();
        String email = intent.getStringExtra(EMAIL);

        SupplierModel supplier = databaseHelper.searchSupplier(email);

        address1 = supplier.getSupplierAddr();
        phone = supplier.getSupplierPhone();
        service = supplier.getSupplierService();
        fname = supplier.getSupplierFname();
        lname = supplier.getSupplierLname();

        TextView textView;
        textView = findViewById(R.id.SUPEmail);
        textView.setText(email);

        textView = findViewById(R.id.SUPFName);
        textView.setText(fname);

        textView = findViewById(R.id.SUPLName);
        textView.setText(lname);

        textView = findViewById(R.id.SUPAddress1);
        textView.setText(address1);

        textView = findViewById(R.id.SUPPhone);
        textView.setText(phone);


        textView = findViewById(R.id.SUPService);
        textView.setText(service);

        RateUser = findViewById(R.id.SUPRating);
        Viewrating = findViewById(R.id.SUPSeeRating);

        Viewrating.setOnClickListener( v -> {

            Intent intent1 = new Intent( this, ReviewView.class);
            intent1.putExtra(EMAIL, email);
            startActivity(intent1);  //changes page to main activity
        });

        RateUser.setOnClickListener( v -> {

            Intent intent1 = new Intent( this, RateService.class);
            intent1.putExtra(EMAIL, email);
            startActivity(intent1);  //changes page to main activity
        });
    }
}
package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SupplierProfileActivity extends AppCompatActivity {

    public final static String EMAIL ="com.example.message_key";
    private DatabaseHelper databaseHelper;
    String address1, phone, service ,fname, lname;
    Button button;


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

        /*mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment fragment = SearchFragment.newInstance("You are searching for: General");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        fragment).commit();
            }
        });*/

    }


    public void openAct(){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);  //changes page to main activity
    }
}
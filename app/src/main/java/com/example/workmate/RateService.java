package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.workmate.database.DatabaseHelper;
import com.example.workmate.models.RatingsModel;
import com.example.workmate.models.SupplierModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RateService extends AppCompatActivity {
    String fname, lname, phone;
    public final static String EMAIL ="com.example.message_key";
    private DatabaseHelper databaseHelper;
    Button rate;
    EditText Ratetext;
    RatingBar Star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_service);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Toolbar mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(RateService.this);

        Intent intent = getIntent();
        String email = intent.getStringExtra(EMAIL);

        SupplierModel supplier = databaseHelper.searchSupplier(email);

        fname = supplier.getSupplierFname();
        lname = supplier.getSupplierLname();
        phone = supplier.getSupplierPhone();

        TextView textView;
        textView = findViewById(R.id.RatingEmail);
        textView.setText(email);

        textView = findViewById(R.id.RatingFName);
        textView.setText(fname);

        textView = findViewById(R.id.RatingLName);
        textView.setText(lname);

        rate = findViewById(R.id.RateButton);
        Ratetext = findViewById(R.id.RatingText);
        Star = findViewById(R.id.RatingBar);
        String CEmail = user.getEmail();

        rate.setOnClickListener(v -> {
            float IRATE = Star.getRating();
            String SRATE = Ratetext.getText().toString();

            //Toast.makeText(RateService.this, "User Created" +IRATE+SRATE, Toast.LENGTH_SHORT).show();

            RatingsModel ratingsModel = new RatingsModel(-1, CEmail, email, IRATE, SRATE);

            DatabaseHelper databaseHelper = new DatabaseHelper(RateService.this);

            boolean success = databaseHelper.addRating(ratingsModel);
            Toast.makeText(RateService.this, "Success= " + success, Toast.LENGTH_SHORT).show();

            //ClientModel clientModel = new ClientModel(0, fname, lname, phone, email, addr1, "null", "null");

        });
    }
}
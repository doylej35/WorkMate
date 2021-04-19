package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewView extends AppCompatActivity {

    private RVRatings rvAdapter;
    private RecyclerView suppliersRV;
    private ArrayList<SupplierModel> supplierModelArrayList;
    private ArrayList<RatingsModel> ratingsModels;
    public final static String EMAIL ="com.example.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_view);

        DatabaseHelper dbHelper = new DatabaseHelper(ReviewView.this);

        Intent intent = getIntent();
        String email = intent.getStringExtra(EMAIL);

        supplierModelArrayList = dbHelper.readSuppliers();
        ratingsModels = dbHelper.searchRating(email);

        rvAdapter = new RVRatings(ratingsModels, supplierModelArrayList, ReviewView.this);
        suppliersRV = findViewById(R.id.idRVRating);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReviewView.this, RecyclerView.VERTICAL, false);
        suppliersRV.setLayoutManager(linearLayoutManager);

        suppliersRV.setAdapter(rvAdapter);

    }
}
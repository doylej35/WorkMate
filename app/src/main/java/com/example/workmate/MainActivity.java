package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Objects;

public class MainActivity<stringTextView> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle mToggle;
    private DrawerLayout mDrawer;
    FirebaseAuth fAuth;
    private ArrayList<SupplierModel> supplierModelArrayList;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawer = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);

        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        fAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = fAuth.getCurrentUser();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_home: //launch home
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new HomeFragment()).commit();
                break;

            case R.id.nav_search: //launch search
                SearchFragment fragment = SearchFragment.newInstance("You are searching for: General");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        fragment).commit();
                break;

            case R.id.nav_account: //launch account
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new ProfileFragment()).commit();
                break;

            case R.id.nav_messages: //launch messages
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new MessagesFragment()).commit();
                break;

            case R.id.nav_settings: //launch settings
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new SettingsFragment()).commit();
                break;

            case R.id.nav_login: //launch login
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new LoginFragment()).commit();
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START); //close drawer
        return true;
    }

    @Override //open navigation drawer
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void client_reg(View V){
        //open client registration
        Intent intent = new Intent(this, ClientRegActivity.class);
        startActivity(intent);
    }

    public void service_reg(View V){
        //open service provider registration
        Intent intent = new Intent(this, ServiceRegActivity.class);
        startActivity(intent);
    }

    //function to open search page
    public void openSearch(View view){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        ArrayList<SupplierModel> services = new ArrayList<>();
        String text;
        switch(view.getId()){
            case(R.id.SearchElec):
                text = "You are searching for: Electricians";
                services = databaseHelper.searchService("electrician");
                break;
            case(R.id.SearchMech):
                text = "You are searching for: Mechanics";
                services = databaseHelper.searchService("mechanic");
                break;
            case(R.id.SearchPlum):
                text = "You are searching for: Plumbers";
                services = databaseHelper.searchService("plumber");
                break;
            case(R.id.SearchGard):
                text = "You are searching for: Gardeners";
                services = databaseHelper.searchService("gardener");
                break;
            default:
                text = "You are searching for: General";
                supplierModelArrayList = databaseHelper.readSuppliers();

                break;
        }
        if(services.size()>0) {
            StringBuffer sb = new StringBuffer();
         //   sb.append(supplierModelArrayList.get(0));
            String name = sb.toString();
            Log.d("CREATION", name);
        }else {
            Log.d("CREATION", "NO SUPPLIERS OF THIS TYPE");
            Toast.makeText(MainActivity.this, "No suppliers of this type", Toast.LENGTH_SHORT).show();
        }


        SearchFragment fragment = SearchFragment.newInstance(text);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                fragment).commit();


    }


}

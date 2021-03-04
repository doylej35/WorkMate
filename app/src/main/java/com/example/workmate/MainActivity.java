package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START); //close drawer
        return true;
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

    @Override //open navigation drawer
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //function to open search page
    public void openSearch(View view){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        ArrayList<SupplierModel> services = new ArrayList<>();
        String text;
        switch(view.getId()){
            case(R.id.SearchElec):
                text = "You are searching for: Electricians";
                Log.d("CREATION","BEFORE DISPLAY");
                services = databaseHelper.search("electrician");
                Log.d("CREATION",String.valueOf(services.get(0)));      //prints to console, first electrician in services list
                break;
            case(R.id.SearchMech):
                text = "You are searching for: Mechanics";
                services = databaseHelper.search("mechanic");
                Log.d("CREATION",String.valueOf(services.get(0)));
                break;
            case(R.id.SearchPlum):
                text = "You are searching for: Plumbers";
                services = databaseHelper.search("plumber");
                Log.d("CREATION",String.valueOf(services.get(0)));
                break;
            case(R.id.SearchGard):
                text = "You are searching for: Gardeners";
                services = databaseHelper.search("gardener");
                Log.d("CREATION",String.valueOf(services.get(0)));
                break;
            default:
                text = "You are searching for: General";
                supplierModelArrayList = databaseHelper.readSuppliers();
                Log.d("CREATION",String.valueOf(supplierModelArrayList.get(0)));
                break;
        }
        SearchFragment fragment = SearchFragment.newInstance(text);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                fragment).commit();
    }
    //Design Branch
}

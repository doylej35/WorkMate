package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity<stringTextView> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle mToggle;
    private DrawerLayout mDrawer;
    FirebaseAuth fAuth;
    private ArrayList<SupplierModel> supplierModelArrayList;
    private DatabaseHelper dbHelper;
    private RVAdapter rvAdapter;
    private RecyclerView suppliersRV;
    private Menu menu;
    int option = 0;

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

        menu = navigationView.getMenu();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if(currentUser != null){
            menu.findItem(R.id.nav_login).setVisible(false);
        }else{
            menu.findItem(R.id.nav_login).setVisible(true);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (menu != null){
                updateMenu();
            }
        }
    };

    private void updateMenu(){
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if (currentUser != null){
            menu.findItem(R.id.nav_login).setVisible(true);
        }else{
            menu.findItem(R.id.nav_login).setVisible(false);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //updateMenu();
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
                //open service provider registration
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

    public void client_reg(View V) {
        //open client registration
        Intent intent = new Intent(this, ClientRegActivity.class);
        startActivity(intent);
    }

    public void service_reg(View V) {
        //open service provider registration
        Intent intent = new Intent(this, ServiceRegActivity.class);
        startActivity(intent);
    }

    public void messagesTemp(View v) {
        Intent i = new Intent(this, MessagesActivityTemp.class);
        startActivity(i);
    }

    //function to open search page
    public void openSearch(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        //ArrayList<SupplierModel> services = new ArrayList<>();
        String text;
        switch (view.getId()) {
            case (R.id.SearchElec):
                text = "You are searching for: Electricians";
                //supplierModelArrayList = databaseHelper.searchService("electrician");
                option = 1;
                break;
            case (R.id.SearchMech):
                text = "You are searching for: Mechanics";
                //supplierModelArrayList = databaseHelper.searchService("mechanic");
                option = 2;
                break;
            case (R.id.SearchPlum):
                text = "You are searching for: Plumbers";
                //supplierModelArrayList = databaseHelper.searchService("plumber");
                option = 3;
                break;
            case (R.id.SearchGard):
                text = "You are searching for: Gardeners";
                //supplierModelArrayList = databaseHelper.searchService("gardener");
                option = 4;
                break;
            default:
                text = "You are searching for: General";
                //supplierModelArrayList = databaseHelper.readSuppliers();
                option = 0;
                break;
        }
        //Log.d("CREATION", String.valueOf(services.get(0)));
        //setContentView(R.layout.fragment_search);
        /*if(supplierModelArrayList.size()>0) {
            Toast.makeText(MainActivity.this, "Searching...", Toast.LENGTH_SHORT).show();
        }else {
            Log.d("CREATION", "NO SUPPLIERS OF THIS TYPE");
            Toast.makeText(MainActivity.this, "No suppliers of this type", Toast.LENGTH_SHORT).show();
        }*/

        SearchFragment fragment = SearchFragment.newInstance(text);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                fragment).commit();
    }

}

package com.example.workmate;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import android.location.LocationManager;
import android.location.Location;

import android.net.wifi.WifiManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
//import com.maxmind.geoip.Location;
//import com.maxmind.geoip.LookupService;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Objects;

public class ClientRegActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_reg);       //refers to activity_client_reg.xml file

        Toolbar mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText Fname = findViewById(R.id.etFirstName);  //User input on registration page
        final EditText Lname = findViewById(R.id.etLastName);
        final EditText Email1 = findViewById(R.id.etEmail1);
        final EditText Email2 = findViewById(R.id.etEmail2);
        final EditText Addr1 = findViewById(R.id.etAddress1);
        final EditText Phone = findViewById(R.id.etPhone);
        final EditText Pass1 = findViewById(R.id.etPassword1);
        final EditText Pass2 = findViewById(R.id.etPassword2);

        Button button =  findViewById(R.id.button);                     //register button
        final CheckBox Cons =  findViewById(R.id.cbTermsOfServices);


        /*String current = null;
        try {
            current = new File( "...../assets/GeoLite" ).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("PWD","Current dir: "+current);

        LookupService cl = null;
        try {
            cl = new LookupService("...../assets/GeoLite/GeoLiteCity.tar",
                    LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
            //Location location = cl.getLocation("some ip address");
            Log.d("LOCATION", cl.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        int REQUEST_LOCATION = 1;

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            return;
        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //if (locationGPS != null) {
        double lat = locationGPS.getLatitude();
        double longi = locationGPS.getLongitude();
        Toast.makeText(ClientRegActivity.this, "Location: " + lat + " " + longi, Toast.LENGTH_LONG).show();
        Log.d("LOCATION", lat + " " + longi);
        //}

        button.setOnClickListener(v -> {        //if register button pressed
            if (Fname.length() == 0  ){         //if nothing input ...
                Fname.setError("Please enter Name");
            }
            else if (Lname.length() == 0){
                Lname.setError("Please enter Name");
            }
            else if (Email1.length() == 0){
                Email1.setError("Please enter Email");
            }
            else if (Email2.length() == 0 ){
                Email2.setError("Please enter Email");
            }
            else if (!Email1.getText().toString().equals(Email2.getText().toString())){
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
                Pass2.setError("Please enter Password");
            }
            else if (!Pass1.getText().toString().equals(Pass2.getText().toString())){
                Pass2.setError("Please enter Password");
            }
            else if (!Cons.isChecked()){
                Cons.setError("Click yes");
            }
            else {
                //if all the fields are filled correctly begin firebase functionality
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                //convert variables to correct types
                String email = Email1.getText().toString().trim();
                String password = Pass1.getText().toString().trim();
                String fname = Fname.getText().toString();
                String lname = Lname.getText().toString();
                String phone = Phone.getText().toString();
                String addr1 = Addr1.getText().toString();

                //attempt to create a new user
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        //sign up was succesful print a message and go to main page
                        Toast.makeText(ClientRegActivity.this, "User Created", Toast.LENGTH_SHORT).show();

                        //add the person to the client table of the database
                        ClientModel clientModel = new ClientModel(0, fname, lname, phone, email, addr1, String.valueOf(lat), String.valueOf(longi));

                        DatabaseHelper databaseHelper = new DatabaseHelper(ClientRegActivity.this);

                        //print a pop up saying whether this succeeded or not
                        boolean success = databaseHelper.addClient(clientModel);
                        Toast.makeText(ClientRegActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();

                        //ClientModel clientModel1 = new ClientModel(0,"mary", "test2", "9379863", "testingserver@gmail.com", "address", "null", "null");

                        OKHttpPOST okHttpPOST = new OKHttpPOST();
                        okHttpPOST.saveClientData(clientModel);

                        Log.d("TESTING", "IMPORTANT STUFF ?? " + clientModel.toString());

                        Log.d("FINISHED", "back to main");
                        openAct();
                    }else
                        //sign up failed!! print error message
                        Toast.makeText(ClientRegActivity.this, "Error" + Objects.requireNonNull(task.getException())
                                .getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("FIREBASE ERROR", Objects.requireNonNull(task.getException())
                                .getMessage());
                });

            }

        });

    }

    //add in registration functions details
    public void openAct(){
        Intent intent = new Intent( this, OKHttpGET.class);
        startActivity(intent);
    }

}
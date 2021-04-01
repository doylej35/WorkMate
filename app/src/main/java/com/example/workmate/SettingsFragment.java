package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends Fragment {
    FirebaseAuth fAuth;
    View view;
    Button logout, PassReset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = fAuth.getCurrentUser();

        view = inflater.inflate(R.layout.fragment_settings,  container, false);

        logout = (Button) view.findViewById(R.id.setting_logout);
        PassReset = (Button) view.findViewById(R.id.setting_PassReset);

        Intent intent = new Intent( getActivity(), MainActivity.class);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logs out the current user then returns to main activity
                if(currentUser!=null) {     //if existing user
                    FirebaseAuth.getInstance().signOut();   //signout
                    Toast.makeText(getActivity(), "Logout Successful", Toast.LENGTH_SHORT).show();
                    startActivity(intent);  //changes page to main activity
                }
                else {
                    Toast.makeText(getActivity(), "Not signed in!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}

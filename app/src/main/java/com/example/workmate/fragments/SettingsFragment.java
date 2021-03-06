package com.example.workmate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.workmate.R;
import com.example.workmate.activities.EditProfileActivity;
import com.example.workmate.activities.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends Fragment {
    FirebaseAuth fAuth;
    View view;
    Button logout, PassReset, edit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = fAuth.getCurrentUser();

        view = inflater.inflate(R.layout.fragment_settings,  container, false);

        logout = (Button) view.findViewById(R.id.setting_logout);
        PassReset = (Button) view.findViewById(R.id.setting_PassReset);
        edit = (Button) view.findViewById(R.id.setting_editUser);

        Intent intent = new Intent( getActivity(), MainActivity.class);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logs out the current user then returns to main activity
                if(currentUser!=null) {     //if existing user
                    FirebaseAuth.getInstance().signOut();   //signout
                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(getString(R.string.default_web_client_id))
                            .requestEmail()
                            .build();
                    GoogleSignInClient mGoogleSignInClient;
                    mGoogleSignInClient= GoogleSignIn.getClient(getActivity(),gso);
                    mGoogleSignInClient.signOut();

                    Toast.makeText(getActivity(), "Logout Successful", Toast.LENGTH_SHORT).show();
                    startActivity(intent);  //changes page to main activity
                }
                else {
                    Toast.makeText(getActivity(), "Not signed in!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logs out the current user then returns to main activity
                if(currentUser!=null) {     //if existing user
                    Intent intent1 = new Intent( getActivity(), EditProfileActivity.class);
                    startActivity(intent1);
                }
                else {
                    Toast.makeText(getActivity(), "Not signed in!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        PassReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = currentUser.getEmail();
                //logs out the current user then returns to main activity
                if(currentUser!=null) {     //if existing user
                    fAuth.sendPasswordResetEmail(email);    //sends recovery email
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "Not signed  cannot reset!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
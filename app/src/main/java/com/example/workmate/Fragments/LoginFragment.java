package com.example.workmate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.workmate.Activities.MainActivity;
import com.example.workmate.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginFragment extends Fragment {
    EditText mEmail, mPassword;
    View view;
    FirebaseAuth fAuth;
    Button login, logout;
    TextView passRest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_login,  container, false);
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = fAuth.getCurrentUser();

        mEmail = view.findViewById(R.id.loginUsername);                         //entered email
        mPassword = view.findViewById(R.id.loginPassword);

        login = (Button) view.findViewById(R.id.loginLogin);
        passRest = view.findViewById(R.id.tvForgotPass);

        Intent intent = new Intent( getActivity(), MainActivity.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();          //email text box from login_activity.xml
                String password = mPassword.getText().toString().trim();    //password text box from login_activity.xml

                if(currentUser!=null) {
                    Toast.makeText(getActivity(), "Already Logged in", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(TextUtils.isEmpty(email)){               //is email empty?
                        mEmail.setError("Email is required.");
                        return;
                    }
                    if(TextUtils.isEmpty(password)){            //is password empty?
                        mEmail.setError("Password is required.");
                    }
                    //run the firebase login functions
                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            //login was successful, links to main activity page via openact() function
                            Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = fAuth.getCurrentUser();  //creates a warning but is necessary so that
                            //the user stays logged in

                            startActivity(intent);
                        }else {
                            //login not successful, prints reason
                            //example: login credentials are not registered
                            Toast.makeText(getActivity(), "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        passRest.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();      //email entered on login screen text box
            if(email.equals("")){       //better than using '=='
                Toast.makeText(getActivity(), "Please Enter Email Above to Reset Password", Toast.LENGTH_SHORT).show();
            }
            else {
                fAuth.sendPasswordResetEmail(email);    //sends recovery email
                Toast.makeText(getActivity(), "Recovery Email Sent", Toast.LENGTH_SHORT).show();
                startActivity(intent);  //returns to main screen
            }
        });

        return view;
    }
}

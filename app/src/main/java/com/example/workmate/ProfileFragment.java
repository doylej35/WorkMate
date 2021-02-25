package com.example.workmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, null);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String name1 = user.getDisplayName();
            String name2 = user.getDisplayName();
            String email = user.getEmail();
            //String pword = user.get

            String uid = user.getUid();

            TextView textView;
            textView = (TextView) root.findViewById(R.id.email);
            textView.setText(email);

            textView = (TextView) root.findViewById(R.id.fname);
            textView.setText(name1);

            textView = (TextView) root.findViewById(R.id.lname);
            textView.setText(name2);

        }else{
           Toast.makeText(getActivity(), "not logged in", Toast.LENGTH_SHORT).show();
        }
        /*firebaseDatabase = firebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Data");

        textView3 = textView3.findViewById(R.id.textView3);

        getdata();*/

        return root;
        //return inflater.inflate(R.layout.fragment_profile,  container, false);
    }

}

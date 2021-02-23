package com.example.workmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, null);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String name = user.getDisplayName();

            String uid = user.getUid();

            TextView textView;
            textView = (TextView) root.findViewById(R.id.textView3);
            textView.setText(name);
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

package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, null);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String email = user.getEmail();
            String address1;
            String phone;
            String service;
            String fname;
            String lname;


            SupplierModel supplier = databaseHelper.searchSupplier(email);
            if(supplier!=null) {
                address1 = supplier.getSupplierAddr();
                phone = supplier.getSupplierPhone();
                service = supplier.getSupplierService();
                fname = supplier.getSupplierFname();
                lname = supplier.getSupplierLname();
            }
            else {
                ClientModel client = databaseHelper.searchClient(email);
                address1 = client.getClientAddr();
                phone = client.getClientPhone();
                service = "Regular User";
                fname = client.getClientFname();
                lname = client.getClientLname();
            }

            String uid = user.getUid();

            TextView textView;
            textView = root.findViewById(R.id.tvEmail);
            textView.setText(email);

            textView = root.findViewById(R.id.tvFName);
            textView.setText(fname);

            textView = root.findViewById(R.id.tvLName);
            textView.setText(lname);

            textView = root.findViewById(R.id.tvAddress1);
            textView.setText(address1);

            textView = root.findViewById(R.id.tvPhone);
            textView.setText(phone);


            textView = root.findViewById(R.id.tvService);
            textView.setText(service);

        }else{

            Intent intent = new Intent( getActivity(), Login.class);
            startActivity(intent);  //changes page to main activity
           //Toast.makeText(getActivity(), "not logged in", Toast.LENGTH_SHORT).show();
        }
        /*firebaseDatabase = firebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Data");

        textView3 = textView3.findViewById(R.id.textView3);

        getdata();*/

        return root;
        //return inflater.inflate(R.layout.fragment_profile,  container, false);
    }

}

package com.example.workmate.fragments;

import android.content.Context;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmate.database.DatabaseHelper;
import com.example.workmate.R;
import com.example.workmate.activities.MainActivity;
import com.example.workmate.models.RVAdapter;
import com.example.workmate.models.SupplierModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;


public class SearchFragment extends Fragment {

    private static final String ARG_TEXT = "argText";
    private String text;
    private ArrayList<SupplierModel> supplierModelArrayList;
    private RVAdapter rvAdapter;
    private RecyclerView suppliersRV;
    private DatabaseHelper dbHelper;
    private MainActivity mainHelper;
    private String input;

    private String searchInput;
    private String spinnerInput;

    public static SearchFragment newInstance(String text){
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString("ARG_TEXT",text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_search,  container, false);

        SearchView search = v.findViewById(R.id.search);
        Spinner spinner = v.findViewById(R.id.spinner);

        Button button = v.findViewById(R.id.button2);

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        if (getArguments() != null) {
            text = getArguments().getString("ARG_TEXT");

            Log.d("CREATION","TEXT = " + text);

            switch (text) {
                case ("You are searching for: General"):
                    supplierModelArrayList = dbHelper.readSuppliers();
                    break;
                case ("You are searching for: Electricians"):
                    supplierModelArrayList = dbHelper.searchService("electrician");
                    break;
                case ("You are searching for: Mechanics"):
                    supplierModelArrayList = dbHelper.searchService("mechanic");
                    break;
                case ("You are searching for: Plumbers"):
                    supplierModelArrayList = dbHelper.searchService("plumber");
                    break;
                case ("You are searching for: Gardeners"):
                    supplierModelArrayList = dbHelper.searchService("gardener");
                    break;
            }
        }else{
            supplierModelArrayList = dbHelper.readSuppliers();
        }

        Toast.makeText(getActivity(),text, Toast.LENGTH_SHORT).show();

        Toast.makeText(getActivity(),text, Toast.LENGTH_SHORT).show();

        button.setOnClickListener(task -> {
            if (search.toString().equals("")){
                supplierModelArrayList = dbHelper.readSuppliers();
            }
            else{
                searchInput = search.getQuery().toString();
                spinnerInput = spinner.getSelectedItem().toString();
                supplierModelArrayList = dbHelper.searchServiceFilter(searchInput, spinnerInput);
                rvAdapter = new RVAdapter(supplierModelArrayList, getActivity());
                suppliersRV = v.findViewById(R.id.idRVSuppliers);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                suppliersRV.setLayoutManager(linearLayoutManager);

                suppliersRV.setAdapter(rvAdapter);
            }
        });

        //TESTING Sort Function
        List<Integer> list = new ArrayList<Integer>();
        list.add(20);
        list.add(10);
        ArrayList<SupplierModel> NewList = new ArrayList<>();
        //Log.d("ENTER TEST", list.toString() + supplierModelArrayList.toString());
        if(list.size() == supplierModelArrayList.size()) {

            String name1 = supplierModelArrayList.get(0).getSupplierFname();
            String name2 = supplierModelArrayList.get(1).getSupplierFname();
            int dist1 = list.get(0);
            int dist2 = list.get(1);

            NewList = dbHelper.sortF(supplierModelArrayList, list);

            String name1after = NewList.get(0).getSupplierFname();
            String name2after = NewList.get(1).getSupplierFname();

            if(dist1 > dist2 && name1.equals(name1after)){  //elements were not sorted properly by nearest distance
                Log.d("TESTING", "Error in Test1: Name1 ->" + name1 + " Name2 -> " + name2 + " Name1 After ->" +name1after + " Name2 After ->" + name2after);
            }
            else if(dist1 > dist2 && name2.equals(name2after)){
                Log.d("TESTING", "Error in Test2: Name1 ->" + name1 + " Name2 -> " + name2 + " Name1 After ->" +name1after + " Name2 After ->" + name2after);
            }
            else if(dist1 < dist2 && name1.equals(name2after)){
                Log.d("TESTING", "Error in Test3: Name1 ->" + name1 + " Name2 -> " + name2 + " Name1 After ->" +name1after + " Name2 After ->" + name2after);
            }
            else if(dist1 < dist2 && name2.equals(name1after)){
                Log.d("TESTING", "Error in Test4: Name1 ->" + name1 + " Name2 -> " + name2 + " Name1 After ->" +name1after + " Name2 After ->" + name2after);
            }
            else{
                Log.d("TESTING", "NO ERRORS DETECTED IN TESTING: Name1 ->" + name1 + " Name2 -> " + name2 + " Name1 After ->" +name1after + " Name2 After ->" + name2after);
            }
            Log.d("FINISHED TESTING", NewList.toString());
        }


        rvAdapter = new RVAdapter(NewList, getActivity());
        suppliersRV = v.findViewById(R.id.idRVSuppliers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        suppliersRV.setLayoutManager(linearLayoutManager);

        suppliersRV.setAdapter(rvAdapter);

        return v;
    }
}



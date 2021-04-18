package com.example.workmate;

import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

        Log.d("CREATION","TEXT = " + text);

        Toast.makeText(getActivity(),text, Toast.LENGTH_SHORT).show();

        Log.d("CREATION","TEXT = " + text);

        button.setOnClickListener(task -> {
            if (search.toString().equals("")){
                supplierModelArrayList = dbHelper.readSuppliers();
                //search.setError("Please enter Name");
                Log.d("CREATION","Empty Search");
            }
            else{
                searchInput = search.getQuery().toString();
                spinnerInput = spinner.getSelectedItem().toString();

                Log.d("CREATION",searchInput + " " + spinnerInput);
                supplierModelArrayList = dbHelper.searchServiceFilter(searchInput, spinnerInput);
                Log.d("CREATION",supplierModelArrayList.toString());
                rvAdapter = new RVAdapter(supplierModelArrayList, getActivity());
                suppliersRV = v.findViewById(R.id.idRVSuppliers);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                suppliersRV.setLayoutManager(linearLayoutManager);

                suppliersRV.setAdapter(rvAdapter);
            }
        });

        rvAdapter = new RVAdapter(supplierModelArrayList, getActivity());
        suppliersRV = v.findViewById(R.id.idRVSuppliers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        suppliersRV.setLayoutManager(linearLayoutManager);

        suppliersRV.setAdapter(rvAdapter);

        Log.d("CREATION", supplierModelArrayList.toString());

        return v;
    }
}



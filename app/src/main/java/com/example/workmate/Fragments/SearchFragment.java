package com.example.workmate.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.workmate.Activities.MainActivity;
import com.example.workmate.Adapters.RVAdapter;
import com.example.workmate.DatabaseHelper;
import com.example.workmate.R;
import com.example.workmate.Models.SupplierModel;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private static final String ARG_TEXT = "argText";
    private String text;
    private ArrayList<SupplierModel> supplierModelArrayList;
    private RVAdapter rvAdapter;
    private RecyclerView suppliersRV;
    private DatabaseHelper dbHelper;
    private MainActivity mainHelper;

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
        /*TextView textView = v.findViewById(R.id.search_prompt);

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_search, null);
        EditText searchInput = root.findViewById(R.id.searchInput);
      //  Log.d("CREATION", String.searchInput);

        //creating a list of suppliers using the search function
        DatabaseHelper databaseHelper= new DatabaseHelper(getActivity());
        List<SupplierModel> suppliers = databaseHelper.searchService(searchInput.toString());
        if(suppliers.size()>0) {
            //showing search results in a toast
            Toast.makeText(getActivity(), suppliers.toString(), Toast.LENGTH_SHORT).show();

            //showing search results in logcat
            Log.d("CREATION", String.valueOf(suppliers.get(0)));
        } else {
            //if there is nothing of this type in the database make a toast
            Toast.makeText(getActivity(), "No suppliers of this kind", Toast.LENGTH_SHORT).show();
            Log.d("CREATION", "NO SUPPLIERS OF THIS KIND");
        }

        if (getArguments() != null){
            text = getArguments().getString("ARG_TEXT");
        }

        textView.setText(text);*/
        supplierModelArrayList = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        //supplierModelArrayList = dbHelper.searchService("electrician");
        /*switch(mainHelper.option){
            case(0):
                supplierModelArrayList = dbHelper.readSuppliers();
            case(1):
                supplierModelArrayList = dbHelper.searchService("electrician");
            case(2):
                supplierModelArrayList = dbHelper.searchService("mechanic");
            case(3):
                supplierModelArrayList = dbHelper.searchService("plumber");
            case(4):
                supplierModelArrayList = dbHelper.searchService("gardener");
        }*/
        supplierModelArrayList = dbHelper.readSuppliers();

        rvAdapter = new RVAdapter(supplierModelArrayList, getActivity());
        suppliersRV = v.findViewById(R.id.idRVSuppliers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        suppliersRV.setLayoutManager(linearLayoutManager);

        suppliersRV.setAdapter(rvAdapter);

        return v;
    }
}
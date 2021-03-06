package com.example.workmate;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class SearchFragment extends Fragment {

    private static final String ARG_TEXT = "argText";
    private String text;

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
        TextView textView = v.findViewById(R.id.search_prompt);

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

        textView.setText(text);

        return v;

    }
}
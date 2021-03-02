package com.example.workmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        if (getArguments() != null){
            text = getArguments().getString("ARG_TEXT");
        }

        textView.setText(text);

        return v;

    }
}
package com.example.workmate.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmate.R;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<SupplierModel> supplierModelArrayList;
    private Context context;

    // constructor
    public RVAdapter(ArrayList<SupplierModel> supplierModelArrayList, Context context) {
        this.supplierModelArrayList = supplierModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        SupplierModel modal = supplierModelArrayList.get(position);
        holder.NameTV.setText(modal.getSupplierFname());
        holder.LNameTV.setText(modal.getSupplierLname());
        holder.AddressTV.setText(modal.getSupplierAddr());
        holder.PhoneTV.setText(modal.getSupplierPhone());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return supplierModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView NameTV, LNameTV, AddressTV, PhoneTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            NameTV = itemView.findViewById(R.id.idTVName);
            LNameTV = itemView.findViewById(R.id.idTVLName);
            AddressTV = itemView.findViewById(R.id.idTVAddress);
            PhoneTV = itemView.findViewById(R.id.idTVPhone);
        }
    }
}

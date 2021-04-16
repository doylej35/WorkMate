package com.example.workmate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private final Context context;
    // variable for our array list and context
    private ArrayList<SupplierModel> supplierModelArrayList;

    public final static String EMAIL ="com.example.message_key";

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
        holder.ServiceTV.setText(modal.getSupplierService());
        String email = modal.getSupplierEmail();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked User : " + supplierModelArrayList.get(position).getSupplierFname() + " " +
                        supplierModelArrayList.get(position).getSupplierLname(), Toast.LENGTH_SHORT).show();
                Log.d("CREATION", "Click!");
                Intent intent = new Intent(v.getContext(), SupplierProfileActivity.class);
                intent.putExtra(EMAIL, email);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return supplierModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView NameTV, LNameTV, AddressTV, PhoneTV, ServiceTV;
        public LinearLayout lv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            NameTV = itemView.findViewById(R.id.idTVName);
            LNameTV = itemView.findViewById(R.id.idTVLName);
            AddressTV = itemView.findViewById(R.id.idTVAddress);
            PhoneTV = itemView.findViewById(R.id.idTVPhone);
            ServiceTV = itemView.findViewById(R.id.idTVService);
        }
    }
}


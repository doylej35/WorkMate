package com.example.workmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmate.models.RatingsModel;
import com.example.workmate.models.SupplierModel;

import java.util.ArrayList;

public class RVRatings extends RecyclerView.Adapter<RVRatings.ViewHolder> {

    private final Context context;
    public final static String EMAIL ="com.example.message_key";

    private ArrayList<RatingsModel> RatingsModelArrayList;
    private ArrayList<SupplierModel> supplierModelArrayList;

    public RVRatings(ArrayList<RatingsModel> Ratingsearch, ArrayList<SupplierModel> supplierModelArrayList, Context context){
        this.RatingsModelArrayList = Ratingsearch;
        this.supplierModelArrayList = supplierModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RVRatings.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_rv, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVRatings.ViewHolder holder, int position) {
        //SupplierModel supplierModel = supplierModelArrayList.get(position);
        RatingsModel ratingsModel = RatingsModelArrayList.get(position);
        holder.Review.setText(ratingsModel.getRatingComment());
        holder.Star.setRating(ratingsModel.getRatingNumber());
    }

    @Override
    public int getItemCount() {
        return RatingsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Email, Fname, Lname, Service, Review;
        private RatingBar Star;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Review = itemView.findViewById(R.id.idRatText);
            Star = itemView.findViewById(R.id.idRatStar);
        }
    }
}

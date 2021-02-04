package com.example.shopclues_clone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.models.OffersResponseModel;
import com.example.shopclues_clone.utils.WishListClickListener;
import com.example.shopclues_clone.viewholder.OffersViewHolder;

import java.util.ArrayList;

public class OffersAdapter extends RecyclerView.Adapter<OffersViewHolder> {

    private ArrayList<OffersResponseModel> offersResponseModelArrayList;
    private Context context;
    private WishListClickListener wishListClickListener;

    public OffersAdapter(ArrayList<OffersResponseModel> offersResponseModelArrayList, Context context, WishListClickListener wishListClickListener) {
        this.offersResponseModelArrayList = offersResponseModelArrayList;
        this.context = context;
        this.wishListClickListener = wishListClickListener;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_item_layout, parent, false);
        return new OffersViewHolder(view, wishListClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
        OffersResponseModel offersResponseModel = offersResponseModelArrayList.get(position);
        holder.setData(offersResponseModel, context);
    }

    @Override
    public int getItemCount() {
        return offersResponseModelArrayList.size();
    }

}

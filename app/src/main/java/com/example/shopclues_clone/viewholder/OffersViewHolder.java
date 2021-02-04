package com.example.shopclues_clone.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopclues_clone.R;
import com.example.shopclues_clone.models.OffersResponseModel;
import com.example.shopclues_clone.utils.WishListClickListener;

public class OffersViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout relativeLayout;
    private CardView cardView;
    private ImageView mIvProduct;
    private TextView mTvProductName, mtvProductPrice, mTvPriceBefore, mTvOff;
    private WishListClickListener wishListClickListener;

    public OffersViewHolder(@NonNull View itemView, WishListClickListener wishListClickListener) {
        super(itemView);
        this.wishListClickListener = wishListClickListener;
        initViewsAndListeners(itemView);
    }

    private void initViewsAndListeners(View itemView) {
        relativeLayout = itemView.findViewById(R.id.rlProduct);
        cardView = itemView.findViewById(R.id.cvLike);
        mIvProduct = itemView.findViewById(R.id.ivProduct);
        mTvProductName = itemView.findViewById(R.id.tvProductName);
        mTvPriceBefore = itemView.findViewById(R.id.tvPriceBefore);
        mtvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        mTvOff = itemView.findViewById(R.id.tvOff);
    }

    public void setData(OffersResponseModel offersResponseModel, Context context){
        mTvProductName.setText(offersResponseModel.getTitle());
        mtvProductPrice.setText(offersResponseModel.getPrice() + "");
        mTvPriceBefore.setText(offersResponseModel.getPriceBefore()+"");
        mTvOff.setText(offersResponseModel.getOff() + "% off");
        Glide.with(context).load(offersResponseModel.getImage()).into(mIvProduct);

        if (cardView.isSelected()){
            cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.red));
        }else {
            cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wishListClickListener.onLiked(getAdapterPosition() );
            }
        });
    }

}

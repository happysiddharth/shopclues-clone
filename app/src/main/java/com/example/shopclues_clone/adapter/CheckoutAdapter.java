package com.example.shopclues_clone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopclues_clone.R;
import com.example.shopclues_clone.interfaces.RemoveCartItem;
import com.example.shopclues_clone.models.CartItemModel;
import com.example.shopclues_clone.models.ProductResponse;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {
    List<CartItemModel> list;
    private RemoveCartItem removeCartItem;
    public CheckoutAdapter(List<CartItemModel> list,RemoveCartItem removeCartItem) {
        this.list = list;
        this.removeCartItem = removeCartItem;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item_layout,parent,false);
        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CheckoutViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView remove,title,total_price;

        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            imageView = itemView.findViewById(R.id.product_iamge);
            remove = itemView.findViewById(R.id.remove);
            total_price = itemView.findViewById(R.id.totalPrice);
            title = itemView.findViewById(R.id.product_name);
        }
        private void setData(CartItemModel cartItemModel){
            Glide.with(imageView).load(cartItemModel.getImage()).into(imageView);
            title.setText(cartItemModel.getTitle().substring(0,15)+"...");
            total_price.setText(cartItemModel.getPrice());
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeCartItem.removeItem(cartItemModel.getCart_id());
                }
            });

        }
    }
}

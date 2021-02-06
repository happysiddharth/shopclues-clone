package com.example.shopclues_clone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopclues_clone.R;
import com.example.shopclues_clone.activities.ProductDetailPage;
import com.example.shopclues_clone.interfaces.ProductClickHandler;
import com.example.shopclues_clone.models.ProductResponse;

import java.util.List;

public class ProductpageAdapter extends RecyclerView.Adapter<ProductpageAdapter.ProductpageViewHolder> {
    List<ProductResponse> list;
    private ProductClickHandler productClickHandler;
    Context context;
    public ProductpageAdapter(List<ProductResponse> list,Context context,ProductClickHandler productClickHandler) {
        this.list = list;
        this.productClickHandler = productClickHandler;
    }

    @NonNull
    @Override
    public ProductpageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_buy_product,parent,false);
        context = parent.getContext();
        return new ProductpageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductpageViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductpageViewHolder extends RecyclerView.ViewHolder{
        private ImageView product_image;
        private LinearLayout llitem;
        private TextView product_name,product_price,off_percentage;

        public ProductpageViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            product_image = itemView.findViewById(R.id.product_iamge);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.buyingPrice);
            llitem = itemView.findViewById(R.id.llitem);
        }
        void setData(ProductResponse response){
            Glide.with(product_image).load(response.getImage()).placeholder(R.drawable.loading).into(product_image);
            product_name.setText(response.getTitle().substring(0,10));
            product_price.setText(response.getPrice().toString());
            llitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productClickHandler.detalsPage(response.getId());
                }
            });
        }
    }
}

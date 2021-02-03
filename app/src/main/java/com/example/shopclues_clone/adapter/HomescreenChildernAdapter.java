package com.example.shopclues_clone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.interfaces.CategoriesOnClickInterface;
import com.example.shopclues_clone.models.HomescreenChildernModel;
import com.example.shopclues_clone.models.HomescreenModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

public class HomescreenChildernAdapter  extends RecyclerView
        .Adapter<RecyclerView.ViewHolder> {
    private List<HomescreenChildernModel> list_childerns;
    private String type;
    private CategoriesOnClickInterface categoriesOnClickInterface;

    public HomescreenChildernAdapter(List<HomescreenChildernModel> list_childerns,String type,CategoriesOnClickInterface categoriesOnClickInterface) {
        this.type = type;
        this.list_childerns = list_childerns;
        this.categoriesOnClickInterface = categoriesOnClickInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (type){
            case HomescreenModel
                    .CATEGORIES:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homescreen_categories_child,parent,false);
                return  new CategoriesViewHolder(view);
            case HomescreenModel.SALE_LABEL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homescreen_sales_crousel,parent,false);
                return  new SaleCrouselViewHolder(view);
            case HomescreenModel.SPONSORS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homescreen_sale_child,parent,false);
                return  new SaleViewHolder(view);
            case HomescreenModel.BEST_BUY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homescreen_best_buy_child,parent,false);
                return  new BestBuyViewHolder(view);
            case HomescreenModel.RECENT_DEALS:
                break;
            case  HomescreenModel.GADGET_STORE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homescreen_gadget_child,parent,false);
                return  new GadgetStoreViewHolder(view);
            case HomescreenModel.WHATS_INDIA_BUYING_NOW:
                break;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomescreenChildernModel childernModel = list_childerns.get(position);
        switch (type){
            case HomescreenModel
                    .CATEGORIES:
                CategoriesViewHolder categoriesViewHolder = (CategoriesViewHolder)holder;
                categoriesViewHolder.title.setText(childernModel.getTitle());
                categoriesViewHolder.imageView.setImageResource(childernModel.getImage());
                categoriesViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        categoriesOnClickInterface.redirectToPariticularActivity(childernModel.getCategory());
                    }
                });
                break;
            case HomescreenModel.SALE_LABEL:
                SaleCrouselViewHolder crouselViewHolder = (SaleCrouselViewHolder)holder;
                break;
            case HomescreenModel.SPONSORS:
                SaleViewHolder saleViewHolder = (SaleViewHolder)holder;
                saleViewHolder.imageView.setImageResource(childernModel.getImage());
                break;
            case HomescreenModel.BEST_BUY:
                BestBuyViewHolder bestBuyViewHolder = (BestBuyViewHolder)holder;
                bestBuyViewHolder.price_under.setText("Under "+childernModel.getPrice());
                bestBuyViewHolder.title.setText(childernModel.getTitle());
                bestBuyViewHolder.imageView.setImageResource(childernModel.getImage());
                break;
            case HomescreenModel.RECENT_DEALS:
                break;
            case  HomescreenModel.GADGET_STORE:
                GadgetStoreViewHolder gadgetStoreViewHolder = (GadgetStoreViewHolder)holder;
                gadgetStoreViewHolder.price_under.setText("Under "+childernModel.getPrice());
                gadgetStoreViewHolder.title.setText(childernModel.getTitle());
                gadgetStoreViewHolder.imageView.setImageResource(childernModel.getImage());
                break;
            case HomescreenModel.WHATS_INDIA_BUYING_NOW:
                break;

        }
    }



    @Override
    public int getItemCount() {
        return list_childerns.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageView;
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.catLabel);
            imageView = itemView.findViewById(R.id.imageCat);

        }
    }
    public class SaleViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public SaleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_sale);
        }
    }

    private class BestBuyViewHolder extends RecyclerView.ViewHolder {
        private TextView title,price_under;
        private ImageView imageView;
        public BestBuyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView  = itemView.findViewById(R.id.imageProduct);
            title  = itemView.findViewById(R.id.productTitle);
            price_under  = itemView.findViewById(R.id.priceRange);
        }
    }

    private class SaleCrouselViewHolder extends RecyclerView.ViewHolder {
        CarouselView carouselView;
        int[] sampleImages = {R.drawable.sale_1, R.drawable.sale_2, R.drawable.sale_3};

        public SaleCrouselViewHolder(@NonNull View itemView) {
            super(itemView);
            carouselView = itemView.findViewById(R.id.carouselView);
            carouselView.setPageCount(sampleImages.length);
            carouselView.setImageListener(imageListener);
        }
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        };
    }

    private class GadgetStoreViewHolder extends RecyclerView.ViewHolder {
        private TextView title,price_under;
        private ImageView imageView;
        public GadgetStoreViewHolder(View itemView) {
            super(itemView);
            imageView  = itemView.findViewById(R.id.imageProduct);
            title  = itemView.findViewById(R.id.productTitle);
            price_under  = itemView.findViewById(R.id.priceRange);

        }
    }
}

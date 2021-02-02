package com.example.shopclues_clone.models;

import java.util.List;

public class HomescreenModel {
    private  String title;
    private List<HomescreenChildernModel> childernModels;
    private String type;
    public final static String CATEGORIES = "categories";
    public final static String SALE_LABEL = "sale_label";
    public final static String SPONSORS = "sponsors";
    public final static String BEST_BUY = "best_buy";
    public final static String RECENT_DEALS = "recent_deals";
    public final static String GADGET_STORE = "gadget_store";
    public final static String WHATS_INDIA_BUYING_NOW = "whats_india_buying_now";


    public HomescreenModel(String title, List<HomescreenChildernModel> childernModels,String type) {
        this.title = title;
        this.childernModels = childernModels;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public List<HomescreenChildernModel> getChildernModels() {
        return childernModels;
    }
}

package com.example.shopclues_clone.models;

public class HomescreenChildernModel {
    private int image;
    private String title;
    private int price;
    private String category;

    public final static String MENS_FASHION = "men clothing";
    public final static String MENS_FOOTWEAR = "men footwear";
    public final static String WOMEN_FASHION = "women clothing";
    public final static String ELECTRONICS = "electronics";
    public final static String JEWELERY = "jewelery";

    public HomescreenChildernModel(int image, String title, int price,String category) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public HomescreenChildernModel(int image, String title, int price) {
        this.image = image;
        this.title = title;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

}

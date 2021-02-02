package com.example.shopclues_clone.models;

public class HomescreenChildernModel {
    private int image;
    private String title;
    private int price;

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
}

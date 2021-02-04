package com.example.shopclues_clone.models;

import org.json.JSONException;
import org.json.JSONObject;

public class CartItemModel {
    String id;
    String cart_id;
    int quantity;
    String user_id;
    String title;
    String image;
    String price;
    public CartItemModel(String id, int quantity, String user_id,String title,String image,String price,String cart_id) {
        this.id = id;
        this.quantity = quantity;
        this.user_id = user_id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.cart_id = cart_id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getCart_id() {
        return cart_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUser_id() {
        return user_id;
    }
    public String toJSON(){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("id", getId());
            jsonObject.put("quantity", getQuantity());
            jsonObject.put("user_id", getUser_id());

            return jsonObject.toString();
        } catch ( JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }
}

package com.example.shopclues_clone.models;

import org.json.JSONException;
import org.json.JSONObject;

public class CartItemModel {
    String id;
    int quantity;
    String user_id;

    public CartItemModel(String id, int quantity, String user_id) {
        this.id = id;
        this.quantity = quantity;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
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

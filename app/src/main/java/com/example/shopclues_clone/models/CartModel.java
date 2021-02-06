package com.example.shopclues_clone.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CartModel {
    List<CartItemModel> list;

    public CartModel(List<CartItemModel> list) {
        this.list = list;
    }

    public String toJSON() {
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<list.size();i++){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonArray.put(jsonArray);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
       return jsonArray.toString();


    }
}

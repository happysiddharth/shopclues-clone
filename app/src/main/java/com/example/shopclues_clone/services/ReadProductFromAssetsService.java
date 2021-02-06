package com.example.shopclues_clone.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.fragments.ProductsFragment;
import com.example.shopclues_clone.models.ProductResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ReadProductFromAssetsService extends IntentService {
    final static String SORT_AECS = "ascending";
    final static String SORT_DECS = "descending";

    private String sorting_type ;
    private String search_query;
    private List<ProductResponse> list;



    public ReadProductFromAssetsService(String sorting_type,String search_query,List<ProductResponse> list) {
        super("ReadProductFromAssetsService");
        this.search_query =search_query;
        this.sorting_type = sorting_type;
        this.list = list;

    }



    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            InputStream stream = getAssets().open("product.json");
            StringBuffer stringBuffer = new StringBuffer();
            int data = stream.read();
            while (data!=-1){
                char c = (char)data;
                data = stream.read();
                stringBuffer.append(c);
            }
            JSONArray jsonArray = new JSONArray(stringBuffer.toString());
            Log.d("Sidd",jsonArray.length()+"");
            list = new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProductResponse response = new ProductResponse();
                response.setCategory(jsonObject.get("category").toString());
                response.setTitle(jsonObject.get("title").toString());
                response.setDescription(jsonObject.get("description").toString());
                response.setImage(jsonObject.get("image").toString());
                response.setPrice(jsonObject.get("price").toString());
                response.setId(Integer.parseInt(jsonObject.get("id").toString()));
                list.add(response);
            }
            Log.d("SIdd",list.size()+" list");
            Bundle bundle = new Bundle();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

}
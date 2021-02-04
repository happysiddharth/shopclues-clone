package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.adapter.OffersAdapter;
import com.example.shopclues_clone.models.OffersResponseModel;
import com.example.shopclues_clone.utils.WishListClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class OffersActivity extends AppCompatActivity implements WishListClickListener {

    private RecyclerView recyclerView;
    private ArrayList<OffersResponseModel> offersResponseModelArrayList = new ArrayList<>();
    OffersAdapter offersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        recyclerView = findViewById(R.id.offersRecyclerView);
        fetchResponseFromAsset();
    }

    private void fetchResponseFromAsset() {
        try {
            InputStream inputStream = getAssets().open("offersresponse.json");
            int data = inputStream.read();
            StringBuffer stringBuffer = new StringBuffer();
            while (data != -1) {
                char ch = (char) data;
                stringBuffer.append(ch);
                data = inputStream.read();
            }

            buildPOJOFromJSON(stringBuffer.toString());

        } catch (Exception e) {

        }
    }

    private void buildPOJOFromJSON(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<OffersResponseModel>>() {
        }.getType();
        Log.d("Prabin", "1 " + offersResponseModelArrayList.size());
        offersResponseModelArrayList = gson.fromJson(json, type);
        Log.d("Prabin", "2 " + offersResponseModelArrayList.size());
        setRecyclerAdapter(offersResponseModelArrayList, this);
    }

    private void setRecyclerAdapter(ArrayList<OffersResponseModel> offersResponseModelArrayList, WishListClickListener wishListClickListener) {
        offersAdapter = new OffersAdapter(offersResponseModelArrayList, OffersActivity.this, wishListClickListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(OffersActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(offersAdapter);
    }

    @Override
    public void onLiked(int position) {
        Toast.makeText(this, "item liked at : " + position, Toast.LENGTH_SHORT).show();
        offersAdapter.notifyItemChanged(position);
    }
}
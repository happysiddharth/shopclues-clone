package com.example.shopclues_clone.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.adapter.ProductpageAdapter;
import com.example.shopclues_clone.fragments.ProductsFragment;
import com.example.shopclues_clone.models.ProductResponse;
import com.example.shopclues_clone.utils.RunnabelToReadProductFromAssets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductsPage extends AppCompatActivity {

    private List<ProductResponse> list = new ArrayList<>();
    private ProductpageAdapter productpageAdapter;
    private String categories="";
    private Button gotoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_page);
        gotoCart = findViewById(R.id.button14);
        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCart = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(goToCart);
            }
        });

        if (getIntent().getExtras().containsKey("category")){
            categories = getIntent().getExtras().get("category").toString();
        }

        RunnabelToReadProductFromAssets runnabelToReadProductFromAssets = new RunnabelToReadProductFromAssets();
        list = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
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

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (categories.isEmpty()) {
                            ProductResponse response = new ProductResponse();
                            response.setCategory(jsonObject.get("category").toString());
                            response.setTitle(jsonObject.get("title").toString());
                            response.setDescription(jsonObject.get("description").toString());
                            response.setImage(jsonObject.get("image").toString());
                            response.setPrice(jsonObject.get("price").toString());
                            response.setId(Integer.parseInt(jsonObject.get("id").toString()));
                            list.add(response);
                        }else{
                            if (categories.equals(jsonObject.get("category").toString())){
                                ProductResponse response = new ProductResponse();
                                response.setCategory(jsonObject.get("category").toString());
                                response.setTitle(jsonObject.get("title").toString());
                                response.setDescription(jsonObject.get("description").toString());
                                response.setImage(jsonObject.get("image").toString());
                                response.setPrice(jsonObject.get("price").toString());
                                response.setId(Integer.parseInt(jsonObject.get("id").toString()));
                                list.add(response);
                            }
                        }

                    }
//

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                Log.d("SIdd",list.size()+"");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        ProductsFragment productsFragment = new ProductsFragment(list);
                        fragmentTransaction.add(R.id.fragment,productsFragment,"productpage").commit();
                    }
                });

            }
        });

        thread.start();
    }
}
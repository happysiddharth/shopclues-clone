package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class SearchActivity extends AppCompatActivity {
    private String q="";
    private TextView appbar_title;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        appbar_title = findViewById(R.id.textView17);
        btn_back = findViewById(R.id.button20);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent()!=null){
            q = getIntent().getExtras().get("query").toString();
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
                        List<ProductResponse> list = new ArrayList<>();
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            ProductResponse response = new ProductResponse();
                            response.setCategory(jsonObject.get("category").toString());

                            response.setTitle(jsonObject.get("title").toString());
                            response.setDescription(jsonObject.get("description").toString());
                            response.setImage(jsonObject.get("image").toString());
                            response.setPrice(jsonObject.get("price").toString());
                            response.setId(Integer.parseInt(jsonObject.get("id").toString()));
                            if (jsonObject.get("title").toString().contains(q)){
                                list.add(response);

                            }
                        }
                        Log.d("SIdd",list.size()+" list");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                appbar_title.setText(q);

                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                ProductsFragment productsFragment = new ProductsFragment(list);
                                fragmentTransaction
                                        .replace(R.id.framel,productsFragment,"id")
                                        .commit();   }
                        });

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

    }
}
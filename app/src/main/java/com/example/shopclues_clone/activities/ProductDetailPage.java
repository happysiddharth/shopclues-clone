package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopclues_clone.R;
import com.example.shopclues_clone.adapter.ProductpageAdapter;
import com.example.shopclues_clone.interfaces.ProductClickHandler;
import com.example.shopclues_clone.models.CartItemModel;
import com.example.shopclues_clone.models.CartModel;
import com.example.shopclues_clone.models.ProductResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailPage extends AppCompatActivity implements ProductClickHandler {
    private ImageView productImage;
    private TextView title,price;
    private ProductResponse response_main;
    private String id;
    private Button addToCarTBtn;
    private Button gotoCartBtn;
    private Button gotoCartBtn2;
    private Button backBtn;
    private Button btnBuy;
    private RecyclerView recyclerView;
    private String categories;
    private List<ProductResponse> list = new ArrayList<>();
    private Runnable runnable = new Runnable() {
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
                response_main = new ProductResponse();

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
                    if (id.equals(jsonObject.get("id").toString())){
                        response_main.setCategory(jsonObject.get("category").toString());
                        response_main.setTitle(jsonObject.get("title").toString());
                        response_main.setDescription(jsonObject.get("description").toString());
                        response_main.setImage(jsonObject.get("image").toString());
                        response_main.setPrice(jsonObject.get("price").toString());
                        response_main.setId(Integer.parseInt(jsonObject.get("id").toString()));
                    }


                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Sidd",response_main.getImage());
                        Glide.with(productImage).load(response_main.getImage()).into(productImage);
                        title.setText(response_main.getTitle());
                        price.setText(response_main.getPrice().toString());
                    }
                });
//

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_page2);
        init();
        id = getIntent().getExtras().get("id").toString();
        Thread thread1 = new Thread(runnable);
        thread1.start();

    }

    private void init() {
        productImage = findViewById(R.id.imageView4);
        title = findViewById(R.id.tilteTv);
        price = findViewById(R.id.productPrice);
        addToCarTBtn = findViewById(R.id.addToCarTBtn);
        gotoCartBtn = findViewById(R.id.gotoCartBtn);
        gotoCartBtn2 = findViewById(R.id.gotoCartBtn2);
        backBtn = findViewById(R.id.backBtn);
        btnBuy = findViewById(R.id.button7);
        recyclerView = findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        ProductpageAdapter productpageAdapter = new ProductpageAdapter(list,getApplicationContext(),this);
        recyclerView.setAdapter(productpageAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        gotoCartBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCart();
                Intent intent = new Intent(getApplicationContext(),AddAddressActivity.class);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addToCarTBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Sidd","clicked");
                readCart();
            }
        });
    }
    private void readCart(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                   File folder = new File(getFilesDir()+File.separator,"data");
                   if (!folder.exists()){
                       folder.mkdir();
                   }
                   File file = new File(folder,"cart.txt");
                   if (!file.exists()){
                       file.createNewFile();
                   }

                   FileInputStream inputStream = new FileInputStream(file);
                   InputStreamReader inputStreamReader =new InputStreamReader(inputStream);
                   int data = inputStreamReader.read();
                   StringBuffer stringBuffer = new StringBuffer();
                   while(data!=-1){
                       char c = (char)data;
                       stringBuffer.append(c);
                       data = inputStreamReader.read();
                   }
                   Long tsLong = System.currentTimeMillis()/1000;
                   String ts = tsLong.toString();
                   if (!stringBuffer.toString().equals("")) {
                       JSONArray jsonArray = new JSONArray(stringBuffer.toString());
                       List<CartItemModel> cartItemModels = new ArrayList<>();
                         Log.d("Sidd",jsonArray.length()+"");
                       for (int i = 0; i < jsonArray.length(); i++) {
                           JSONObject object = new JSONObject(jsonArray.get(i).toString());
                           cartItemModels.add(new CartItemModel(object.get("id").toString(), 1, object.get("user_id").toString(), object.get("title").toString(), object.get("image").toString(),object.get("price").toString(), object.get("cart_id").toString()));
                       }

                       cartItemModels.add(new CartItemModel(id, 1, "id", response_main.getTitle(), response_main.getImage(),response_main.getPrice().toString(),ts));


                       FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                       OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                       Gson gson = new GsonBuilder().setPrettyPrinting().create();
                       String g = gson.toJson(cartItemModels);
                       outputStreamWriter.append(g);
                       outputStreamWriter.close();
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               gotoCartBtn.setText((jsonArray.length()+1)+"");
                               gotoCartBtn2.setVisibility(View.VISIBLE);
                               addToCarTBtn.setVisibility(View.GONE);
                           }
                       });
                   }else{

                       List<CartItemModel> cartItemModels = new ArrayList<>();


                       cartItemModels.add(new CartItemModel(id, 1, "id", response_main.getTitle(), response_main.getImage(),response_main.getPrice().toString(),ts));


                       FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                       OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                       Gson gson = new GsonBuilder().setPrettyPrinting().create();
                       String g = gson.toJson(cartItemModels);
                       outputStreamWriter.append(g);
                       outputStreamWriter.close();
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               gotoCartBtn.setText((1)+"");
                               gotoCartBtn2.setVisibility(View.VISIBLE);
                               addToCarTBtn.setVisibility(View.GONE);
                           }
                       });

                   }
                   inputStreamReader.close();


               }catch (Exception e){

               }
            }
        });
        thread.start();
    }

    @Override
    public void detalsPage(int id) {
        Intent intent = new Intent(getApplicationContext(), ProductDetailPage.class);
        intent.putExtra("id",id+"");
        startActivity(intent);
    }
}
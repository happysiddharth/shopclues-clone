package com.example.shopclues_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.icu.math.BigDecimal;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.shopclues_clone.activities.CartActivity;
import com.example.shopclues_clone.activities.ProductsPage;
import com.example.shopclues_clone.adapter.CheckoutAdapter;
import com.example.shopclues_clone.fragments.HomescreenFragment;
import com.example.shopclues_clone.fragments.ProductsFragment;
import com.example.shopclues_clone.fragments.TestingFragment;
import com.example.shopclues_clone.interfaces.BottomNavigationToggle;
import com.example.shopclues_clone.models.CartItemModel;
import com.example.shopclues_clone.models.ProductResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationToggle {
    private FrameLayout frameLayout ;
    private Button gotocart;
    private BottomNavigationView bottomNavigationView ;
    private EditText pinCodeEditText;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("Sidd","run");
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
                JSONArray jsonArray = new JSONArray(stringBuffer.toString());
                List<CartItemModel> list = new ArrayList<>();

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());


                    list.add(new CartItemModel(jsonObject.get("id").toString(),1,"",jsonObject.get("title").toString(),jsonObject.get("image").toString(),jsonObject.get("price").toString(),jsonObject.get("cart_id").toString()));
                    //Map<Integer,CartItemModel> item = new HashMap<>();
//                    response.put(Integer.parseInt(jsonObject.get("id").toString()), item);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotocart.setText(list.size()+"");
                    }
                });
            }catch (Exception r){

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setHomescreenFragment();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void setHomescreenFragment() {
        FragmentTransaction homepageTransaction = fragmentManager.beginTransaction();
        HomescreenFragment homescreenFragment = new HomescreenFragment(this);
        homepageTransaction.add(R.id.homescreenFrameLayout,homescreenFragment,"homepage").commit();
    }

    private void init() {
        frameLayout = findViewById(R.id.homescreenFrameLayout);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        pinCodeEditText = findViewById(R.id.editTextTextPersonName);
        gotocart = findViewById(R.id.button2);
        gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocart = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(gotocart);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_account:
                        setFrameLayout(new TestingFragment());
                       break;
                    case R.id.action_recents:
                        Log.d("Sidd","home");
                        FragmentTransaction homepageTransaction = fragmentManager.beginTransaction();
                        HomescreenFragment homescreenFragment = new HomescreenFragment(MainActivity.this);
                        homepageTransaction.replace(R.id.homescreenFrameLayout,homescreenFragment,"homepage").commit();

//                        FragmentTransaction homepageTransaction = fragmentManager.beginTransaction();
//                        HomescreenFragment homescreenFragment = new HomescreenFragment(MainActivity.this);
//                        homepageTransaction.add(R.id.homescreenFrameLayout,homescreenFragment,"homepage").commit();
                        break;
                    case R.id.action_offers:
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
                                        // "id": 2,
                                        //    "title": "Mens Casual Premium Slim Fit T-Shirts ",
                                        //    "price": 22.3,
                                        //    "description": "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
                                        //    "category": "men clothing",
                                        //    "image":
                                        response.setCategory(jsonObject.get("category").toString());
                                        response.setTitle(jsonObject.get("title").toString());
                                        response.setDescription(jsonObject.get("description").toString());
                                        response.setImage(jsonObject.get("image").toString());
                                        response.setPrice(jsonObject.get("price").toString());
                                        response.setId(Integer.parseInt(jsonObject.get("id").toString()));
                                        list.add(response);
                                    }
                                    Log.d("SIdd",list.size()+" list");
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                             ProductsFragment productsFragment = new ProductsFragment(list);
                                            fragmentTransaction
                                                    .replace(R.id.homescreenFrameLayout,productsFragment,"id")
                                                    .addToBackStack("random")
                                                    .commit();   }
                                    });

                                } catch (IOException | JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                        break;
                }
                return true;
            }
        });
    }
    void setFrameLayout(Fragment frameLayout){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.homescreenFrameLayout,frameLayout,"id")
                .addToBackStack("random")
                .commit();
    }


    @Override
    public void togggle(String from, int to) {
        bottomNavigationView.setSelectedItemId(to);
    }


}
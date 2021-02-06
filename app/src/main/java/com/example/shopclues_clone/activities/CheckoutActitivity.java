package com.example.shopclues_clone.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.math.BigDecimal;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.adapter.CheckoutAdapter;
import com.example.shopclues_clone.fragments.AddressKeys;
import com.example.shopclues_clone.interfaces.RemoveCartItem;
import com.example.shopclues_clone.models.CartItemModel;
import com.example.shopclues_clone.models.ProductResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import javax.xml.transform.Templates;

public class CheckoutActitivity extends AppCompatActivity implements RemoveCartItem {
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private Button nextBtn;
    private TextView checkoutTotal,discount_amount,pricetotal,payableAmount,remove;
    private CheckoutAdapter checkoutAdapter;
    private Map<Integer,Map<Integer, CartItemModel>> response = new HashMap<>();
    private List<CartItemModel> list =  new ArrayList<>();
    double cart_total = 0;
    double  discount = 30;
    private String pincode,fullname,phonenumber,flatno,area_code,city,state;
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


                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());

                    cart_total = cart_total+ Double.parseDouble(jsonObject.get("price").toString());

                    list.add(new CartItemModel(jsonObject.get("id").toString(),1,"",jsonObject.get("title").toString(),jsonObject.get("image").toString(),jsonObject.get("price").toString(),jsonObject.get("cart_id").toString()));
                    //Map<Integer,CartItemModel> item = new HashMap<>();
//                    response.put(Integer.parseInt(jsonObject.get("id").toString()), item);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    cart_total = BigDecimal.valueOf(cart_total).setScale(3, 5)
                            .doubleValue();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        checkoutTotal.setText(cart_total+"");
                        discount_amount.setText(discount+"");
                        pricetotal.setText(cart_total+"");
                        payableAmount.setText(BigDecimal.valueOf((cart_total-discount)).setScale(3, 5)
                                .doubleValue()+"");

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        checkoutAdapter = new CheckoutAdapter(list,CheckoutActitivity.this);

                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(checkoutAdapter);
                    }
                });
            }catch (Exception r){

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_actitivity);
        init();
        if (getIntent()!=null){
            if (!getIntent().getExtras().get(AddressKeys.PIN_CODE).toString().equals("")){
                pincode = getIntent().getExtras().get(AddressKeys.PIN_CODE).toString();
            }
            if (!getIntent().getExtras().get(AddressKeys.FULL_NAME).toString().equals("")){
                fullname  = getIntent().getExtras().get(AddressKeys.FULL_NAME).toString();
            }
            if (!getIntent().getExtras().get(AddressKeys.PHONE_NUMBER).toString().equals("")){
                phonenumber = getIntent().getExtras().get(AddressKeys.PHONE_NUMBER).toString();
            }
            if (!getIntent().getExtras().get(AddressKeys.FLAT_NO).toString().equals("")){
                flatno = getIntent().getExtras().get(AddressKeys.FLAT_NO).toString();
            }
            if (!getIntent().getExtras().get(AddressKeys.AREA_CODE).toString().equals("")){
                area_code = getIntent().getExtras().get(AddressKeys.AREA_CODE).toString();
            }
            if (!getIntent().getExtras().get(AddressKeys.CITY).toString().equals("")){
                city = getIntent().getExtras().get(AddressKeys.CITY).toString();
            }
            if (!getIntent().getExtras().get(AddressKeys.STATE).toString().equals("")){
                state = getIntent().getExtras().get(AddressKeys.STATE).toString();
            }
        }
        Thread thread = new Thread(runnable);
        thread.start();


    }

    private void init() {
        recyclerView = findViewById(R.id.rv);
        checkoutTotal = findViewById(R.id.textView4);
        //discount_amount,pricetotal,payableAmount
        linearLayout = findViewById(R.id.ll);
        nextBtn = findViewById(R.id.button9);

        pricetotal = findViewById(R.id.priceTotal);
        discount_amount = findViewById(R.id.discountAmount);
        payableAmount = findViewById(R.id.payable_amount);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PaymentPage.class);
                intent.putExtra(AddressKeys.PIN_CODE,pincode);
                intent.putExtra(AddressKeys.FULL_NAME,fullname);
                intent.putExtra(AddressKeys.PHONE_NUMBER,phonenumber);
                intent.putExtra(AddressKeys.FLAT_NO,flatno);
                intent.putExtra(AddressKeys.AREA_CODE,area_code);
                intent.putExtra(AddressKeys.CITY,city);
                intent.putExtra(AddressKeys.STATE,state);
                startActivity(intent);
            }
        });

    }

    @Override
    public void removeItem(String cart_id) {
        Log.d("Sidd",cart_id+"cart");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.removeIf(new Predicate<CartItemModel>() {
                @Override
                public boolean test(CartItemModel cartItemModel) {
                    if (cartItemModel.getCart_id().equals(cart_id)){
                        cart_total = BigDecimal.valueOf(cart_total-Double.parseDouble(cartItemModel.getPrice())).setScale(3, 5)
                                .doubleValue();
                        checkoutTotal.setText(cart_total+"");
                        payableAmount.setText(BigDecimal.valueOf((cart_total-discount)).setScale(3, 5)
                                .doubleValue()+"");
                        pricetotal.setText(cart_total+"");
                    }
                    return cartItemModel.getCart_id().equals(cart_id);
                }
            });
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    removeItemFromInternalStoreage();
                }
            });thread.start();
            if (list.size()==0){
                linearLayout.setVisibility(View.GONE);

            }
            checkoutAdapter.notifyDataSetChanged();

        }
    }
    private void removeItemFromInternalStoreage(){
      try{
          File folder = new File(getFilesDir()+File.separator,"data");
          if (!folder.exists()){
              folder.mkdir();
          }
          File file = new File(folder,"cart.txt");
          if (!file.exists()){
              file.createNewFile();
          }
          FileOutputStream fileOutputStream = new FileOutputStream(file, false);
          OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
          Gson gson = new GsonBuilder().setPrettyPrinting().create();
          String g = gson.toJson(list);
          outputStreamWriter.append(g);
          outputStreamWriter.close();
      }catch (Exception e){

      }
    }
}
package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.fragments.AddressKeys;
import com.example.shopclues_clone.models.AddressModel;
import com.example.shopclues_clone.models.CartItemModel;
import com.example.shopclues_clone.models.OrderModel;
import com.example.shopclues_clone.utils.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class EnterOtpActivity extends AppCompatActivity {
    private Button placeOrder;
    private EditText otp;
    private String OTP = "1234";
    private String number  = "";
    private String pincode,fullname,phonenumber,flatno,area_code,city,state;

    private Runnable runnable = new Runnable() {
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
                while(data!=-1) {
                    char c = (char) data;
                    stringBuffer.append(c);
                    data = inputStreamReader.read();
                }
                List<CartItemModel> cartItemModels = new ArrayList<>();

                if (!stringBuffer.toString().equals("")) {
                    JSONArray jsonArray = new JSONArray(stringBuffer.toString());
                    Log.d("Sidd",jsonArray.length()+"");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = new JSONObject(jsonArray.get(i).toString());
                        cartItemModels.add(new CartItemModel(object.get("id").toString(), 1, object.get("user_id").toString(), object.get("title").toString(), object.get("image").toString(),object.get("price").toString(), object.get("cart_id").toString()));
                    }



                    List<OrderModel> list_of_order = new ArrayList<>();

                    for (int i=0;i<cartItemModels.size();i++){
                        Long tsLong = (System.currentTimeMillis()+1)/1000;
                        String ts = tsLong.toString();
                        JSONObject cartItemModel = new JSONObject(jsonArray.get(i).toString());
                        list_of_order.add(new OrderModel(ts,"1",getIntent().getExtras().get("data").toString(),OrderModel.STATUS_PENDING,
                                new AddressModel(phonenumber,pincode,flatno,area_code,area_code,city,"state",state),
                                cartItemModels.get(i)));
                    }

                    File order_file = new File(folder,"orders.txt");
                    if (!order_file.exists()){
                        order_file.createNewFile();
                    }

                    FileInputStream read_order = new FileInputStream(order_file);
                    InputStreamReader read_order_inputStreamReader =new InputStreamReader(read_order);
                    int data_order = read_order_inputStreamReader.read();
                    StringBuffer stringBuffer_order = new StringBuffer();
                    while(data_order!=-1) {
                        char c = (char) data_order;
                        stringBuffer_order.append(c);
                        data_order = read_order_inputStreamReader.read();
                    }
                    List<OrderModel> order_temp = new ArrayList<>();
                    if (stringBuffer_order.length()!=0){

                        JSONArray jsonArray_orders = new JSONArray(stringBuffer_order.toString());
                        Log.d("Sidd",jsonArray_orders.length()+"");
                        for (int i = 0; i < jsonArray_orders.length(); i++) {
                            JSONObject object = new JSONObject(jsonArray_orders.get(i).toString());
                            Gson gson = new Gson();
                            OrderModel orderModel= gson.fromJson(object.toString(),OrderModel.class);
                            order_temp.add(orderModel);
                        }
                    }
                    read_order.close();
                    read_order_inputStreamReader.close();

                    list_of_order.addAll(order_temp);


                    FileOutputStream fileOutputStream = new FileOutputStream(order_file, false);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String g = gson.toJson(list_of_order);
                    outputStreamWriter.append(g);
                    outputStreamWriter.close();

                    FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter2.append("");
                    outputStreamWriter2.close();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent in = new Intent(getApplicationContext(),CongratulationActivity.class);
                            startActivity(in);
                        }
                    });
                }else{

                }
                inputStreamReader.close();


            }catch (Exception e){

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
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
        SharedPreferences.getsharePreferences(getApplicationContext());
        placeOrder = findViewById(R.id.button11);
        otp = findViewById(R.id.editTextNumber);

        if (getIntent()!=null){
            number = getIntent().getExtras().get("data").toString();
        }
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otp.getText().toString().equals(OTP)){
      //             SharedPreferences.writeStringToPrefernces("user_phone",number);
                   Thread thread = new Thread(runnable);
                   thread.start();
//                    Intent in = new Intent(getApplicationContext(),CongratulationActivity.class);
//                    startActivity(in);
                }else {
                    otp.setError("Wrong otp");
                }

            }
        });
    }
}
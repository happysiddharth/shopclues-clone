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
import com.example.shopclues_clone.models.ProductResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {
    private EditText pincode,fullname,phonenumber,flatno,areacode,city,state;
    private Button continueBtn;
    private Map<Integer,ProductDetailPage> map_product = new HashMap<>();
    private List<ProductResponse> list =  new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address2);
        init();
    }

    private void init() {
        pincode = findViewById(R.id.etPinCode);
        fullname = findViewById(R.id.etFullName);
        phonenumber = findViewById(R.id.etPhone);
        flatno = findViewById(R.id.etHno);
        areacode = findViewById(R.id.etArea);
        city = findViewById(R.id.etCity);
        state = findViewById(R.id.etState);
        continueBtn = findViewById(R.id.Btncontinue);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()){
                    Intent intent =  new Intent(getApplicationContext(),CheckoutActitivity.class);
                    intent.putExtra(AddressKeys.PIN_CODE,pincode.getText().toString());
                    intent.putExtra(AddressKeys.FULL_NAME,fullname.getText().toString());
                    intent.putExtra(AddressKeys.PHONE_NUMBER,phonenumber.getText().toString());
                    intent.putExtra(AddressKeys.FLAT_NO,flatno.getText().toString());
                    intent.putExtra(AddressKeys.AREA_CODE,areacode.getText().toString());
                    intent.putExtra(AddressKeys.CITY,city.getText().toString());
                    intent.putExtra(AddressKeys.STATE,state.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isValidate() {
        boolean flag = true;
        if (pincode.getText().toString().isEmpty()){
            flag = false;
            pincode.setError("Enter pincode");
        }
        if (fullname.getText().toString().isEmpty()){
            flag = false;
            fullname.setError("error");
        }
        if (phonenumber.getText().toString().isEmpty()){
            flag = false;
            phonenumber.setError("Enter phonenumber");
        }
        if (flatno.getText().toString().isEmpty()){
            flag = false;
            flatno.setError("Enter flatno");
        }
        if (areacode.getText().toString().isEmpty()){
            flag = false;
            areacode.setError("Enter areacode");
        }
        if (city.getText().toString().isEmpty()){
            flag = false;
            city.setError("Enter city");
        }
        if (state.getText().toString().isEmpty()){
            flag = false;
            state.setError("Enter state");
        }
        return flag;
    }
}
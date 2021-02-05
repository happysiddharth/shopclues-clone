package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.fragments.AddressKeys;

public class EnterPhoneNumberActivity extends AppCompatActivity {
    private Button nextPage;
    private EditText number;
    private String pincode,fullname,phonenumber,flatno,area_code,city,state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phone_number);
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
        nextPage = findViewById(R.id.button10);
        number = findViewById(R.id.editTextPhone);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnterOtpActivity.class);
                intent.putExtra("data",number.getText().toString());
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
}
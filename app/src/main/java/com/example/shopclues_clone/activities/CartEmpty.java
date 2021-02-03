package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopclues_clone.R;

public class CartEmpty extends AppCompatActivity {

    private TextView mTvCartEmpty;
    private Button mBtnContinueShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_empty);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mTvCartEmpty = findViewById(R.id.tvCartEmpty);
        mBtnContinueShopping = findViewById(R.id.btnContinueShopping);
        mTvCartEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtnContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
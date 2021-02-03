package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shopclues_clone.R;

public class Notifications extends AppCompatActivity {

    private TextView mTvNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        initViews();
    }

    private void initViews() {
        mTvNotification = findViewById(R.id.tvNotifications);
        mTvNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
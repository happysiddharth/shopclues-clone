package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shopclues_clone.R;

public class CongratulationActivity extends AppCompatActivity {
    private Button myorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        init();
    }

    private void init() {
        myorder = findViewById(R.id.button12);
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MyOrdersActivity.class);
                startActivity(intent);
            }
        });
    }
}
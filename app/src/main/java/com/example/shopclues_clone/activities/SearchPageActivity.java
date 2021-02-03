package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shopclues_clone.R;

public class SearchPageActivity extends AppCompatActivity {

    private EditText mETStayHomeAndExplore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        initViews();
    }

    private void initViews() {
        mETStayHomeAndExplore = findViewById(R.id.etStayHomeAndExplore);
        mETStayHomeAndExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
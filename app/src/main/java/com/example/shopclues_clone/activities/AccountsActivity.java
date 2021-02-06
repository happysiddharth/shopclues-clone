package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.fragments.AccountSignUpFragment;
import com.example.shopclues_clone.fragments.UserDetailsFragment;

public class AccountsActivity extends AppCompatActivity {
    private TextView myOrders ;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        myOrders = findViewById(R.id.tvMyOrders);
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MyOrdersActivity.class);
                startActivity(intent);
            }
        });
        launchAccountSignUpFragment();

        if (getIntent() != null && getIntent().getStringExtra("email") != null) {
            launchUserDetailsFragment();
        } else {
            launchAccountSignUpFragment();
        }
    }

    private void launchUserDetailsFragment() {
        String email = getIntent().getStringExtra("email");
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
        userDetailsFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.flContainer, userDetailsFragment, "UserDetailsFragment").commit();
    }

    private void launchAccountSignUpFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AccountSignUpFragment accountSignUpFragment = new AccountSignUpFragment();
        fragmentTransaction.add(R.id.flContainer, accountSignUpFragment, "AccountSignUpFragment").commit();
    }
}
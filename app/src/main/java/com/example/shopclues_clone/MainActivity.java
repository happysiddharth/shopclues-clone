package com.example.shopclues_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.shopclues_clone.fragments.HomescreenFragment;
import com.example.shopclues_clone.fragments.TestingFragment;
import com.example.shopclues_clone.interfaces.BottomNavigationToggle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationToggle {
    private FrameLayout frameLayout ;
    private BottomNavigationView bottomNavigationView ;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setHomescreenFragment();
    }

    private void setHomescreenFragment() {
        FragmentTransaction homepageTransaction = fragmentManager.beginTransaction();
        HomescreenFragment homescreenFragment = new HomescreenFragment(this);
        homepageTransaction.add(R.id.homescreenFrameLayout,homescreenFragment,"homepage").commit();
    }

    private void init() {
        frameLayout = findViewById(R.id.homescreenFrameLayout);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_account:
                        setFrameLayout(new TestingFragment());
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
package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.fragments.EssentialsFragment;
import com.example.shopclues_clone.fragments.GadgetsFragment;
import com.example.shopclues_clone.fragments.KidsFashionFragment;
import com.example.shopclues_clone.fragments.MenFashionFragment;
import com.example.shopclues_clone.fragments.WomenFashionFragment;

public class CategoriesActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private CardView mCvMenFashion, mCvWomenFashion, mcvKidsFashion, mCvGadgets, mCvEssentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        startMenFashionFragment();
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mCvMenFashion = findViewById(R.id.cvMenFashion);
        mCvWomenFashion = findViewById(R.id.cvWomenFashion);
        mcvKidsFashion = findViewById(R.id.cvKidsFashion);
        mCvGadgets = findViewById(R.id.cvGadgets);
        mCvEssentials = findViewById(R.id.cvEssentials);

        mCvMenFashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMenFashionFragment();
            }
        });

        mCvWomenFashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWomenFashionFragment();
            }
        });

        mcvKidsFashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startKidsFashionFragment();
            }
        });

        mCvGadgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGadgetsFragment();
            }
        });

        mCvEssentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEssentialsFragment();
            }
        });
    }

    private void startMenFashionFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MenFashionFragment menFashionFragment = new MenFashionFragment();
        fragmentTransaction.replace(R.id.flContainer, menFashionFragment, "MenFashionFragment").commit();
    }

    private void startWomenFashionFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WomenFashionFragment womenFashionFragment = new WomenFashionFragment();
        fragmentTransaction.replace(R.id.flContainer, womenFashionFragment, "WomenFashionFragment").commit();
    }

    private void startKidsFashionFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        KidsFashionFragment kidsFashionFragment = new KidsFashionFragment();
        fragmentTransaction.replace(R.id.flContainer, kidsFashionFragment, "KidsFashionFragment").commit();
    }

    private void startGadgetsFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GadgetsFragment gadgetsFragment = new GadgetsFragment();
        fragmentTransaction.replace(R.id.flContainer, gadgetsFragment, "GadgetsFragment").commit();
    }

    private void startEssentialsFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EssentialsFragment essentialsFragment = new EssentialsFragment();
        fragmentTransaction.replace(R.id.flContainer, essentialsFragment, "EssentialsFragment").commit();
    }
}
package com.example.shopclues_clone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopclues_clone.R;

public class CategoriesFragment extends Fragment {

    private FragmentManager fragmentManager = getFragmentManager();
    private CardView mCvMenFashion, mCvWomenFashion, mcvKidsFashion, mCvGadgets, mCvEssentials;
    public CategoriesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startMenFashionFragment();
        initViewsAndListeners(view);
    }

    private void initViewsAndListeners(View view) {
        mCvMenFashion = view.findViewById(R.id.cvMenFashion);
        mCvWomenFashion = view.findViewById(R.id.cvWomenFashion);
        mcvKidsFashion = view.findViewById(R.id.cvKidsFashion);
        mCvGadgets = view.findViewById(R.id.cvGadgets);
        mCvEssentials = view.findViewById(R.id.cvEssentials);

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
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        MenFashionFragment menFashionFragment = new MenFashionFragment();
        fragmentTransaction.replace(R.id.flContainer, menFashionFragment, "MenFashionFragment").commit();
    }

    private void startWomenFashionFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        WomenFashionFragment womenFashionFragment = new WomenFashionFragment();
        fragmentTransaction.replace(R.id.flContainer, womenFashionFragment, "WomenFashionFragment").commit();
    }

    private void startKidsFashionFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        KidsFashionFragment kidsFashionFragment = new KidsFashionFragment();
        fragmentTransaction.replace(R.id.flContainer, kidsFashionFragment, "KidsFashionFragment").commit();
    }

    private void startGadgetsFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        GadgetsFragment gadgetsFragment = new GadgetsFragment();
        fragmentTransaction.replace(R.id.flContainer, gadgetsFragment, "GadgetsFragment").commit();
    }

    private void startEssentialsFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        EssentialsFragment essentialsFragment = new EssentialsFragment();
        fragmentTransaction.replace(R.id.flContainer, essentialsFragment, "EssentialsFragment").commit();
    }
}
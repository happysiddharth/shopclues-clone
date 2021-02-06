package com.example.shopclues_clone.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.activities.ProductDetailPage;
import com.example.shopclues_clone.adapter.ProductpageAdapter;
import com.example.shopclues_clone.interfaces.ProductClickHandler;
import com.example.shopclues_clone.models.ProductResponse;

import java.util.Comparator;
import java.util.List;


public class ProductsFragment extends Fragment implements ProductClickHandler {
    private RecyclerView recyclerView;
    private List<ProductResponse> list;
    private    ProductpageAdapter productpageAdapter;
    private Button price_sort;
    private Button price_sort2;
    private Button sort_options;
    public ProductsFragment(List<ProductResponse> list) {
        // Required empty public constructor
        this.list  = list;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
             productpageAdapter = new ProductpageAdapter(list,getContext(),this);
                recyclerView.setAdapter(productpageAdapter);
                recyclerView.setLayoutManager(gridLayoutManager);
                price_sort = view.findViewById(R.id.button6);
                price_sort2 = view.findViewById(R.id.button8);

        price_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    price_sort2.setVisibility(View.VISIBLE);
                    price_sort.setVisibility(View.GONE);
                    list.sort(new Comparator<ProductResponse>() {
                        @Override
                        public int compare(ProductResponse o1, ProductResponse o2) {
                            return Double.parseDouble(o1.getPrice().toString())<Double.parseDouble(o2.getPrice().toString())?-1:1;
                        }
                    });
                    productpageAdapter.notifyDataSetChanged();

                }
            }

        });
        price_sort2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    price_sort.setVisibility(View.VISIBLE);
                    price_sort2.setVisibility(View.GONE);
                    list.sort(new Comparator<ProductResponse>() {
                        @Override
                        public int compare(ProductResponse o1, ProductResponse o2) {
                            return Double.parseDouble(o1.getPrice().toString())>Double.parseDouble(o2.getPrice().toString())?-1:1;
                        }
                    });
                    productpageAdapter.notifyDataSetChanged();

                }
            }

        });

    }

    @Override
    public void detalsPage(int id) {
        Intent intent = new Intent(getContext(), ProductDetailPage.class);
        intent.putExtra("id",id+"");
        startActivity(intent);
    }
}
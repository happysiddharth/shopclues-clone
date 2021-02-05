package com.example.shopclues_clone.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.activities.ProductsPage;
import com.example.shopclues_clone.adapter.HomescreenParentRecyclerViewAdpter;
import com.example.shopclues_clone.interfaces.BottomNavigationToggle;
import com.example.shopclues_clone.interfaces.CategoriesOnClickInterface;
import com.example.shopclues_clone.models.HomescreenChildernModel;
import com.example.shopclues_clone.models.HomescreenModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomescreenFragment extends Fragment implements CategoriesOnClickInterface {

    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView ;
    private BottomNavigationToggle bottomNavigationToggle;

    public HomescreenFragment(BottomNavigationToggle bottomNavigationToggle) {
        // Required empty public constructor
        this.bottomNavigationToggle = bottomNavigationToggle;
    }



    @Override
    public void onResume() {
        super.onResume();
       // bottomNavigationToggle.togggle("",(R.id.action_recents));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homescreen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setParentRecyclerView();
    }

    private void setParentRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        HomescreenParentRecyclerViewAdpter parentRecyclerViewAdpter = new HomescreenParentRecyclerViewAdpter(ParentItemList(),this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(parentRecyclerViewAdpter);
    }

    private List<HomescreenModel> ParentItemList() {
        List<HomescreenModel> list=new ArrayList<>();
        HomescreenModel categories =new HomescreenModel("",categoriesListGenerator(),HomescreenModel.CATEGORIES);
        HomescreenModel sponsor = new HomescreenModel("",sponsor(),HomescreenModel.SPONSORS);
        HomescreenModel sale = new HomescreenModel("",sale(),HomescreenModel.SALE_LABEL);
        HomescreenModel best_buys = new HomescreenModel("Best for Boys",best_buys(),HomescreenModel.BEST_BUY);
        HomescreenModel sponsor2 = new HomescreenModel("",sponsor2(),HomescreenModel.SPONSORS);
        HomescreenModel gadget_store = new HomescreenModel("",gadget_store(),HomescreenModel.GADGET_STORE);

        list.add(categories);
        list.add(sponsor);
        list.add(sale);
        list.add(best_buys);
        list.add(sponsor2);
        list.add(gadget_store);
        return list;
    }

    private List<HomescreenChildernModel> gadget_store() {
        List<HomescreenChildernModel> list = new ArrayList<>();
        HomescreenChildernModel item1 = new HomescreenChildernModel(R.drawable.gadget_1,"T-Shirts",499);
        HomescreenChildernModel item2 = new HomescreenChildernModel(R.drawable.gadget_2,"Sports Footwear",999);
        HomescreenChildernModel item3 = new HomescreenChildernModel(R.drawable.gadget_3,"Dresses",799);
        HomescreenChildernModel item4 = new HomescreenChildernModel(R.drawable.gadget_4,"Lipstics",499);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        return list;
    }

    private List<HomescreenChildernModel> sponsor2() {
        List<HomescreenChildernModel> list = new ArrayList<>();
        HomescreenChildernModel item = new HomescreenChildernModel(R.drawable.sponsor2,"",-1);
        list.add(item);
        return list;
    }



    private List<HomescreenChildernModel> sale() {
        List<HomescreenChildernModel> list = new ArrayList<>();
        HomescreenChildernModel item1 = new HomescreenChildernModel(R.drawable.sale_1,"T-Shirts",499);
        HomescreenChildernModel item2 = new HomescreenChildernModel(R.drawable.sale_1,"T-Shirts",499);
        HomescreenChildernModel item3 = new HomescreenChildernModel(R.drawable.sale_1,"T-Shirts",499);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        return  list;

    }

    private List<HomescreenChildernModel> best_buys() {
        List<HomescreenChildernModel> list = new ArrayList<>();
        HomescreenChildernModel item1 = new HomescreenChildernModel(R.drawable.men_best_buy,"T-Shirts",499);
        HomescreenChildernModel item2 = new HomescreenChildernModel(R.drawable.shoe_under,"Sports Footwear",999);
        HomescreenChildernModel item3 = new HomescreenChildernModel(R.drawable.dress_under,"Dresses",799);
        HomescreenChildernModel item4 = new HomescreenChildernModel(R.drawable.lipsticks_under,"Lipstics",499);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        return list;
    }

    private List<HomescreenChildernModel> sponsor() {
        List<HomescreenChildernModel> list = new ArrayList<>();
        HomescreenChildernModel item = new HomescreenChildernModel(R.drawable.sales,"",-1);
        list.add(item);
        return list;
    }

    private List<HomescreenChildernModel> categoriesListGenerator() {
        List<HomescreenChildernModel> child_categories = new ArrayList<>();
        HomescreenChildernModel item1 = new HomescreenChildernModel(R.drawable.time_sale,"Free Delivery",24,HomescreenChildernModel.MENS_FASHION);
        HomescreenChildernModel item2 = new HomescreenChildernModel(R.drawable.men_footwear,"Mens Footwear",24,HomescreenChildernModel.MENS_FOOTWEAR);
        HomescreenChildernModel item3 = new HomescreenChildernModel(R.drawable.mobiles_tabs,"Mobile & Tablets",24,HomescreenChildernModel.ELECTRONICS);
        HomescreenChildernModel item4 = new HomescreenChildernModel(R.drawable.mens_fashions,"Mens Fashion",24,HomescreenChildernModel.MENS_FASHION);
        HomescreenChildernModel item5 = new HomescreenChildernModel(R.drawable.kids_fashions,"Kids Store",24,HomescreenChildernModel.MENS_FASHION);
        HomescreenChildernModel item6 = new HomescreenChildernModel(R.drawable.women_fashion,"Women Store",24,HomescreenChildernModel.WOMEN_FASHION);
        HomescreenChildernModel item7 = new HomescreenChildernModel(R.drawable.home,"Home Store",24,HomescreenChildernModel.MENS_FASHION);
        HomescreenChildernModel item8 = new HomescreenChildernModel(R.drawable.men_watches,"Mens Watch",24,HomescreenChildernModel.MENS_FASHION);
        HomescreenChildernModel item9 = new HomescreenChildernModel(R.drawable.mobile_accessories,"Kitchen & Dinning",24,HomescreenChildernModel.MENS_FASHION);
        HomescreenChildernModel item10 = new HomescreenChildernModel(R.drawable.kitchen_diinigs,"Food & Beverages",24,HomescreenChildernModel.MENS_FASHION);
        child_categories.add(item1);
        child_categories.add(item2);
        child_categories.add(item3);
        child_categories.add(item4);
        child_categories.add(item5);
        child_categories.add(item6);
        child_categories.add(item7);
        child_categories.add(item8);
        child_categories.add(item9);
        child_categories.add(item10);
        return child_categories;
    }

    private void init(View view) {
        recyclerView  = view.findViewById(R.id.parent_recyclerview);
    }

    @Override
    public void redirectToPariticularActivity(String category) {
        Intent intent = new Intent(getContext(), ProductsPage.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }
}
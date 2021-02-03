package com.example.shopclues_clone.fragments;

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
import com.example.shopclues_clone.adapter.LeaderAdapter;
import com.example.shopclues_clone.models.LeaderModelClass;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<LeaderModelClass> leaderModelClassList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leader_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewOfLeaderFragment);
        buildRecyclerData();
        setRecyclerView();
    }

    private void setRecyclerView() {
        LeaderAdapter leaderAdapter = new LeaderAdapter(leaderModelClassList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(leaderAdapter);
    }

    private void buildRecyclerData() {
        leaderModelClassList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                leaderModelClassList.add(new LeaderModelClass("Siddharth", 1));
            }else if (i % 3 == 1){
                leaderModelClassList.add(new LeaderModelClass("praven", 2));
            }else if (i % 3 == 2) {
                leaderModelClassList.add(new LeaderModelClass("sumit", 3));
            }
        }
    }
}
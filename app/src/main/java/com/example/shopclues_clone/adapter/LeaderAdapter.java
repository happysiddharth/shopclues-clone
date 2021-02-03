package com.example.shopclues_clone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.ViewHolder.LeaderViewHolder;
import com.example.shopclues_clone.models.LeaderModelClass;

import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderViewHolder> {

    private List<LeaderModelClass> leaderModelClassList;

    public LeaderAdapter(List<LeaderModelClass> leaderModelClassList) {
        this.leaderModelClassList = leaderModelClassList;
    }

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_layout, parent, false);
        return new LeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {
        LeaderModelClass leaderModelClass = leaderModelClassList.get(position);
        holder.setData(leaderModelClass);

    }

    @Override
    public int getItemCount() {
        return leaderModelClassList.size();
    }
}

package com.example.shopclues_clone.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.models.LeaderModelClass;

public class LeaderViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvLeaderName;
    private TextView mTvRank;

    public LeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvLeaderName = itemView.findViewById(R.id.tvLeaderName);
        mTvRank = itemView.findViewById(R.id.tvRank);
    }

    public void setData(LeaderModelClass leaderModelClass) {
        mTvLeaderName.setText(leaderModelClass.getLeaderName());
        mTvRank.setText(leaderModelClass.getRank());
    }
}

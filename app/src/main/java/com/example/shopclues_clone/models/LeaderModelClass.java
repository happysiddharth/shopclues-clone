package com.example.shopclues_clone.models;

public class LeaderModelClass {
    private String leaderName;
    private int rank;

    public LeaderModelClass(String leaderName, int rank) {
        this.leaderName = leaderName;
        this.rank = rank;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public int getRank() {
        return rank;
    }
}

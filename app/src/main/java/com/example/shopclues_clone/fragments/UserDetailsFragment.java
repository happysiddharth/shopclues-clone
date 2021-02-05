package com.example.shopclues_clone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopclues_clone.R;

public class UserDetailsFragment extends Fragment {

    private TextView mTvName, mTvEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvEmail = view.findViewById(R.id.tvUserEmail);
        mTvName = view.findViewById(R.id.tvUserName);

        String email = getArguments().getString("email");
        String[] name_arr = email.split("@");
        String name = name_arr[0];
        mTvName.setText(name);
        mTvEmail.setText(email);
    }
}
package com.example.shopclues_clone.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.activities.GoogleLoginActivity;
import com.example.shopclues_clone.activities.SignUpActivity;

public class AccountSignUpFragment extends Fragment {

    private Button mBtnSignUp, mBtnSignIn;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnSignUp = view.findViewById(R.id.btnSignUp);
        mBtnSignIn = view.findViewById(R.id.btnSignIn);
        fragmentManager = getActivity().getSupportFragmentManager();

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                intent.putExtra("message", "signIn");
                startActivity(intent);
            }
        });

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                intent.putExtra("message", "signUp");
                startActivity(intent);
            }
        });

    }

}
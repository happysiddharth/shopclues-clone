package com.example.shopclues_clone.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.activities.AccountsActivity;

public class SignUpFragment extends Fragment {

    private EditText mEtEmail, mEtMobile, mEtPassword;
    private Button mBtnRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewsAndListeners(view);
    }

    private void initViewsAndListeners(View view) {
        mEtEmail = view.findViewById(R.id.etEmail);
        mEtMobile = view.findViewById(R.id.etMobile);
        mEtPassword = view.findViewById(R.id.etPassword);
        mBtnRegister = view.findViewById(R.id.btnRegister);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    Intent intent = new Intent(getContext(), AccountsActivity.class);
                    intent.putExtra("email", mEtEmail.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private Boolean isValid() {
        if (!(mEtEmail.getText().toString().contains("@")) && !(mEtEmail.getText().toString().contains(".com"))) {
            mEtEmail.setError("Invalid email");
            return false;
        }
        if (mEtMobile.getText().toString().length() != 10) {
            mEtMobile.setError("Should be of 10 digits");
            return false;
        }
        if (mEtPassword.getText().toString().length() < 7) {
            mEtPassword.setError("Password must contain minimum 7 characters");
            return false;
        }
        return true;
    }
}
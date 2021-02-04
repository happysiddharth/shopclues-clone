package com.example.shopclues_clone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.fragments.SignInFragment;
import com.example.shopclues_clone.fragments.SignUpFragment;

public class SignUpActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (getIntent()!=null){
            String message = getIntent().getStringExtra("message");

            if (message.equals("signIn")){
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SignInFragment signInFragment = new SignInFragment();
                fragmentTransaction.replace(R.id.flContainer, signInFragment, "signInFragment").commit();
            }else if (message.equals("signUp")){
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SignUpFragment signUpFragment = new SignUpFragment();
                fragmentTransaction.replace(R.id.flContainer, signUpFragment, "SignUpFragment").commit();
            }
        }
    }
}
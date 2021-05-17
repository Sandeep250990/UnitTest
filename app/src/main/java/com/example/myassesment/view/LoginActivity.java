package com.example.myassesment.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myassesment.R;
import com.example.myassesment.databinding.ActivityLoginBinding;
import com.example.myassesment.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init(){
        activityLoginBinding= DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel=new LoginViewModel(this);
        activityLoginBinding.setLoginViewModel(loginViewModel);
    }
}

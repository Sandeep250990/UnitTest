package com.example.myassesment.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.myassesment.R;
import com.example.myassesment.model.LoginRequest;
import com.example.myassesment.model.LoginSuccessfullResponse;
import com.example.myassesment.network.ApiClient;
import com.example.myassesment.network.ApiInterface;
import com.example.myassesment.util.Utils;
import com.example.myassesment.view.EmailValidator;
import com.example.myassesment.view.MainActivity;
import com.example.myassesment.view.PasswordValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    Context ctx;

    public LoginViewModel(Context context){
        this.ctx=context;
    }

    public void onLoginClick(String email, String password){
        if(email.trim().length()==0){
            Utils.showToast(ctx,ctx.getResources().getString(R.string.error_enter_email));
        }else if(!EmailValidator.isValidEmail(email)){
            Utils.showToast(ctx,ctx.getResources().getString(R.string.error_inavlid_email));
        }else if(password.trim().length()==0){
            Utils.showToast(ctx,ctx.getResources().getString(R.string.error_enter_pwd));
        }else if(!PasswordValidator.isPasswordValidMethod(password)){
            Utils.showToast(ctx,ctx.getResources().getString(R.string.error_invalid_pwd));
        }
        else{
            callLoginApi(email,password);
        }
    }

    public void callLoginApi(String email, String password){
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getLoginSuccessfullData(loginRequest).enqueue(new Callback<LoginSuccessfullResponse>() {
            @Override
            public void onResponse(Call<LoginSuccessfullResponse> call, Response<LoginSuccessfullResponse> response) {
                if(response.isSuccessful()) {
                    callNextActivity();
                } else if(response.code()==400){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message=jObjError.get("error").toString();
                        Utils.showToast(ctx, message);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginSuccessfullResponse> call, Throwable t) {
                    Utils.showToast(ctx,t.getMessage());
            }
        });

    }
    public void callNextActivity(){
        Intent intent=new Intent(ctx, MainActivity.class);
        ctx.startActivity(intent);
        ((Activity)(ctx)).finish();
    }
}

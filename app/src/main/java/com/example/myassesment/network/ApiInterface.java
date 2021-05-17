package com.example.myassesment.network;

import com.example.myassesment.model.LoginRequest;
import com.example.myassesment.model.LoginSuccessfullResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/api/login")
    Call<LoginSuccessfullResponse> getLoginSuccessfullData(@Body LoginRequest loginRequest);
}

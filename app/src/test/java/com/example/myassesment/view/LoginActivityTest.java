package com.example.myassesment.view;

import com.example.myassesment.model.LoginRequest;
import com.example.myassesment.model.LoginSuccessfullResponse;
import com.example.myassesment.network.ApiClient;
import com.example.myassesment.network.ApiInterface;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import retrofit2.Response;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class LoginActivityTest {

    LoginRequest loginRequest;

    @Before
    public void setUp() {
        loginRequest=new LoginRequest();
    }

    @Test
    public void checkSuccessfullApiResponse() throws IOException {
        loginRequest.setEmail("eve.holt@reqres.in");
        loginRequest.setPassword("cityslicka");
        assertNotNull(loginRequest);
        ApiInterface apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Response<LoginSuccessfullResponse>response=apiInterface.getLoginSuccessfullData(loginRequest).execute();
        assertTrue(response.isSuccessful());
    }

    @Test
    public void checkUnSuccessfullApiResponse() throws IOException {
        loginRequest.setEmail("eve.holt@reqres.com");
        loginRequest.setPassword("eve@898");
        assertNotNull(loginRequest);
        ApiInterface apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Response<LoginSuccessfullResponse>response=apiInterface.getLoginSuccessfullData(loginRequest).execute();
        assertFalse(response.isSuccessful());
    }
}
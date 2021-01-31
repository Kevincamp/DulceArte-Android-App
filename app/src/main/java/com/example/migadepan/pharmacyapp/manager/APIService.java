package com.example.migadepan.pharmacyapp.manager;

import com.example.migadepan.pharmacyapp.Model.User;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers("Content-Type: application/json")
    @POST("users/login")
    Call<User> login(@Body JsonObject body);
}

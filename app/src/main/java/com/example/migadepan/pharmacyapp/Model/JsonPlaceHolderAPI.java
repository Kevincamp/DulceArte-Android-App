package com.example.migadepan.pharmacyapp.Model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderAPI {

    @GET
    Call<ArrayList<Store>> getStoreList();

    @GET
    Call<ArrayList<Product>> getProductList();
}

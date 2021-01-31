package com.example.migadepan.pharmacyapp.Network;

import com.example.migadepan.pharmacyapp.manager.APIService;
import com.example.migadepan.pharmacyapp.manager.RetrofitClient;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://10.0.2.2:8080/RESTServer/rest/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
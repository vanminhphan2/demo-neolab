package com.example.rio.mvpapp.retrofit;

import com.example.rio.mvpapp.model.User;


import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("api/get_list_user")
    Call<User> getData();
}

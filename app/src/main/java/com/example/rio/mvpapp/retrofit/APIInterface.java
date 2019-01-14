package com.example.rio.mvpapp.retrofit;

import com.example.rio.mvpapp.model.User;


import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("api/get_list_user")
    Observable<List<User>> getData();
}

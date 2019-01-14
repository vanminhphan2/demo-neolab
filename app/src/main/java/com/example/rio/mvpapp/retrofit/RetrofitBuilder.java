package com.example.rio.mvpapp.retrofit;

import com.example.rio.mvpapp.di.API;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitBuilder {

    private String baseUrl;
    private   Retrofit retrofit;

    @Inject
    public RetrofitBuilder(@API String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public  Retrofit getRetrofitBuilder() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}

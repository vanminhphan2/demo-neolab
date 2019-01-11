package com.example.rio.mvpapp.retrofit;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitBuilder {

    private String baseUrl;
    private   Retrofit retrofit;

    @Inject
    public RetrofitBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public  Retrofit getRetrofitBuilder() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}

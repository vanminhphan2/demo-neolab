package com.example.rio.mvpapp.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.rio.mvpapp.api.Api;
import com.example.rio.mvpapp.di.API;
import com.example.rio.mvpapp.di.ApplicationContext;
import com.example.rio.mvpapp.di.DatabaseInfo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }

    @Provides
    @API
    String provideUrl() {
        return Api.IP_LOCAL_SERVER;
    }




//    String mBaseUrl;
//
//    @Provides
//    @Singleton
//    Cache provideHttpCache(Application application) {
//        int cacheSize = 10 * 1024 * 1024;
//        Cache cache = new Cache(application.getCacheDir(), cacheSize);
//        return cache;
//    }
//
//    @Provides
//    @Singleton
//    Gson provideGson() {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//        return gsonBuilder.create();
//    }
//
//    @Provides
//    @Singleton
//    OkHttpClient provideOkhttpClient(Cache cache) {
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        client.cache(cache);
//        return client.build();
//    }
//
//    @Provides
//    @Singleton
//    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
//        return new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .baseUrl(Api.IP_LOCAL_SERVER)
//                .client(okHttpClient)
//                .build();
//    }

}

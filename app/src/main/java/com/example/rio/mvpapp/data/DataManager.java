package com.example.rio.mvpapp.data;

import android.content.Context;

import com.example.rio.mvpapp.data.prefs.SharedPrefsHelper;
import com.example.rio.mvpapp.data.sqlite.DbHelper;
import com.example.rio.mvpapp.di.ApplicationContext;
import com.example.rio.mvpapp.retrofit.RetrofitBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;


@Singleton
public class DataManager {

    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPrefsHelper mSharedPrefsHelper;
    private RetrofitBuilder retrofitBuilder;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DbHelper dbHelper,
                       SharedPrefsHelper sharedPrefsHelper,RetrofitBuilder retrofit) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelper = sharedPrefsHelper;
        retrofitBuilder=retrofit;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken(){
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public SharedPrefsHelper getPrefs(){
        return mSharedPrefsHelper;
    }

    public Retrofit getRetrofit(){return  retrofitBuilder.getRetrofitBuilder();}

}
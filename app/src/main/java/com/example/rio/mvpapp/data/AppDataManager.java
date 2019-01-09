package com.example.rio.mvpapp.data;

import android.content.Context;

import com.example.rio.mvpapp.data.prefs.PreferencesHelper;
import com.example.rio.mvpapp.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class AppDataManager   {


    private Context mContext;
    private PreferencesHelper mSharedPrefsHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context mContext, PreferencesHelper mSharedPrefsHelper) {
        this.mContext = mContext;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
    }

    public String getPhoneNumber() {
        return mSharedPrefsHelper.getPhoneNumber();
    }

    public void setPhoneNumber(String phone) {
        mSharedPrefsHelper.setPhoneNumber(phone);
    }

    public String getPassword() {
        return mSharedPrefsHelper.getPhoneNumber();
    }

    public void setPassword(String newValue) {
        mSharedPrefsHelper.setPhoneNumber(newValue);
    }

    public String getAccessToken() {
        return mSharedPrefsHelper.getAccessToken();
    }

    public void setAccessToken(String accessToken) {
        mSharedPrefsHelper.setPhoneNumber(accessToken);
    }
}

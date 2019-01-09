package com.example.rio.mvpapp.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rio.mvpapp.di.ApplicationContext;
import com.example.rio.mvpapp.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPrefsHelper  {

    private static final String PREF_KEY_PHONE = "PREF_KEY_PHONE";
    private static final String PREF_KEY_PASS = "PREF_KEY_PASS";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private SharedPreferences mSharedPreferences;

    @Inject
    public SharedPrefsHelper(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        mSharedPreferences =  context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    public String getPhoneNumber() {
        return mSharedPreferences.getString(PREF_KEY_PHONE,null);
    }

    public void setPhoneNumber(String phone) {
        mSharedPreferences.edit().putString(PREF_KEY_PHONE, phone).apply();
    }

    public String getPassword() {

        return mSharedPreferences.getString(PREF_KEY_PASS,null);
    }

    public void setPassword(String newValue) {

        mSharedPreferences.edit().putString(PREF_KEY_PASS, newValue).apply();
    }

    public String getAccessToken() {

        return mSharedPreferences.getString(PREF_KEY_ACCESS_TOKEN,null);
    }

    public void setAccessToken(String accessToken) {

        mSharedPreferences.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

}

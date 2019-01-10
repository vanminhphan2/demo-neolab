package com.example.rio.mvpapp.view.activity.login;

import android.content.Context;

import com.example.rio.mvpapp.di.PerActivity;

import javax.inject.Singleton;

@PerActivity
public interface LoginPresenterListener {

    void checkLogin(String phone, String pass);
    void setViewListener(Context context, LoginViewListener loginViewListener);
}

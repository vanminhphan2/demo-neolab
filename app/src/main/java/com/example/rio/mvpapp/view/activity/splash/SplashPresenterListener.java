package com.example.rio.mvpapp.view.activity.splash;

import android.content.Context;

import com.example.rio.mvpapp.di.PerActivity;

@PerActivity
public interface SplashPresenterListener {
    void checkLogin();
    void setViewListener(Context context, SplashViewListener splashViewListener);
}

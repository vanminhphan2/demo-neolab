package com.example.rio.mvpapp.di.module;


import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.rio.mvpapp.di.ActivityContext;
import com.example.rio.mvpapp.di.PerActivity;
import com.example.rio.mvpapp.retrofit.RetrofitBuilder;
import com.example.rio.mvpapp.view.activity.login.LoginPresenter;
import com.example.rio.mvpapp.view.activity.login.LoginPresenterListener;
import com.example.rio.mvpapp.view.activity.splash.SplashPresenter;
import com.example.rio.mvpapp.view.activity.splash.SplashPresenterListener;
import com.example.rio.mvpapp.view.activity.splash.SplashViewListener;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    LoginPresenterListener provideLoginPresenterListener(
            LoginPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SplashPresenterListener provideSplashPresenterListener(
            SplashPresenter presenter) {
        return presenter;
    }

}
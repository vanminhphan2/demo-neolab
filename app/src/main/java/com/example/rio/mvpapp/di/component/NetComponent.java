package com.example.rio.mvpapp.di.component;

import com.example.rio.mvpapp.di.PerActivity;
import com.example.rio.mvpapp.di.PerNet;
import com.example.rio.mvpapp.di.module.ActivityModule;
import com.example.rio.mvpapp.di.module.NetModule;
import com.example.rio.mvpapp.retrofit.RetrofitBuilder;
import com.example.rio.mvpapp.view.activity.login.LoginActivity;
import com.example.rio.mvpapp.view.activity.main.MainActivity;
import com.example.rio.mvpapp.view.activity.splash.SplashActivity;
import com.example.rio.mvpapp.view.activity.splash.SplashPresenter;

import dagger.Component;
import retrofit2.Retrofit;

//@PerNet
//@Component(dependencies = ApplicationComponent.class, modules =NetModule.class)
public interface NetComponent {

    void inject(SplashPresenter splashPresenter);

//    RetrofitBuilder retrofitBuilder();
}
package com.example.rio.mvpapp.di.component;

import com.example.rio.mvpapp.di.PerActivity;
import com.example.rio.mvpapp.di.module.ActivityModule;
import com.example.rio.mvpapp.view.activity.login.LoginActivity;
import com.example.rio.mvpapp.view.activity.main.MainActivity;
import com.example.rio.mvpapp.view.activity.splash.SplashActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(SplashActivity splashActivity);
    void inject(LoginActivity loginActivity);
}
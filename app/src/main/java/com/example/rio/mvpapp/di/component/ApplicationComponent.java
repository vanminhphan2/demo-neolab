package com.example.rio.mvpapp.di.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.rio.mvpapp.MyApplication;
import com.example.rio.mvpapp.data.AppDataManager;
import com.example.rio.mvpapp.di.ApplicationContext;
import com.example.rio.mvpapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyApplication myApplication);

    @ApplicationContext
    Context context();

    Application getApplication();

    AppDataManager getDataManager();

    SharedPreferences getSharedPreferencesHelper();

}

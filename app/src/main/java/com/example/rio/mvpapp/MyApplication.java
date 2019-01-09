package com.example.rio.mvpapp;

import android.app.Application;
import android.content.Context;

import com.example.rio.mvpapp.data.AppDataManager;
import com.example.rio.mvpapp.di.component.ApplicationComponent;
import com.example.rio.mvpapp.di.module.ApplicationModule;

import javax.inject.Inject;

public class MyApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    AppDataManager dataManager;

    public static MyApplication get(Context context){
        return (MyApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        applicationComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this))
//                .build();
//        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }
}

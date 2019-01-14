package com.example.rio.mvpapp.di.component;

import android.app.Application;
import android.content.Context;

import com.example.rio.mvpapp.MyApplication;
import com.example.rio.mvpapp.data.DataManager;
import com.example.rio.mvpapp.data.prefs.SharedPrefsHelper;
import com.example.rio.mvpapp.data.sqlite.DbHelper;
import com.example.rio.mvpapp.di.ApplicationContext;
import com.example.rio.mvpapp.di.module.ApplicationModule;
import com.example.rio.mvpapp.di.module.NetModule;
import com.example.rio.mvpapp.retrofit.RetrofitBuilder;
import com.example.rio.mvpapp.view.activity.login.LoginActivity;
import com.example.rio.mvpapp.view.activity.main.MainActivity;
import com.example.rio.mvpapp.view.activity.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules ={ ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MyApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getPreferenceHelper();

    DbHelper getDbHelper();

    RetrofitBuilder getRetrofitBuilder();


}
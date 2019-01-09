package com.example.rio.mvpapp.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.rio.mvpapp.data.AppDataManager;
import com.example.rio.mvpapp.di.ApplicationContext;
import com.example.rio.mvpapp.di.PreferenceInfo;
import com.example.rio.mvpapp.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    SharedPreferences provideSharedPrefs(){
        return mApplication.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }


}

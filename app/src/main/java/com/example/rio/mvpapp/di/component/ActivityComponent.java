package com.example.rio.mvpapp.di.component;

import com.example.rio.mvpapp.di.PerActivity;
import com.example.rio.mvpapp.di.module.ActivityModule;
import com.example.rio.mvpapp.view.activity.main.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
package com.example.rio.mvpapp.view.activity.splash;

import com.example.rio.mvpapp.model.User;

public interface SplashViewListener {
    void toLogin();
    void toMain(User user );
}

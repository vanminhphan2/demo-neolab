package com.example.rio.mvpapp.view.activity.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rio.mvpapp.R;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.presenter.splash.SplashPresenter;
import com.example.rio.mvpapp.view.activity.login.LoginActivity;
import com.example.rio.mvpapp.view.activity.main.MainActivity;

import es.dmoral.toasty.Toasty;

public class SplashActivity extends AppCompatActivity implements SplashViewListener{

    private SplashPresenter splashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashPresenter=new SplashPresenter(this, this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashPresenter.checkLogin();
            }
        },2000);
    }

    @Override
    public void toLogin() {
        Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void toMain(User user) {
        Intent loginIntent = new Intent(SplashActivity.this, MainActivity.class);
        loginIntent.putExtra("INFO_USER", user);
        startActivity(loginIntent);
        finish();
    }

}

package com.example.rio.mvpapp.view.activity.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rio.mvpapp.MyApplication;
import com.example.rio.mvpapp.R;
import com.example.rio.mvpapp.di.component.ActivityComponent;
import com.example.rio.mvpapp.di.component.DaggerActivityComponent;
import com.example.rio.mvpapp.di.module.ActivityModule;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.view.activity.login.LoginActivity;
import com.example.rio.mvpapp.view.activity.main.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashViewListener{

    @Inject
    SplashPresenterListener mPresenter;

    private ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(MyApplication.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        activityComponent = DaggerActivityComponent.builder()
//                .activityModule(new ActivityModule(this))
//                .applicationComponent(MyApplication.get(this).getComponent())
//                .build();

        getActivityComponent().inject(this);

        mPresenter.setViewListener(this, this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.checkLogin();
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

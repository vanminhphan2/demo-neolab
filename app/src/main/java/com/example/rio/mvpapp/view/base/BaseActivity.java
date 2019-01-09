package com.example.rio.mvpapp.view.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rio.mvpapp.MyApplication;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.utils.Utils;


public abstract class BaseActivity extends AppCompatActivity implements MvpView{

    private ProgressDialog mProgressDialog;
//    private MyApplication myApplication;
    private Context applicationContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        myApplication= (MyApplication) getApplication();
//        applicationContext=myApplication.getApplicationContext();
    }

    public Context getAppContext() {
        return applicationContext;
    }

//    public User getUser() {
//        return myApplication.getUser();
//    }
//
//    public void setUser(User user) {
//        myApplication.setUser(user);
//    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = Utils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }


}

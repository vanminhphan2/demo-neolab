package com.example.rio.mvpapp.view.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.rio.mvpapp.MyApplication;
import com.example.rio.mvpapp.R;
import com.example.rio.mvpapp.data.DataManager;
import com.example.rio.mvpapp.di.component.ActivityComponent;
import com.example.rio.mvpapp.di.component.DaggerActivityComponent;
import com.example.rio.mvpapp.di.module.ActivityModule;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.view.activity.main.MainActivity;
import com.example.rio.mvpapp.view.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity implements LoginViewListener {

    @BindView(R.id.et_phone)
    TextInputEditText etPhone;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.btn_server_login)
    Button btnServerLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;

    @Inject
    LoginPresenterListener loginPresenterListener;

    @Inject
    DataManager dataManager;

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
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        loginPresenterListener.setViewListener(this, this);
    }

    @OnClick({R.id.btn_server_login, R.id.btn_register})
    public void onClickLogin(View v) {

        switch (v.getId()) {
            case R.id.btn_server_login:

                showLoading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loginPresenterListener.checkLogin(String.valueOf(etPhone.getText()), String.valueOf(etPassword.getText()));
                    }
                }, 1000);
                break;
            case R.id.btn_register:

                break;
        }
    }

    @Override
    public void loginSuccess(User user) {

        hideLoading();
        Toasty.success(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT, true).show();
        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        loginIntent.putExtra("INFO_USER", user);
        startActivity(loginIntent);
        finish();
//        loginServer();
    }

    @Override
    public void loginFail(String error) {
        hideLoading();
        Toasty.warning(this, error, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void emptyPhone() {
        hideLoading();
        Toasty.warning(this, "Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void emptyPass() {
        hideLoading();
        Toasty.warning(this, "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void phoneWrong() {
        hideLoading();
        Toasty.warning(this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void passWrong() {
        hideLoading();
        Toasty.warning(this, "Sai mật khẩu!", Toast.LENGTH_SHORT, true).show();
    }

}

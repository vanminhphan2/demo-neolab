package com.example.rio.mvpapp.view.activity.login;

import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.view.base.BaseMvpView;

public interface LoginViewListener extends BaseMvpView {

    void loginSuccess( User user);
    void loginFail(String error);
    void emptyPhone();
    void emptyPass();
    void phoneWrong();
    void passWrong();
}

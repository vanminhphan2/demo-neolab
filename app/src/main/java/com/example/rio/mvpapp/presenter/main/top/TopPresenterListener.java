package com.example.rio.mvpapp.presenter.main.top;

import com.example.rio.mvpapp.model.User;

public interface TopPresenterListener {

    void getDataFromSV();
    void toDetail(User user);
    void getDataMoreFromSV();
    void refreshData();
}

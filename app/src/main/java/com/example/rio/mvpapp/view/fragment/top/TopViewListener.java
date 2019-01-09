package com.example.rio.mvpapp.view.fragment.top;

import com.example.rio.mvpapp.model.User;

import java.util.ArrayList;

public interface TopViewListener {

    void getListSuccess(ArrayList<User> users);
    void getListFail();
    void toDetail(User user);
    void toDetailError();
    void getMoreDataSuccess(ArrayList<User> users);
    void getMoreDataError();
    void refreshDataSuccess(ArrayList<User> users);
    void refreshDataFail();
}

package com.example.rio.mvpapp.view.fragment.editaccount;

import com.example.rio.mvpapp.model.User;

public interface EditAccountViewListener {

    void saveInfoSuccess(User user);
    void saveInfFail();
}

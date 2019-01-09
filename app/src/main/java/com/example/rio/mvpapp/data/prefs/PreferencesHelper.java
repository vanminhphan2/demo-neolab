package com.example.rio.mvpapp.data.prefs;

public interface PreferencesHelper {

    String getPhoneNumber();

    void setPhoneNumber(String phone);

    String getPassword() ;

    public void setPassword(String newValue);

    String getAccessToken();

    void setAccessToken(String accessToken);

}

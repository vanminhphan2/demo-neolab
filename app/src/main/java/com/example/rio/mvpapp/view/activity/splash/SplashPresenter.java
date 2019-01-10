package com.example.rio.mvpapp.view.activity.splash;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rio.mvpapp.api.Common;
import com.example.rio.mvpapp.data.DataManager;
import com.example.rio.mvpapp.data.prefs.SharedPrefsHelper;
import com.example.rio.mvpapp.model.ResultServer;
import com.example.rio.mvpapp.utils.Constants;
import com.example.rio.mvpapp.utils.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class SplashPresenter implements SplashPresenterListener{

    private SplashViewListener splashViewListener;
    private Context context;
    private SharedPrefsHelper sharedPrefsHelper;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        sharedPrefsHelper=dataManager.getPrefs();
    }

    @Override
    public void checkLogin() {
        if(sharedPrefsHelper.get(Constants.PHONE,"").equals("")){
            splashViewListener.toLogin();
        }else {
            loginServer(sharedPrefsHelper.get(Constants.PHONE,""),sharedPrefsHelper.get(Constants.PASS,""));
        }
    }

    @Override
    public void setViewListener(Context context, SplashViewListener splashViewListener) {
        this.splashViewListener = splashViewListener;
        this.context=context;
    }

    private void loginServer(final String phone, final String pass) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phone", phone);
            jsonObject.put("password", pass);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Common.LOGIN_API, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Rio ", "loginServer - - onResponse--> " + response.toString());
                    Gson gson;
                    GsonBuilder builder = new GsonBuilder();
                    gson = builder.serializeNulls().create();
                    final ResultServer resultServer;
                    try {
                        resultServer = gson.fromJson(response.toString(), ResultServer.class);
                        if (resultServer != null) {
                            if (resultServer.isStatus()) {

                                splashViewListener.toMain(resultServer.getData());

                            } else {
                                splashViewListener.toLogin();
                            }
                        }
                    } catch (Exception e) {
                        Log.e("loi pasre gson", " -->loginServer : " + e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    splashViewListener.toLogin();
                    Log.e("Rio ", "loginServer -- onErrorResponse :" + error.toString());
                }
            }) {
            };
            VolleySingleton.getInstance(context).getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

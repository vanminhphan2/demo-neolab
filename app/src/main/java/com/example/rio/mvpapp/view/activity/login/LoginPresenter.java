package com.example.rio.mvpapp.view.activity.login;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rio.mvpapp.api.Api;
import com.example.rio.mvpapp.data.prefs.SharedPrefsHelper;
import com.example.rio.mvpapp.model.ResultServer;
import com.example.rio.mvpapp.utils.Constants;
import com.example.rio.mvpapp.utils.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class LoginPresenter  implements LoginPresenterListener{

    private SharedPrefsHelper sharedPrefsHelper;

    private LoginViewListener loginViewListener;
    private Context context;

    @Inject
    public LoginPresenter(SharedPrefsHelper sharedPrefsHelper) {
        this.sharedPrefsHelper=sharedPrefsHelper;
    }

    @Override
    public void checkLogin(String phone, String pass) {


        if (phone == null || phone.equals("")) {
            loginViewListener.emptyPhone();
        } else if (pass == null || pass.equals("")) {
            loginViewListener.emptyPass();
        } else if (phone.trim().length() <10) {
            loginViewListener.phoneWrong();
        } else if (pass.trim().length() < 6) {
            loginViewListener.passWrong();
        }else {
            loginServer(phone,pass);
//            loginViewListener.loginSuccess(new User());
        }//loginViewListener.loginSuccess();

    }

    @Override
    public void setViewListener(Context context, LoginViewListener loginViewListener) {
        this.loginViewListener = loginViewListener;
        this.context=context;
    }

    private void loginServer(final String phone, final String pass) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phone", phone);
            jsonObject.put("password", pass);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Api.LOGIN_API, jsonObject, new Response.Listener<JSONObject>() {
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
                                loginViewListener.loginSuccess(resultServer.getData());

                                Log.e("Rio ", "loginServer - - getData--> " + resultServer.getData().toString());
                                sharedPrefsHelper.put(Constants.PHONE,phone);
                                sharedPrefsHelper.put(Constants.PASS,pass);

                            } else {
                                loginViewListener.loginFail(resultServer.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        Log.e("loi pasre gson", " -->loginServer : " + e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loginViewListener.loginFail("Lỗi kết nối với server!");
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

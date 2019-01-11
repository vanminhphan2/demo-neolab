package com.example.rio.mvpapp.presenter.main.setting;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rio.mvpapp.api.Api;
import com.example.rio.mvpapp.model.ResultServer;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.utils.VolleySingleton;
import com.example.rio.mvpapp.view.fragment.editaccount.EditAccountViewListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class EditAccountPresenter implements EditAccountPresenterListener{


    private EditAccountViewListener editAccountViewListener;
    private Context context;

    public EditAccountPresenter(EditAccountViewListener editAccountViewListener, Context context) {
        this.editAccountViewListener = editAccountViewListener;
        this.context = context;
    }

    @Override
    public void save(User user) {
        updateInfoToServer(user);
    }


    private void updateInfoToServer(final User user) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", user.getId());
            jsonObject.put("name", user.getName());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Api.UPDATE_USER_NAME_API, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Gson gson;
                    GsonBuilder builder = new GsonBuilder();
                    gson = builder.serializeNulls().create();
                    final ResultServer resultServer;
                    try {
                        resultServer = gson.fromJson(response.toString(), ResultServer.class);
                        if (resultServer != null) {
                            if (resultServer.isStatus()) {
                                editAccountViewListener.saveInfoSuccess(user);
                            } else {
                                editAccountViewListener.saveInfFail();
                            }
                        }
                    } catch (Exception e) {
                        Log.e("loi pasre gson", " -->updateInfoToServer : " + e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    editAccountViewListener.saveInfFail();
                    Log.e("Rio ", "updateInfoToServer -- onErrorResponse :" + error.toString());
                }
            }) {
            };
            VolleySingleton.getInstance(context).getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

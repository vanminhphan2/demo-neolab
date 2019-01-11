package com.example.rio.mvpapp.presenter.main.top;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rio.mvpapp.api.Api;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.utils.VolleySingleton;
import com.example.rio.mvpapp.view.fragment.top.TopViewListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TopPresenter implements TopPresenterListener {

    private Context context;
    private TopViewListener topViewListener;

    public TopPresenter(Context context, TopViewListener topViewListener) {
        this.context = context;
        this.topViewListener = topViewListener;
    }

    @Override
    public void getDataFromSV() {
        getListUserServer();
    }

    @Override
    public void toDetail(User user) {
        if(user!=null){
            topViewListener.toDetail(user);
        }else {
            topViewListener.toDetailError();
        }
    }

    @Override
    public void getDataMoreFromSV() {
        getListUserMoreServer();
    }

    @Override
    public void refreshData() {
        refreshDataFromSV();
    }

    private void getListUserServer() {
        JSONObject jsonObject = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Api.GET_LIST_USER_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    ArrayList<User> listdata = new ArrayList<User>();
                    Gson gson;
                    GsonBuilder builder = new GsonBuilder();
                    gson = builder.serializeNulls().create();

                    JSONArray jsonArray = (JSONArray) response.get("results");
                    if (jsonArray != null) {
                        for (int i=0;i<jsonArray.length();i++){
                            User user1= gson.fromJson(jsonArray.get(i).toString(), User.class);
                            if (user1 != null) {
                                listdata.add(user1);
                            }
                        }
                    }
                    if(listdata.size()>0){
                        topViewListener.getListSuccess(listdata);

                        Log.e("Rio ", "GET_LIST_USER_API - - getListSuccess--> " + listdata.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                topViewListener.getListFail();
                Log.e("Rio ", "GET_LIST_USER_API -- onErrorResponse :" + error.toString());
            }
        }) {
        };
        VolleySingleton.getInstance(context).getRequestQueue().add(jsonObjectRequest);

    }

    private void refreshDataFromSV() {
        JSONObject jsonObject = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Api.GET_LIST_USER_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    ArrayList<User> listdata = new ArrayList<User>();
                    Gson gson;
                    GsonBuilder builder = new GsonBuilder();
                    gson = builder.serializeNulls().create();

                    JSONArray jsonArray = (JSONArray) response.get("results");
                    if (jsonArray != null) {
                        for (int i=0;i<jsonArray.length();i++){
                            User user1= gson.fromJson(jsonArray.get(i).toString(), User.class);
                            if (user1 != null) {
                                listdata.add(user1);
                            }
                        }
                    }
                    if(listdata.size()>0){
                        topViewListener.refreshDataSuccess(listdata);

                        Log.e("Rio ", "GET_LIST_USER_API - - getListSuccess--> " + listdata.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                topViewListener.refreshDataFail();
                Log.e("Rio ", "GET_LIST_USER_API -- onErrorResponse :" + error.toString());
            }
        }) {
        };
        VolleySingleton.getInstance(context).getRequestQueue().add(jsonObjectRequest);

    }

    private void getListUserMoreServer() {
        JSONObject jsonObject = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Api.GET_LIST_USER_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    ArrayList<User> listdata = new ArrayList<User>();
                    Gson gson;
                    GsonBuilder builder = new GsonBuilder();
                    gson = builder.serializeNulls().create();

                    JSONArray jsonArray = (JSONArray) response.get("results");
                    if (jsonArray != null) {
                        for (int i=0;i<jsonArray.length();i++){
                            User user1= gson.fromJson(jsonArray.get(i).toString(), User.class);
                            if (user1 != null) {
                                listdata.add(user1);
                            }
                        }
                    }
                    if(listdata.size()>0){
                        topViewListener.getMoreDataSuccess(listdata);

                        Log.e("Rio ", "GET_LIST_USER_API - - getListSuccess--> " + listdata.toString());
                    }else {

                        topViewListener.getMoreDataError();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                topViewListener.getMoreDataError();
                Log.e("Rio ", "GET_LIST_USER_API -- onErrorResponse :" + error.toString());
            }
        }) {
        };
        VolleySingleton.getInstance(context).getRequestQueue().add(jsonObjectRequest);

    }
}

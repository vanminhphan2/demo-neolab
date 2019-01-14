package com.example.rio.mvpapp.view.activity.splash;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rio.mvpapp.api.Api;
import com.example.rio.mvpapp.data.DataManager;
import com.example.rio.mvpapp.data.prefs.SharedPrefsHelper;
import com.example.rio.mvpapp.model.ResultServer;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.retrofit.APIInterface;
import com.example.rio.mvpapp.retrofit.RetrofitBuilder;
import com.example.rio.mvpapp.utils.Constants;
import com.example.rio.mvpapp.utils.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class SplashPresenter implements SplashPresenterListener {

    private SplashViewListener splashViewListener;
    private Context context;
    private SharedPrefsHelper sharedPrefsHelper;
    private Retrofit retrofit;

    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    public SplashPresenter(DataManager dataManager) {
        sharedPrefsHelper = dataManager.getPrefs();
        this.retrofit = dataManager.getRetrofit();
    }

    @Override
    public void checkLogin() {
        if (sharedPrefsHelper.get(Constants.PHONE, "").equals("")) {
            splashViewListener.toLogin();
        } else {
            loginServer(sharedPrefsHelper.get(Constants.PHONE, ""), sharedPrefsHelper.get(Constants.PASS, ""));
        }
    }

    @Override
    public void setViewListener(Context context, SplashViewListener splashViewListener) {
        this.splashViewListener = splashViewListener;
        this.context = context;
    }


    private void loginServer(final String phone, final String pass) {

//        Observable<List<User>> posts = retrofit.create(APIInterface.class);

        mCompositeDisposable = new CompositeDisposable();
        APIInterface reqApi = retrofit.create(APIInterface.class);

        Disposable disposable = reqApi.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())//Sau khi gọi thằng .observeOn(AndroidSchedulers.mainThread()) thì thằng subscribe sẽ chạy trên thread mà nó chỉ định
                .subscribe(response -> {
                    handleResponse(response);
                }, error -> handleError(error));

        mCompositeDisposable.add(disposable);

//        posts.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
//                Log.e("onResponse onResponse", " -->loginServer : " + response.toString());
//                if(response.message().equals("OK")){
//
//                    Log.e("onResponse onResponse", " -->loginServer : " + response.body().toString());
//
//                }else {
//
//                    Log.e("onResponse onResponse", " -->loginServer : " + "loiiii 111");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Log.e("onFailure onFailure", " -->loginServer : " + t.toString());
//            }
//        });


//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("phone", phone);
//            jsonObject.put("password", pass);
//
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Api.LOGIN_API, jsonObject, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    Log.e("Rio ", "loginServer - - onResponse--> " + response.toString());
//                    Gson gson;
//                    GsonBuilder builder = new GsonBuilder();
//                    gson = builder.serializeNulls().create();
//                    final ResultServer resultServer;
//                    try {
//                        resultServer = gson.fromJson(response.toString(), ResultServer.class);
//                        if (resultServer != null) {
//                            if (resultServer.isStatus()) {
//
//                                splashViewListener.toMain(resultServer.getData());
//
//                            } else {
//                                splashViewListener.toLogin();
//                            }
//                        }
//                    } catch (Exception e) {
//                        Log.e("loi pasre gson", " -->loginServer : " + e.toString());
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    splashViewListener.toLogin();
//                    Log.e("Rio ", "loginServer -- onErrorResponse :" + error.toString());
//                }
//            }) {
//            };
//            VolleySingleton.getInstance(context).getRequestQueue().add(jsonObjectRequest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private void handleResponse(List<User> androidList) {


        try {
            if (androidList != null) {

//                                splashViewListener.toMain(resultServer.getData());

            } else {
//                                splashViewListener.toLogin();

            }
        } catch (Exception e) {
            Log.e("loi pasre gson", " -->loginServer : " + e.toString());
        }
        Log.e("Rio ", "loginServer -- handleResponse :" + androidList.toString());

    }

    private void handleError(Throwable error) {
        Log.e("Rio ", "loginServer -- handleError :");
    }

    private void handleSuccess() {
        Log.e("Rio ", "loginServer -- handleSuccess :");
    }
}

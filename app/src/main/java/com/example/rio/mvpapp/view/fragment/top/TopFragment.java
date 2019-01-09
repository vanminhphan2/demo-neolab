package com.example.rio.mvpapp.view.fragment.top;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rio.mvpapp.R;
import com.example.rio.mvpapp.adapter.TopAdapter;
import com.example.rio.mvpapp.inf.MyOnClick;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.presenter.main.top.TopPresenter;
import com.example.rio.mvpapp.view.activity.AccountActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

public class TopFragment extends Fragment implements TopViewListener {


    @BindView(R.id.rv_list_user)
    RecyclerView rvListUser;
    Unbinder unbinder;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private TopPresenter topPresenter;
    private Context context;
    boolean isLoading = false;
    private TopAdapter topAdapter;
    private ArrayList<User> users;


    public TopFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        unbinder = ButterKnife.bind(this, view);

        context = this.getContext();
        topPresenter = new TopPresenter(context, this);
        topPresenter.getDataFromSV();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getListSuccess(ArrayList<User> users) {

        this.users=users;
        topAdapter = new TopAdapter(context, users);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvListUser.setLayoutManager(linearLayoutManager);
        topAdapter.setMyOnClick(new MyOnClick() {
            @Override
            public void onClickItem(View v, Object object, int position) {

                User user = (User) object;
                topPresenter.toDetail(user);
            }
        });

        rvListUser.setAdapter(topAdapter);
        rvListUser.setHasFixedSize(true);
        initScrollListener();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                topPresenter.refreshData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                    }
                }, 4000);
            }
        });

    }

    int visibleThreshold=1;
    int lastVisibleItem,totalItemCount;

    private void initScrollListener() {

        rvListUser.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                assert linearLayoutManager != null;
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading&& totalItemCount <= (lastVisibleItem+visibleThreshold)&&!swipe.isRefreshing()) {
//                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == users.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
//                    }
                }
            }
        });
    }

    private void loadMore() {
        users.add(null);
        topAdapter.notifyItemInserted(users.size() - 1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                users.remove(users.size() - 1);
                int scrollPosition = users.size();
                topAdapter.notifyItemRemoved(scrollPosition);
                topPresenter.getDataMoreFromSV();
            }
        }, 3000);
    }

    @Override
    public void getListFail() {
        Toasty.warning(context, "loi ket noi server, ko lay dc data!", Toast.LENGTH_SHORT, true).show();

    }

    @Override
    public void toDetail(User user) {
        Intent loginIntent = new Intent(getActivity(), AccountActivity.class);
        loginIntent.putExtra("INFO_USER", user);
        startActivity(loginIntent);
    }

    @Override
    public void toDetailError() {
        Toasty.warning(context, "Click detail!", Toast.LENGTH_SHORT, true).show();

    }

    @Override
    public void getMoreDataSuccess(ArrayList<User> users) {

        this.users.addAll(users);
        topAdapter.setData(this.users);
        isLoading = false;
    }

    @Override
    public void getMoreDataError() {
        topAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    @Override
    public void refreshDataSuccess(ArrayList<User> users) {
        this.users=users;
        topAdapter.setData(this.users);
        swipe.setRefreshing(false);
    }

    @Override
    public void refreshDataFail() {
        swipe.setRefreshing(false);
    }
}

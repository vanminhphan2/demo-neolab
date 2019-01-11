package com.example.rio.mvpapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.rio.mvpapp.R;
import com.example.rio.mvpapp.inf.MyOnClick;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 1, VIEW_TYPE_LOADING = 0;
    private ArrayList<User> data;
    private Context context;
    private RequestOptions requestOptions;
    private MyOnClick myOnClick;


    public void setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
    }

    public void addData(ArrayList<User> users){
        this.data.addAll(users);
        notifyDataSetChanged();
    }

    public void setData(ArrayList<User> users){
//        this.data.clear();
        this.data=users;
        notifyDataSetChanged();
    }

    public TopAdapter(Context context, ArrayList<User> data) {

        this.context=context;
        this.data = data;
        requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new RoundedCorners(Utils.ConvertDpToPixel(context, 10)));
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_top, parent, false);
            return new RecyclerViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_loadmore, parent, false);
            return new LoadMoreViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof RecyclerViewHolder) {
            final User item = data.get(position);
            RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
            viewHolder.tvGender.setText("Gioi tinh :Nam");
            Glide.with(context)
                    .load(R.drawable.rio)
                    .apply(requestOptions)
                    .into(viewHolder.imgUser);
            viewHolder.tvUserName.setText(item.getName());
            viewHolder.tvPhone.setText(item.getPhone());
            viewHolder.rlItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnClick.onClickItem(v, item, position);
                }
            });

        } else if (holder instanceof LoadMoreViewHolder) {
            LoadMoreViewHolder loadingViewHolder = (LoadMoreViewHolder) holder;
            loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
//        return data.size();
        return data == null ? 0 : data.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_user)
        ImageView imgUser;
        @BindView(R.id.tv_gender)
        TextView tvGender;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class LoadMoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

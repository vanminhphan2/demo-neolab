package com.example.rio.mvpapp.view.fragment.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rio.mvpapp.R;
import com.example.rio.mvpapp.data.prefs.SharedPrefsHelper;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.view.fragment.editaccount.EditAccountFragment;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SettingFragment extends Fragment implements SettingViewListener {


    @BindView(R.id.btn)
    Button btn;
    Unbinder unbinder;

    SharedPrefsHelper sharedPrefsHelper;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.frame_edit)
    FrameLayout frameEdit;
    private EditAccountFragment editAccountFragment;
    private User user;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(User user){
        SettingFragment settingFragment= new SettingFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("INFO_USER",user);
        settingFragment.setArguments(bundle);
        return settingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
//        sharedPrefsHelper = new SharedPrefsHelper();


        if (getArguments() != null) {

            user = (User) getArguments().getSerializable("INFO_USER");
            if (user != null) {
                Log.e("Rio ", "user - - user--> " + user.toString());
                Glide.with(this)
                        .load(R.drawable.rio)
                        .apply(RequestOptions.circleCropTransform())
                        .into(imgUser);
                tvUserName.setText(user.getName());
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addEditFrag();
                    }
                });
            }
        }
        return view;
    }

    private void addEditFrag(){
        editAccountFragment = EditAccountFragment.newInstance(user);
        getChildFragmentManager().beginTransaction()
                .add(R.id.frame_edit, editAccountFragment).addToBackStack("A").commit();
        frameEdit.setVisibility(View.VISIBLE);
    }

    public void setData(User user){

        tvUserName.setText(user.getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
    }
}

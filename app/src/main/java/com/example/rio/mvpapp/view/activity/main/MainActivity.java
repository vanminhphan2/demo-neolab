package com.example.rio.mvpapp.view.activity.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.rio.mvpapp.R;
import com.example.rio.mvpapp.adapter.MyPaperAdapter;
import com.example.rio.mvpapp.customview.CustomViewPager;
import com.example.rio.mvpapp.inf.MyOnClick;
import com.example.rio.mvpapp.model.ResultServer;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.view.fragment.setting.SettingFragment;
import com.example.rio.mvpapp.view.fragment.top.TopFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    private SettingFragment settingFragment;
    private User user;


    private View tabRFR, tabRA, tabLT;
    private MyPaperAdapter listPagerAdapter;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {

            user = (User) intent.getSerializableExtra("INFO_USER");
            if (user != null) {
                Log.e("Rio ", "user - - user--> " + user.toString());
                addControl();
            }
        }


    }

    private void addControl() {

        fragments.add(new TopFragment());
        settingFragment = SettingFragment.newInstance(user);
        fragments.add(settingFragment);

        FragmentManager manager = getSupportFragmentManager();
        listPagerAdapter = new MyPaperAdapter(manager, fragments);
        viewPager.setAdapter(listPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabRFR = LayoutInflater.from(this).inflate(R.layout.item_tablayout, null);
        tabRA = LayoutInflater.from(this).inflate(R.layout.item_tablayout, null);
        tabLayout.getTabAt(0).setCustomView(tabRFR);
        tabLayout.getTabAt(1).setCustomView(tabRA);
        initTabLayout(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        initTabLayout(0);
                        break;

                    case 1:
                        initTabLayout(1);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initTabLayout(int type) {
        try {
            ((TextView) tabRFR.findViewById(R.id.title)).setText("TOP");
            if (type == 0) {
                ((TextView) tabRFR.findViewById(R.id.title)).setTextColor(ContextCompat.getColor(this, android.R.color.white));
                ((TextView) tabRFR.findViewById(R.id.title)).setTypeface(((TextView) tabRFR.findViewById(R.id.title)).getTypeface(), Typeface.BOLD);

            } else {
                ((TextView) tabRFR.findViewById(R.id.title)).setTextColor(ContextCompat.getColor(this, R.color.textExtraColor));
                ((TextView) tabRFR.findViewById(R.id.title)).setTypeface(((TextView) tabRFR.findViewById(R.id.title)).getTypeface(), Typeface.NORMAL);
            }

            ((TextView) tabRA.findViewById(R.id.title)).setText("SETTING");
            if (type == 1) {
                ((TextView) tabRA.findViewById(R.id.title)).setTextColor(ContextCompat.getColor(this, android.R.color.white));
                ((TextView) tabRA.findViewById(R.id.title)).setTypeface(((TextView) tabRA.findViewById(R.id.title)).getTypeface(), Typeface.BOLD);

            } else {
                ((TextView) tabRA.findViewById(R.id.title)).setTextColor(ContextCompat.getColor(this, R.color.textExtraColor));
                ((TextView) tabRA.findViewById(R.id.title)).setTypeface(((TextView) tabRA.findViewById(R.id.title)).getTypeface(), Typeface.NORMAL);
            }
        } catch (NullPointerException e) {
            Log.e("Rio", "loi init Tablayout-->" + e.toString());
        }

    }

    @Override
    public void onBackPressed() {
        if (tabLayout.getTabAt(0).isSelected()) {
            super.onBackPressed();
        } else {
            if (settingFragment != null && settingFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
                Log.e("Rio ", "settingFragment 2222");
                settingFragment.getChildFragmentManager().popBackStack();
            } else {
                Log.e("Rio ", "settingFragment11111");
                super.onBackPressed();
            }
        }

    }
}

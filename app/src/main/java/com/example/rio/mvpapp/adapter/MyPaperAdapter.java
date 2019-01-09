package com.example.rio.mvpapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyPaperAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private boolean isAccess = true;
    private long baseId = 0;

    public MyPaperAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    public MyPaperAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public void setAccess(boolean access) {
        isAccess = access;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        if(isAccess) {
            return this.fragments.size();
        }else {
            return this.fragments.size() - 1;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return "Fragment "+position;
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        int index = fragments.indexOf (object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        super.destroyItem(container, position, object);
    }



    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
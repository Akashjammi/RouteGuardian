package com.jammi.akash.schoolbustracker.Adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jammi.akash.schoolbustracker.Fragement.Tab2fragment;
import com.jammi.akash.schoolbustracker.Fragement.TabFragment;

public  class ViewPagerAdapter extends FragmentPagerAdapter {

    private String title[] = {"Today's Status", "Monthly Status"};

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position ==0)
        return TabFragment.getInstance(position);
        else
         return Tab2fragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
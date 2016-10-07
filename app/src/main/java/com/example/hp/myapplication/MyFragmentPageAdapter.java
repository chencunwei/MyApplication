package com.example.hp.myapplication;

/**
 * Created by Hp on 2016/6/28.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    public MyFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MyFragment.newInstance(position);
            case 1:
                return MyFragment.newInstance(position);
            case 2:
                return MyFragment.newInstance(position);
            case 3:
                return MyFragment.newInstance(position);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}

package com.example.zymvvmsample.home;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public void removeIndex0Fragment(){
        fragments.remove(0);
        notifyDataSetChanged();
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    public int getListSize(){
        return fragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        if (fragments.contains(object)) {
            return fragments.indexOf(object);
        } else {
            return POSITION_NONE;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
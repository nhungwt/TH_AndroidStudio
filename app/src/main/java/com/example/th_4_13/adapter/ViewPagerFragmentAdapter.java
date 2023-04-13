package com.example.th_4_13.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.th_4_13.fragment.DashboardFragment;
import com.example.th_4_13.fragment.ListFragment;
import com.example.th_4_13.fragment.ProfileFragment;

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    int pageNum;
    public ViewPagerFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNum = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ListFragment();
            case 1:
                return new DashboardFragment();
            case 2:
                return new ProfileFragment();
        }
        return new ListFragment();
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}

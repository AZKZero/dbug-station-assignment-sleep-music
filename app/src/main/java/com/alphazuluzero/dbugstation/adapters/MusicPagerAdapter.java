package com.alphazuluzero.dbugstation.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MusicPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;
    private int mCurrentPosition;

    public MusicPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        /*Fragment fragment = fragments.get(position);
        return fragment instanceof MusicPageFragment ? ((MusicPageFragment) fragment).getTitle()
                : fragment instanceof ContentFragment ? ((ContentFragment) fragment).getTitle()
                : fragment.getTag();*/
        return "";
    }

    /*@Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (position != mCurrentPosition) {
            Fragment fragment = (Fragment) object;
            HotelPager pager = (HotelPager) container;
            if (fragment.getView() != null) {
                mCurrentPosition = position;
                pager.measureCurrentView(fragment.getView());
            }
        }
    }*/
}

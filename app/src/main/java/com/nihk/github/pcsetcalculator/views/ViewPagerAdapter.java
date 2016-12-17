package com.nihk.github.pcsetcalculator.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Created by Nick on 2016-12-16.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private SparseArray<Fragment> mRegisteredFragments = new SparseArray<>();

    private static final int NUM_PAGES = 2;
    private static final String CALCULATOR_PAGE_TITLE = "Calculator";
    private static final String SET_LIST_PAGE_TITLE = "List of Set Classes";

    public static final int CALCULATOR_PAGE_NUMBER = 0;
    public static final int SET_LIST_PAGE_NUMBER = 1;

    public ViewPagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case 0: return new CalculatorFragment();
            case 1: return new SetListFragment();
            default: return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return CALCULATOR_PAGE_TITLE;
            case 1: return SET_LIST_PAGE_TITLE;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mRegisteredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        mRegisteredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return mRegisteredFragments.get(position);
    }
}

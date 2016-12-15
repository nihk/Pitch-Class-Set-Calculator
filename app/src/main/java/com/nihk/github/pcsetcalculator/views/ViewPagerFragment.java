package com.nihk.github.pcsetcalculator.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nihk.github.pcsetcalculator.R;

import java.util.List;

/**
 * Created by Nick on 2016-11-27.
 */

public class ViewPagerFragment extends Fragment {
    private CalculatorFragment mCalculatorFragment;
    private SetListFragment mSetListFragment;
    private ViewPager mViewPager;

    private static final int NUM_PAGES = 2;
    private static final int CALCULATOR_PAGE = 0;
    private static final int SET_LIST_PAGE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_viewpager, container, false /* attachToRoot */);

        final FragmentManager childFragmentManager = getChildFragmentManager();
        final List<Fragment> fragments = childFragmentManager.getFragments();
        final boolean hasFragments = fragments != null;

        mCalculatorFragment = hasFragments
                ? (CalculatorFragment) fragments.get(CALCULATOR_PAGE)
                : new CalculatorFragment();

        mSetListFragment = hasFragments
                ? (SetListFragment) fragments.get(SET_LIST_PAGE)
                : new SetListFragment();

        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0: return mCalculatorFragment;
                    case 1: return mSetListFragment;
                    default: return null;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0: return "Calculator";
                    case 1: return "List of set classes";
                    default: return null;
                }
            }

            @Override
            public int getCount() {
                return NUM_PAGES;
            }
        });

        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    public void onSetListItemClicked(final String forteNumber) {
        mViewPager.setCurrentItem(CALCULATOR_PAGE, true /* smoothScroll */);
        mCalculatorFragment.injectPcSet(forteNumber);
    }
}

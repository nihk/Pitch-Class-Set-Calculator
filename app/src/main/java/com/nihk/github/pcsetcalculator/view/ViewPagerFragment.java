package com.nihk.github.pcsetcalculator.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nihk.github.pcsetcalculator.R;

/**
 * Created by Nick on 2016-11-27.
 */

public class ViewPagerFragment extends Fragment {
    public static final String NAME = "viewpager_fragment";
    private static final int NUM_PAGES = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_viewpager, container, false /* attachToRoot */);

        final Fragment calculatorFragment = new CalculatorFragment();
        final Fragment setListFragment = new SetListFragment();

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0: return calculatorFragment;
                    case 1: return setListFragment;
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
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}

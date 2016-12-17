package com.nihk.github.pcsetcalculator.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nihk.github.pcsetcalculator.R;

/**
 * Created by Nick on 2016-11-27.
 */

public class ViewPagerFragment extends Fragment {
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_viewpager, container, false /* attachToRoot */);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    public void onSetListItemClicked(final String forteNumber) {
        mViewPager.setCurrentItem(ViewPagerAdapter.CALCULATOR_PAGE_NUMBER, true /* smoothScroll */);
        final ViewPagerAdapter adapter = (ViewPagerAdapter) mViewPager.getAdapter();
        final CalculatorFragment calculatorFragment =
                (CalculatorFragment) adapter.getRegisteredFragment(ViewPagerAdapter.CALCULATOR_PAGE_NUMBER);
        calculatorFragment.injectPcSet(forteNumber);
    }
}

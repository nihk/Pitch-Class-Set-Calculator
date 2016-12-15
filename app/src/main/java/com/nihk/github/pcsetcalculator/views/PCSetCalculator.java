package com.nihk.github.pcsetcalculator.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nihk.github.pcsetcalculator.R;

public class PCSetCalculator extends AppCompatActivity {
    private static final String VIEW_PAGER_FRAGMENT = "viewPagerFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcsetcalculator);

        // Check if the view pager already exists
        ViewPagerFragment viewPagerFragment = (ViewPagerFragment) getSupportFragmentManager().findFragmentByTag(VIEW_PAGER_FRAGMENT);

        if (viewPagerFragment == null) {
            // Create a new view pager fragment
            viewPagerFragment = new ViewPagerFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_pcsetcalculator, viewPagerFragment, VIEW_PAGER_FRAGMENT)
                    .commit();
        }
    }
}

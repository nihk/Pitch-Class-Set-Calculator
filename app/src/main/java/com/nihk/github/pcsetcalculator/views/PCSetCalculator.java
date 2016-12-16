package com.nihk.github.pcsetcalculator.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                return true;
            case R.id.menu_rating_beg:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

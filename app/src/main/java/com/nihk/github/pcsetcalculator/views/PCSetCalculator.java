package com.nihk.github.pcsetcalculator.views;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

        if (getSupportFragmentManager().findFragmentByTag(VIEW_PAGER_FRAGMENT) == null) {
            final ViewPagerFragment viewPagerFragment = new ViewPagerFragment();

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
                goToPlayStorePage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToPlayStorePage() {
        final Context context = getApplicationContext();
        final Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        final Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY
                | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
                | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }
}

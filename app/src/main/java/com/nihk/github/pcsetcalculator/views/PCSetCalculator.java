package com.nihk.github.pcsetcalculator.views;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.utils.PreferencesUtils;

public class PCSetCalculator extends AppCompatActivity {
    private static final String VIEW_PAGER_FRAGMENT = "viewPagerFragment";
    private static final String PREFERENCES_FRAGMENT = "preferencesFragment";
    private static final String ABOUT_FRAGMENT = "aboutFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcsetcalculator);

        PreferencesUtils.initPreferences(this);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(VIEW_PAGER_FRAGMENT) == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_pcsetcalculator, new ViewPagerFragment(), VIEW_PAGER_FRAGMENT)
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
                goToSettingsFragment();
                return true;

            case R.id.menu_about:
                goToAboutFragment();
                return true;

            case R.id.menu_rating_beg:
                goToPlayStorePage();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToAboutFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        final AboutFragment aboutFragment;

        // Don't make a new prefs fragment if one is already open and visible. This won't be a scalable
        // solution if more fragments are introduced into the options menu.
        if ((aboutFragment = (AboutFragment) fragmentManager.findFragmentByTag(ABOUT_FRAGMENT)) == null
                || !aboutFragment.isVisible()) {
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_right, R.anim.exit_right, R.anim.pop_enter_right, R.anim.pop_exit_right)
                    .replace(android.R.id.content, new AboutFragment(), ABOUT_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void goToSettingsFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        final PrefsFragment prefsFragment;

        // Don't make a new prefs fragment if one is already open and visible. This won't be a scalable
        // solution if more fragments are introduced into the options menu.
        if ((prefsFragment = (PrefsFragment) fragmentManager.findFragmentByTag(PREFERENCES_FRAGMENT)) == null
                || !prefsFragment.isVisible()) {
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_bottom, R.anim.exit_bottom, R.anim.pop_enter_bottom, R.anim.pop_exit_bottom)
                    .replace(android.R.id.content, new PrefsFragment(), PREFERENCES_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
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

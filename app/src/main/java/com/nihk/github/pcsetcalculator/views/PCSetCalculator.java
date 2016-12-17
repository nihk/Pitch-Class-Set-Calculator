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
    private boolean mIsPreferencesFragmentVisible;

    private static final String KEY_PREF_FRAGMENT_VISIBLE = "isPreferencesFragmentVisible";
    private static final String VIEW_PAGER_FRAGMENT = "viewPagerFragment";
    private static final String PREFERENCES_FRAGMENT = "preferencesFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcsetcalculator);

        mIsPreferencesFragmentVisible = savedInstanceState != null
                && savedInstanceState.getBoolean(KEY_PREF_FRAGMENT_VISIBLE, false);
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
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_PREF_FRAGMENT_VISIBLE, mIsPreferencesFragmentVisible);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mIsPreferencesFragmentVisible = false;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                if (!mIsPreferencesFragmentVisible) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                            .replace(android.R.id.content, new PrefsFragment())
                            .addToBackStack(null)
                            .commit();
                    mIsPreferencesFragmentVisible = true;
                }
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

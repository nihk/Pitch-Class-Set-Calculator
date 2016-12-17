package com.nihk.github.pcsetcalculator.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nihk.github.pcsetcalculator.R;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by Nick on 2016-12-17.
 */

public class PrefsFragment extends PreferenceFragmentCompat {
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            // Fragments have a transparent background by default
            view.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.background_light));
        }

        return view;
    }

    @Override
    public void onCreatePreferencesFix(@Nullable final Bundle savedInstanceState, final String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}

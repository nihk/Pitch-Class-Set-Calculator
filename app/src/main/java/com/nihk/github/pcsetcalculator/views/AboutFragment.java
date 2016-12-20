package com.nihk.github.pcsetcalculator.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nihk.github.pcsetcalculator.R;

/**
 * Created by Nick on 2016-12-19.
 */

public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_about, container, false /* attachToRoot */);
        if (view != null) {
            // Fragments have a transparent background by default
            view.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.background_light));
        }

        return view;
    }
}

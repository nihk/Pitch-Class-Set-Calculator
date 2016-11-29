package com.nihk.github.pcsetcalculator.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.controllers.CalculatorController;

/**
 * Created by Nick on 2016-11-27.
 */

public class CalculatorFragment extends Fragment {
    private CalculatorController mController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false /* attachToRoot */);
        mController = new CalculatorController(view.findViewById(R.id.calculator_table));

        return view;
    }
}

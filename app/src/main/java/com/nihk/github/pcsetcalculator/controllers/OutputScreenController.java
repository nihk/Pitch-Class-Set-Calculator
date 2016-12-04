package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.TextView;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;
import com.nihk.github.pcsetcalculator.utils.StringFormatUtils;

/**
 * The screen which displays calculated statistics from the input screen, e.g. normal form,
 * interval vector, et al.
 */
public class OutputScreenController implements InputScreenController.Listener {
    private final TextView mNormalFormView;
    private final TextView mPrimeFormView;
    private final TextView mIntervalVectorView;
    private final TextView mForteNumberView;

    public OutputScreenController(View outputView) {
        mNormalFormView = (TextView) outputView.findViewById(R.id.normalForm);
        mPrimeFormView = (TextView) outputView.findViewById(R.id.primeForm);
        mIntervalVectorView = (TextView) outputView.findViewById(R.id.intervalVector);
        mForteNumberView = (TextView) outputView.findViewById(R.id.forteNumber);
    }

    @Override
    public void onInputScreenUpdated(PitchClassSet set) {
        mNormalFormView.setText(StringFormatUtils.makeNormalFormStringRepresentation(set));
        mPrimeFormView.setText(StringFormatUtils.makePrimeFormStringRepresentation(set));
        mIntervalVectorView.setText(StringFormatUtils.makeIntervalVectorStringRepresentation(set));
        mForteNumberView.setText(StringFormatUtils.makeForteNumberStringRepresentation(set));
    }
}

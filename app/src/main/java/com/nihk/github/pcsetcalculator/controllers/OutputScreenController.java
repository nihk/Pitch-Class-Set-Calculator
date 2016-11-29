package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.TextView;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.ForteNumber;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;
import com.nihk.github.pcsetcalculator.utils.IntervalVectorUtils;

import java.util.Locale;

/**
 * The screen which displays calculated statistics from the input screen, e.g. normal form,
 * interval vector, et al.
 */
public class OutputScreenController implements InputScreenController.Listener {
    private final TextView mNormalFormView;
    private final TextView mPrimeFormView;
    private final TextView mIntervalVectorView;
    private final TextView mForteNumberView;

    private static final String EMPTY_TEXT = "--";

    public OutputScreenController(View outputView) {
        mNormalFormView = (TextView) outputView.findViewById(R.id.normalForm);
        mPrimeFormView = (TextView) outputView.findViewById(R.id.primeForm);
        mIntervalVectorView = (TextView) outputView.findViewById(R.id.intervalVector);
        mForteNumberView = (TextView) outputView.findViewById(R.id.forteNumber);
    }

    @Override
    public void onInputUpdated(PitchClassSet set) {
        // TODO use correct formatters from somewhere else
        // TODO add proper string formats to PitchClassSet?
        mNormalFormView.setText(set.getNormalFormCollection().toString());
        mPrimeFormView.setText(set.getPrimeFormCollection().toString());
        mIntervalVectorView.setText(set.getIntervalVector().toString());

        if (set.getForteNumber() != null) {
            final StringBuilder stringBuilder = new StringBuilder();
            final ForteNumber forteNumber = set.getForteNumber();
            stringBuilder.append(forteNumber.toString());
            if (forteNumber.isZedRelated()) {
                stringBuilder.append(String.format(Locale.getDefault(),
                        IntervalVectorUtils.Z_MATE_FORMATTER,
                        IntervalVectorUtils.Z_MATES.get(forteNumber)));
            }
            mForteNumberView.setText(stringBuilder.toString());
        } else {
            setEmptyText(mForteNumberView);
        }
    }

    private void clearOutputs() {
        setEmptyText(mNormalFormView);
        setEmptyText(mPrimeFormView);
        setEmptyText(mIntervalVectorView);
        setEmptyText(mForteNumberView);
    }

    private void setEmptyText(TextView textView) {
        textView.setText(EMPTY_TEXT);
    }
}

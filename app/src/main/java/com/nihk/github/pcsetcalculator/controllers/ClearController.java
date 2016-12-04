package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.R;

/**
 * Created by Nick on 2016-11-28.
 */

public class ClearController {
    private final View mCalculatorView;
    private final Button mClearButton;
    private Listener mListener;

    public interface Listener {
        void onClearButtonPressed();
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public ClearController(View calculatorView) {
        mCalculatorView = calculatorView;
        mClearButton = (Button) mCalculatorView.findViewById(R.id.buttonClear);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mListener.onClearButtonPressed();
            }
        });
    }
}

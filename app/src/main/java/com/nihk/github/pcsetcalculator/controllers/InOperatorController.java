package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.R;

/**
 * Created by Nick on 2016-11-28.
 */

public class InOperatorController extends OperatorController {
    private final View mCalculatorView;
    private final Button mInButton;

    @Override
    public Button getButton() {
        return mInButton;
    }

    public InOperatorController(View calculatorView) {
        mCalculatorView = calculatorView;
        mInButton = (Button) mCalculatorView.findViewById(R.id.buttonIn);
        mInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnyNumberButtonPressed()) {
                    toggleButton();
                    callListener(OperatorController.IN);
                } else {
                    makeToast(mCalculatorView.getContext());
                }
            }
        });
    }
}

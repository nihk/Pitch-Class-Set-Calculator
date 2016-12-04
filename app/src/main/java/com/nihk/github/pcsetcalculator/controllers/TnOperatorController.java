package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.R;

/**
 * Created by Nick on 2016-11-28.
 */

public class TnOperatorController extends OperatorController {
    private final View mCalculatorView;
    private final Button mTnButton;

    public TnOperatorController(View calculatorView) {
        mCalculatorView = calculatorView;
        mTnButton = (Button) mCalculatorView.findViewById(R.id.buttonTn);
        mTnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callListener(OperatorController.TN);
            }
        });
    }
}

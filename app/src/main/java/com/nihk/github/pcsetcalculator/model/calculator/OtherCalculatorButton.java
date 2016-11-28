package com.nihk.github.pcsetcalculator.model.calculator;

import android.widget.Button;

import com.nihk.github.pcsetcalculator.model.calculator.listeners.OtherButtonListener;

/**
 * A class for the non-numbered calculator buttons, e.g. undo, clear, Tn, and In
 */
public class OtherCalculatorButton extends CalculatorButton {
    private OtherButtonListener mListener;

    public void setListener(OtherButtonListener listener) {
        mListener = listener;
    }

    public OtherCalculatorButton(Button button) {

    }
}

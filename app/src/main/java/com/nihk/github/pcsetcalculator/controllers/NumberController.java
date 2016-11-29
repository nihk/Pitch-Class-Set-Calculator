package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;

/**
 * Created by Nick on 2016-11-27.
 */

public class NumberController {
    public interface Listener {
        void onNumberButtonPressed(NumberController button);
    }

    private final Button mButton;
    private Listener mListener;
    private final int mBinaryValue;
    private boolean mIsOn;
    private boolean mIsOperatorModified;

    public int getBinaryValue() {
        return mBinaryValue;
    }

    public boolean isOn() {
        return mIsOn;
    }

    public boolean isOperatorModified() {
        return mIsOperatorModified;
    }

    public void setOperatorModified(boolean operatorModified) {
        mIsOperatorModified = operatorModified;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public NumberController(Button button) {
        mButton = button;
        final CharSequence buttonText = mButton.getText();
        mBinaryValue = SetTheoryUtils.PC_BITS.get(buttonText.toString());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onNumberButtonPressed(NumberController.this);
                // Toggle the state on each click
                mIsOn = !mIsOn;
            }
        });
    }
}

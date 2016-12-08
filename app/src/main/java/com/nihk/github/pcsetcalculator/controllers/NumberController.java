package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;

import static com.nihk.github.pcsetcalculator.controllers.OperatorController.*;
import static com.nihk.github.pcsetcalculator.utils.ButtonColourUtils.*;

/**
 * Created by Nick on 2016-11-27.
 */

public class NumberController {
    public interface Listener {
        void onNumberButtonPressed(NumberController button);
    }

    private final Button mButton;
    private Listener mListener;
    private final int mBinaryPcValue;
    private final int mValue;
    // Whether the input screen PC set shares a value with this button's binary value
    private boolean mIsOn;
    private @OperatorModified int mOperatorModification = OperatorController.NONE;

    public int getBinaryPcValue() {
        return mBinaryPcValue;
    }

    public boolean isOn() {
        return mIsOn;
    }

    public void setOn(boolean isOn) {
        mIsOn = isOn;
        setButtonColour(mButton, mIsOn
                ? DARK_PINK
                : CLEAR_COLOUR);
    }

    @OperatorModified
    public int getOperatorModification() {
        return mOperatorModification;
    }

    public void setOperatorModified(@OperatorModified int operatorModified) {
        mOperatorModification = operatorModified;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public int getValue() {
        return mValue;
    }

    public NumberController(Button button) {
        mButton = button;
        final String buttonText = String.valueOf(mButton.getText());
        mBinaryPcValue = SetTheoryUtils.PC_BITS.get(buttonText);
        mValue = Integer.parseInt(buttonText, 12);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onNumberButtonPressed(NumberController.this);
            }
        });
    }
}

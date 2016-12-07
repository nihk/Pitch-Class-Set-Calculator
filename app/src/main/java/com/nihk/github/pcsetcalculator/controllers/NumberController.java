package com.nihk.github.pcsetcalculator.controllers;

import android.graphics.PorterDuff;
import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;

import static com.nihk.github.pcsetcalculator.controllers.OperatorController.*;

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
    private static final int GREEN = 0xFF7D8A2E;
    private static final int SOFT_BLUE = 0xFFC4D7ED;
    private static final int DARK_SOFT_BLUE = 0xFF9ABED6;
    private static final int SOFT_RED = 0xFFF3B59B;
    private static final int DARK_SOFT_RED = 0xFFF29C9C;
    private static final int DEFAULT_COLOUR = 0;

    public int getBinaryPcValue() {
        return mBinaryPcValue;
    }

    public boolean isOn() {
        return mIsOn;
    }

    public void setOn(boolean isOn) {
        mIsOn = isOn;
        if (mIsOn) {
            mButton.getBackground().setColorFilter(GREEN, PorterDuff.Mode.MULTIPLY);
        } else {
            mButton.getBackground().clearColorFilter();
        }
    }

    @OperatorModified
    public int getOperatorModification() {
        return mOperatorModification;
    }

    public void setOperatorModified(@OperatorModified int operatorModified) {
        mOperatorModification = operatorModified;
        switch (operatorModified) {
            case TN: setButtonColour(mIsOn ? DARK_SOFT_BLUE : SOFT_BLUE); break;
            case IN: setButtonColour(mIsOn ? DARK_SOFT_RED : SOFT_RED); break;
            case NONE:  // fall through
            default: setButtonColour(DEFAULT_COLOUR); break;
        }
    }

    private void setButtonColour(int colour) {
        if (colour == DEFAULT_COLOUR) {
            mButton.getBackground().clearColorFilter();
        } else {
            mButton.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
        }
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

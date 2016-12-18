package com.nihk.github.pcsetcalculator.controllers;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.utils.PreferencesUtils;
import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;

import static com.nihk.github.pcsetcalculator.controllers.OperatorController.*;
import static com.nihk.github.pcsetcalculator.utils.ButtonColourUtils.*;
import static com.nihk.github.pcsetcalculator.utils.PreferencesUtils.hasPcsAorBorTorECharacter;
import static com.nihk.github.pcsetcalculator.utils.PreferencesUtils.registerListener;

/**
 * Created by Nick on 2016-11-27.
 */

public class NumberController implements SharedPreferences.OnSharedPreferenceChangeListener {
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
                : INACTIVE);
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
        mValue = getValue(buttonText);

        if (hasPcsAorBorTorECharacter(buttonText)) {
            registerListener(this);
            maybeSwapAndBwithTandEOrViceVersa();
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onNumberButtonPressed(NumberController.this);
            }
        });
    }

    private int getValue(final String buttonText) {
        switch (buttonText) {
            case "A":  // fall through
            case "T": return 10;
            case "B":  // fall through
            case "E": return 11;
            default: return Integer.parseInt(buttonText);
        }
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences,
                                          final String key) {
        if (key.equals(PreferencesUtils.KEY_T_AND_E)) {
            maybeSwapAndBwithTandEOrViceVersa();
        }
    }

    private void maybeSwapAndBwithTandEOrViceVersa() {
        String buttonText = String.valueOf(mButton.getText());
        mButton.setText(PreferencesUtils.swapAandBwithTandEOrViceVersa(buttonText));
    }
}

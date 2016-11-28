package com.nihk.github.pcsetcalculator.model.calculator;

import android.view.View;
import android.widget.TextView;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.model.PitchClassSet;
import com.nihk.github.pcsetcalculator.model.calculator.listeners.InputUpdatedListener;
import com.nihk.github.pcsetcalculator.model.calculator.listeners.NumberButtonListener;
import com.nihk.github.pcsetcalculator.model.calculator.listeners.OtherButtonListener;

import java.util.Stack;

/**
 * The uppermost screen on the calculator which displays the user's input.
 */
public class InputScreen implements NumberButtonListener, OtherButtonListener {
    private final TextView mInputView;
    private InputUpdatedListener mListener;
    private PitchClassSet mPitchClassSet;
    private boolean isTnOrInButtonActive;
    // TODO rethink how to do this. Don't want to store a shit load of objects.. or do I.
    // (it's for the undo button)
    private final Stack<PitchClassSet> mInputStack;

    public void setListener(InputUpdatedListener listener) {
        mListener = listener;
    }

    public InputScreen(View inputView) {
        mInputView = (TextView) inputView.findViewById(R.id.input_screen);
        mInputStack = new Stack<>();
    }

    @Override
    public void onNumberButtonPressed(NumberCalculatorButton button) {
        int inputBinaryValue = button.getBinaryValue();
        int newBinaryValue;
        if (mPitchClassSet == null) {
            newBinaryValue = inputBinaryValue;
        } else {
            if (button.isOn()) {
                newBinaryValue = mPitchClassSet.getOriginalSetBinary() & ~inputBinaryValue;
            } else {
                newBinaryValue = mPitchClassSet.getOriginalSetBinary() | inputBinaryValue;
            }
        }
        mPitchClassSet = PitchClassSet.fromBinary(newBinaryValue);
        mInputView.setText(mPitchClassSet.getCollection().toString());
        mListener.onInputUpdated(mPitchClassSet);
    }

    @Override
    public void onOtherButtonClicked(OtherCalculatorButton button) {
        // TODO
    }
}

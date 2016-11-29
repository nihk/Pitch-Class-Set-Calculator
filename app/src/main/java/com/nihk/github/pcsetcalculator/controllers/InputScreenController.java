package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.TextView;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;

import java.util.Stack;

/**
 * The uppermost screen on the calculator which displays the user's input.
 */
public class InputScreenController implements NumberController.Listener {
    public interface Listener {
        void onInputUpdated(PitchClassSet set);
    }

    private final TextView mInputView;
    private Listener mListener;
    private PitchClassSet mPitchClassSet;
    private boolean isTnOrInButtonActive;
    // TODO rethink how to do this. Don't want to store a shit load of objects.. or do I.
    // (it's for the undo button). Just remove at index 0 when size >= 100
    private final Stack<PitchClassSet> mInputStack;

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public InputScreenController(View inputView) {
        mInputView = (TextView) inputView.findViewById(R.id.input_screen);
        mInputStack = new Stack<>();
    }

    @Override
    public void onNumberButtonPressed(NumberController button) {
        int inputBinaryValue = button.getBinaryValue();
        int newBinaryValue;
        if (mPitchClassSet == null) {
            newBinaryValue = inputBinaryValue;
        } else {
            // TODO move these bitwise operations to SetTheoryUtils
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
}

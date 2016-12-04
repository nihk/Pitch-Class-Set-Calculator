package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.TextView;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;
import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;
import com.nihk.github.pcsetcalculator.utils.StringFormatUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The uppermost screen on the calculator which displays the user's input.
 */
public class InputScreenController implements NumberController.Listener,
        ClearController.Listener, UndoController.Listener {

    public interface Listener {
        void onInputScreenUpdated(PitchClassSet set);
    }

    private final TextView mInputView;
    private List<Listener> mListeners;
    private PitchClassSet mPitchClassSet;
    private final Stack<PitchClassSet> mInputStack;

    private static final int MAX_STACK_SIZE = 50;

    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    public InputScreenController(View inputView) {
        mInputView = (TextView) inputView.findViewById(R.id.input_screen);
        mListeners = new ArrayList<>();
        mInputStack = new Stack<>();
    }

    @Override
    public void onNumberButtonPressed(NumberController button) {
        storeRecentPcSet();

        if (button.getOperatorModification() != OperatorController.NONE) {
            handleTnOrInAction(button);
        } else {
            handlePcAddedOrRemoved(button);
        }

        updateInputScreenText();
    }

    private void updateInputScreenText() {
        mInputView.setText(StringFormatUtils.makeUnorderedSetStringRepresentation(mPitchClassSet));

        for (Listener listener : mListeners) {
            listener.onInputScreenUpdated(mPitchClassSet);
        }
    }

    private void handleTnOrInAction(NumberController button) {
        // Ignore empty sets
        if (mPitchClassSet != null) {
            int originalSet = mPitchClassSet.getOriginalSetBinary();
            int tnValue = button.getValue();
            int modifiedSet;

            if (button.getOperatorModification() == OperatorController.IN) {
                modifiedSet = SetTheoryUtils.invertThenTranspose(originalSet, tnValue);
            } else {  // OperatorController.TN
                modifiedSet = SetTheoryUtils.transpose(originalSet, tnValue);
            }

            mPitchClassSet = PitchClassSet.fromBinary(modifiedSet);
        }
    }

    private void handlePcAddedOrRemoved(NumberController button) {
        int inputBinaryValue = button.getBinaryPcValue();
        int newBinaryValue;
        if (mPitchClassSet == null) {
            newBinaryValue = inputBinaryValue;
        } else {
            if (button.isOn()) {
                newBinaryValue = SetTheoryUtils.removePc(mPitchClassSet.getOriginalSetBinary(), inputBinaryValue);
            } else {
                newBinaryValue = SetTheoryUtils.addPc(mPitchClassSet.getOriginalSetBinary(), inputBinaryValue);
            }
        }

        mPitchClassSet = PitchClassSet.fromBinary(newBinaryValue);
    }

    private void storeRecentPcSet() {
        mInputStack.push(mPitchClassSet);

        // Stack is capped at MAX_STACK_SIZE to avoid any potential huge memory leakage
        if (mInputStack.size() > MAX_STACK_SIZE) {
            mInputStack.remove(0);
        }
    }

    @Override
    public void onClearButtonPressed() {
        storeRecentPcSet();
        mPitchClassSet = null;
        updateInputScreenText();
    }

    // This ignores the operator (Tn/In) buttons; only PC sets are stored in the stack.
    // In future this might change, but ignoring it should be the simplest interaction for the
    // user experience.
    @Override
    public void onUndoButtonPressed() {
        if (mInputStack.size() > 0) {
            mPitchClassSet = mInputStack.pop();
        }
        updateInputScreenText();
    }
}

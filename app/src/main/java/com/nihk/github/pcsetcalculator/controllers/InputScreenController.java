package com.nihk.github.pcsetcalculator.controllers;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.ForteNumber;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;
import com.nihk.github.pcsetcalculator.utils.PreferencesUtils;
import com.nihk.github.pcsetcalculator.utils.RahnForteUtils;
import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;
import com.nihk.github.pcsetcalculator.utils.StringFormatUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The uppermost screen on the calculator which displays the user's input.
 */
public class InputScreenController implements NumberController.Listener,
        ClearController.Listener, UndoController.Listener, SharedPreferences.OnSharedPreferenceChangeListener {
    private final TextView mInputView;
    private List<Listener> mListeners;
    private PitchClassSet mPitchClassSet;
    private Stack<PitchClassSet> mInputStack;

    private static final int MAX_STACK_SIZE = 100;  // somewhat arbitrary
    private static final String KEY_ACTIVE_PC_SET = "activePitchClassSet";
    private static final String KEY_STACK_OF_PC_SETS = "stackOfPcSets";
    public static final String KEY_BUNDLE = "inputScreenControllerBundle";

    public interface Listener {
        void onInputScreenUpdated(PitchClassSet set);
    }

    public void registerListener(Listener... listeners) {
        for (Listener listener : listeners) {
            mListeners.add(listener);
        }
    }

    public InputScreenController(View inputView) {
        mInputView = (TextView) inputView.findViewById(R.id.input_screen);
        mListeners = new ArrayList<>();
        mInputStack = new Stack<>();
        mPitchClassSet = PitchClassSet.emptySet();
        PreferencesUtils.registerListener(this);
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
        if (!mPitchClassSet.isEmpty()) {
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
        if (mPitchClassSet.isEmpty()) {
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
        // No point in clearing an already empty set
        if (!mPitchClassSet.isEmpty()) {
            storeRecentPcSet();
            setPitchClassSetAndUpdateScreen(PitchClassSet.emptySet());
        }
    }

    // This ignores the operator (Tn/In) buttons; only PC sets are stored in the stack.
    // In future this might change, but ignoring it should be the simplest interaction for the
    // user experience.
    @Override
    public void onUndoButtonPressed() {
        if (mInputStack.size() > 0) {
            setPitchClassSetAndUpdateScreen(mInputStack.pop());
        }
    }

    private void setPitchClassSetAndUpdateScreen(final PitchClassSet pitchClassSet) {
        mPitchClassSet = pitchClassSet;
        updateInputScreenText();
    }

    public Bundle getInputScreenBundle() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_ACTIVE_PC_SET, mPitchClassSet);
        final ArrayList<PitchClassSet> list = new ArrayList<>(mInputStack);
        bundle.putParcelableArrayList(KEY_STACK_OF_PC_SETS, list);

        return bundle;
    }

    public void setScreenFromBundle(final Bundle bundle) {
        final PitchClassSet pitchClassSet = bundle.getParcelable(KEY_ACTIVE_PC_SET);
        final ArrayList<PitchClassSet> list = bundle.getParcelableArrayList(KEY_STACK_OF_PC_SETS);
        mInputStack.addAll(list);
        setPitchClassSetAndUpdateScreen(pitchClassSet);
    }

    // Displays a PitchClassSet that did not originate from the buttons themselves. Currently
    // this only comes from hitting a list item on the list of set classes page.
    public void injectPcSet(final String forteNumber) {
        storeRecentPcSet();
        final ForteNumber fn = new ForteNumber(forteNumber);
        setPitchClassSetAndUpdateScreen(PitchClassSet.fromForte(fn));
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
        final boolean isAlgorithmChange = key.equals(PreferencesUtils.KEY_FORTE_ALGORITHM);
        if (key.equals(PreferencesUtils.KEY_T_AND_E) || isAlgorithmChange) {
            // Recreate the instance; PitchClassSet will handle the preference changes internally
            mPitchClassSet = PitchClassSet.fromBinary(mPitchClassSet.getOriginalSetBinary());
            updateInputScreenText();

            // Only need to update the stack if its an algorithm change. T/E and A/B changes
            // aren't bound the the PitchClassSet objects; they are set to the input/output
            // screens through StringFormatUtils static methods
            if (isAlgorithmChange) {
                // Handle Rahn/Forte primes in the stack
                for (int i = 0; i < mInputStack.size(); i++) {
                    final PitchClassSet pitchClassSet = mInputStack.get(i);
                    if (RahnForteUtils.isPrimeFormDifferentDependingOnAlgorithm(pitchClassSet)) {
                        mInputStack.set(i, PitchClassSet.fromBinary(pitchClassSet.getOriginalSetBinary()));
                    }
                }
            }
        }
    }
}

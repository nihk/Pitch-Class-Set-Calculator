package com.nihk.github.pcsetcalculator.controllers;

import android.util.SparseArray;
import android.view.View;

import com.nihk.github.pcsetcalculator.models.PitchClassSet;

import static com.nihk.github.pcsetcalculator.controllers.OperatorController.*;

/**
 * Created by Nick on 2016-12-07.
 */

public class OperatorGroupController implements OperatorController.Listener,
        InputScreenController.Listener {
    private SparseArray<OperatorController> mOperatorControllers;
    private View mCalculatorView;
    private Listener mListener;

    public interface Listener {
        void onOperatorControllerClicked(@OperatorModified final int operatorModified);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void registerAllGroupMembersListener(AnyNumberButtonsPressedListener listener) {
        for (int i = 0; i < mOperatorControllers.size(); i++) {
            @OperatorModified final int key = mOperatorControllers.keyAt(i);
            final OperatorController operatorController = mOperatorControllers.get(key);
            operatorController.setListener(listener);
        }
    }

    public OperatorGroupController(final View calculatorView) {
        mCalculatorView = calculatorView;
        setupOperatorButtons();
    }

    private void setupOperatorButtons() {
        mOperatorControllers = new SparseArray<OperatorController>() {{
            put(OperatorController.TN, new TnOperatorController(mCalculatorView));
            put(OperatorController.IN, new InOperatorController(mCalculatorView));
        }};

        for (int i = 0; i < mOperatorControllers.size(); i++) {
            final int key = mOperatorControllers.keyAt(i);
            final OperatorController operatorController = mOperatorControllers.get(key);
            operatorController.setListener(this);
        }
    }

    // Turn every other operator button off except the one that was just clicked
    @Override
    public void onOperatorButtonClicked(@OperatorModified final int operatorModification) {
        turnOffEveryOperatorButtonExcept(operatorModification);
        mListener.onOperatorControllerClicked(operatorModification);
    }

    @Override
    public void onInputScreenUpdated(final PitchClassSet set) {
        turnOffEveryOperatorButtonExcept(OperatorController.NONE);
    }

    // Passing null implies turning every button off
    private void turnOffEveryOperatorButtonExcept(@OperatorModified final int buttonId) {
        for (int i = 0; i < mOperatorControllers.size(); i++) {
            @OperatorModified final int key = mOperatorControllers.keyAt(i);
            if (buttonId != key) {
                final OperatorController operatorController = mOperatorControllers.get(key);
                operatorController.turnButtonOff();
            }
        }
    }
}

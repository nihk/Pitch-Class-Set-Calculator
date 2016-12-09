package com.nihk.github.pcsetcalculator.controllers;

import android.os.Bundle;
import android.view.View;

/**
 * A controller for the calculator button inputs and outputs.
 */
public class CalculatorController {
    private final View mCalculatorView;

    private final NumberGroupController mNumberGroupController;
    private final InputScreenController mInputScreenController;
    private final OutputScreenController mOutputScreenController;
    private final OperatorGroupController mOperatorGroupController;
    private final UndoController mUndo;
    private final ClearController mClear;

    public static final String KEY_CALCULATOR_CONTROLLER = "calculatorController";

    public CalculatorController(View view) {
        mCalculatorView = view;

        mNumberGroupController = new NumberGroupController(mCalculatorView);
        mInputScreenController = new InputScreenController(mCalculatorView);
        mOutputScreenController = new OutputScreenController(mCalculatorView);
        mOperatorGroupController = new OperatorGroupController(mCalculatorView);
        mUndo = new UndoController(mCalculatorView);
        mClear = new ClearController(mCalculatorView);

        mNumberGroupController.setListener(mInputScreenController);
        mInputScreenController.registerListener(mOutputScreenController, mNumberGroupController,
                mOperatorGroupController);
        mClear.setListener(mInputScreenController);
        mUndo.setListener(mInputScreenController);
        mOperatorGroupController.setListener(mNumberGroupController);
        mOperatorGroupController.registerAllGroupMembersListener(mNumberGroupController);
    }

    public Bundle getCalculatorBundle() {
        final Bundle bundle = new Bundle();
        bundle.putBundle(InputScreenController.KEY_BUNDLE, mInputScreenController.getInputScreenBundle());
        bundle.putBundle(OperatorGroupController.KEY_OPERATOR_GROUP, mOperatorGroupController.getOperatorGroupBundle());

        return bundle;
    }

    public void setCalculatorFromBundle(final Bundle bundle) {
        final Bundle inputScreenBundle = bundle.getBundle(InputScreenController.KEY_BUNDLE);
        final Bundle operatorGroupBundle = bundle.getBundle(OperatorGroupController.KEY_OPERATOR_GROUP);
        mInputScreenController.setScreenFromBundle(inputScreenBundle);
        mOperatorGroupController.setOperatorGroupFromBundle(operatorGroupBundle);
    }
}

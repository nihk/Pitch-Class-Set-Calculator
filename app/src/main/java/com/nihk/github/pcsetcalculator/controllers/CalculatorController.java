package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;

/**
 * A controller for the calculator button inputs and outputs.
 */
public class CalculatorController {
    private final View mCalculatorView;

    private final NumberGroupController mNumberGroupController;
    private final InputScreenController mInputScreenController;
    private final OutputScreenController mOutputScreenController;
    private final UndoController mUndo;
    private final ClearController mClear;
    private final OperatorController mTn;
    private final OperatorController mIn;

    public CalculatorController(View view) {
        mCalculatorView = view;

        mNumberGroupController = new NumberGroupController(mCalculatorView);
        mInputScreenController = new InputScreenController(mCalculatorView);
        mOutputScreenController = new OutputScreenController(mCalculatorView);
        mUndo = new UndoController(mCalculatorView);
        mClear = new ClearController(mCalculatorView);
        mTn = new TnOperatorController(mCalculatorView);
        mIn = new InOperatorController(mCalculatorView);

        mNumberGroupController.setListener(mInputScreenController);
        mInputScreenController.setListener(mOutputScreenController);
        mTn.setListener(mNumberGroupController);
        mIn.setListener(mNumberGroupController);
    }
}

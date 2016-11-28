package com.nihk.github.pcsetcalculator.controller;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.model.calculator.InputScreen;
import com.nihk.github.pcsetcalculator.model.calculator.NumberCalculatorButton;
import com.nihk.github.pcsetcalculator.model.calculator.OtherCalculatorButton;
import com.nihk.github.pcsetcalculator.model.calculator.OutputScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * A controller for the calculator button inputs and outputs.
 */
public class CalculatorController {
    private final Context mContext;
    private final View mCalculatorView;

    private InputScreen mInputScreen;
    private OutputScreen mOutputScreen;
    private List<NumberCalculatorButton> mNumberButtons;
    private List<OtherCalculatorButton> mOtherButtons;

    public CalculatorController(Context context, View view) {
        mContext = context;
        mCalculatorView = view;
        mInputScreen = new InputScreen(view);
        mOutputScreen = new OutputScreen(view);
        mNumberButtons = new ArrayList<>();

        setupCalculatorButtons();
        mInputScreen.setListener(mOutputScreen);
    }

    private void setupCalculatorButtons() {
        mNumberButtons = new ArrayList<NumberCalculatorButton>() {{
            add(makeNumberButton(R.id.button0));
            add(makeNumberButton(R.id.button1));
            add(makeNumberButton(R.id.button2));
            add(makeNumberButton(R.id.button3));
            add(makeNumberButton(R.id.button4));
            add(makeNumberButton(R.id.button5));
            add(makeNumberButton(R.id.button6));
            add(makeNumberButton(R.id.button7));
            add(makeNumberButton(R.id.button8));
            add(makeNumberButton(R.id.button9));
            add(makeNumberButton(R.id.buttonA));
            add(makeNumberButton(R.id.buttonB));
        }};

        for (NumberCalculatorButton button : mNumberButtons) {
            button.setListener(mInputScreen);
        }

        mOtherButtons = new ArrayList<OtherCalculatorButton>() {{
            add(makeOtherButton(R.id.buttonUndo));
            add(makeOtherButton(R.id.buttonClear));
            add(makeOtherButton(R.id.buttonTn));
            add(makeOtherButton(R.id.buttonIn));
        }};

        for (OtherCalculatorButton button : mOtherButtons) {
            button.setListener(mInputScreen);
        }
    }

    private NumberCalculatorButton makeNumberButton(int id) {
        return new NumberCalculatorButton((Button) mCalculatorView.findViewById(id));
    }

    private OtherCalculatorButton makeOtherButton(int id) {
        return new OtherCalculatorButton((Button) mCalculatorView.findViewById(id));
    }
}

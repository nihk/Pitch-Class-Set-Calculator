package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 2016-11-28.
 */

public class NumberGroupController implements OperatorController.Listener {
    private View mCalculatorView;
    private List<NumberController> mNumberControllers;

    public NumberGroupController(View calculatorView) {
        mCalculatorView = calculatorView;
        setupNumberButtons();
    }

    private void setupNumberButtons() {
        mNumberControllers = new ArrayList<NumberController>() {{
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
    }

    private NumberController makeNumberButton(int id) {
        return new NumberController((Button) mCalculatorView.findViewById(id));
    }

    public void setListener(NumberController.Listener listener) {
        for (NumberController button : mNumberControllers) {
            button.setListener(listener);
        }
    }

    @Override
    public void onOperatorButtonClicked(boolean isOperatorModified) {
        for (NumberController button : mNumberControllers) {
            button.setOperatorModified(isOperatorModified);
        }
    }
}

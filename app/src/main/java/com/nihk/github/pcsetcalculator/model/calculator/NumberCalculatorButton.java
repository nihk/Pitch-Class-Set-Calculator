package com.nihk.github.pcsetcalculator.model.calculator;

import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.model.calculator.listeners.NumberButtonListener;
import com.nihk.github.pcsetcalculator.model.calculator.listeners.OperatorButtonListener;
import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;

/**
 * Created by Nick on 2016-11-27.
 */

public class NumberCalculatorButton extends CalculatorButton
                                    implements OperatorButtonListener {
    private final Button mButton;
    private NumberButtonListener mListener;
    private final int mBinaryValue;
    private boolean isOn;
    private boolean isOperatorModified;

    public int getBinaryValue() {
        return mBinaryValue;
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean isOperatorModified() {
        return isOperatorModified;
    }

    public void setListener(NumberButtonListener listener) {
        mListener = listener;
    }

    public NumberCalculatorButton(Button button) {
        mButton = button;
        final CharSequence buttonText = mButton.getText();
        mBinaryValue = SetTheoryUtils.PC_BITS.get(buttonText.toString());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the state on each click
                mListener.onNumberButtonPressed(NumberCalculatorButton.this);
                isOn = !isOn;
            }
        });
    }

    @Override
    public void onOperatorButtonClicked(boolean isOn) {
        isOperatorModified = isOn;
    }
}

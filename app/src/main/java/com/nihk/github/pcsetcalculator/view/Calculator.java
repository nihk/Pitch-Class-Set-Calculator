package com.nihk.github.pcsetcalculator.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;

import com.nihk.github.pcsetcalculator.R;

/**
 * Created by Nick on 2016-11-26.
 */

public class Calculator extends TableLayout {
    private CalculatorButtons mCalculatorButtons;

    public Calculator(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.calculator, this, true);

        // TODO make buttons clickable to have a sound. Make them simultaneously sound
        // TODO pressing Tn button will light all the numbered buttons (+ itself) up blue, In
        // will light them up (+ itself) red
    }
}

package com.nihk.github.pcsetcalculator.controllers;

import android.view.View;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.R;

/**
 * Created by Nick on 2016-11-28.
 */

public class UndoController {
    private final View mCalculatorView;
    private final Button mUndoButton;
    private Listener mListener;

    public interface Listener {
        void onUndoButtonPressed();
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public UndoController(View calculatorView) {
        mCalculatorView = calculatorView;
        mUndoButton = (Button) mCalculatorView.findViewById(R.id.buttonUndo);
        mUndoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mListener.onUndoButtonPressed();
            }
        });
    }
}

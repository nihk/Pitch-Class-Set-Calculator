package com.nihk.github.pcsetcalculator.controllers;

/**
 * Created by Nick on 2016-11-28.
 */

public abstract class OperatorController {
    private Listener mListener;

    public interface Listener {
        void onOperatorButtonClicked(boolean isOperatorModified);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void callListener(boolean isOperatorModified) {
        mListener.onOperatorButtonClicked(isOperatorModified);
    }
}

package com.nihk.github.pcsetcalculator.controllers;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Nick on 2016-11-28.
 */

public abstract class OperatorController {
    private Listener mListener;

    @Retention(SOURCE)
    @IntDef({ NONE, TN, IN })
    public @interface OperatorModified {}
    public static final int NONE = -1;
    public static final int TN = 0;
    public static final int IN = 1;

    public interface Listener {
        void onOperatorButtonClicked(@OperatorModified int operatorModification);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void callListener(@OperatorModified int operatorModification) {
        mListener.onOperatorButtonClicked(operatorModification);
    }
}

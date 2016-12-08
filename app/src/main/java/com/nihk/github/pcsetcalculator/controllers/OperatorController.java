package com.nihk.github.pcsetcalculator.controllers;

import android.content.Context;
import android.support.annotation.IntDef;
import android.widget.Button;
import android.widget.Toast;

import com.nihk.github.pcsetcalculator.models.PitchClassSet;

import java.lang.annotation.Retention;

import static com.nihk.github.pcsetcalculator.utils.ButtonColourUtils.CLEAR_COLOUR;
import static com.nihk.github.pcsetcalculator.utils.ButtonColourUtils.PLUM;
import static com.nihk.github.pcsetcalculator.utils.ButtonColourUtils.setButtonColour;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Nick on 2016-11-28.
 */

public abstract class OperatorController implements InputScreenController.Listener {
    private Listener mListener;
    private boolean mIsOn;
    private AnyNumberButtonsPressedListener mAnyNumberButtonsPressedListener;

    public abstract Button getButton();

    @Retention(SOURCE)
    @IntDef({ NONE, TN, IN })
    public @interface OperatorModified {}
    public static final int NONE = -1;
    public static final int TN = 0;
    public static final int IN = 1;

    public interface Listener {
        void onOperatorButtonClicked(@OperatorModified int operatorModification);
    }

    public interface AnyNumberButtonsPressedListener {
        boolean isAnyNumberButtonPressed();
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void setListener(AnyNumberButtonsPressedListener listener) {
        mAnyNumberButtonsPressedListener = listener;
    }

    protected boolean isAnyNumberButtonPressed() {
        return mAnyNumberButtonsPressedListener.isAnyNumberButtonPressed();
    }

    public void toggleButton() {
        mIsOn = !mIsOn;
        setButtonColour(getButton(), mIsOn ? PLUM : CLEAR_COLOUR);
    }

    public void turnButtonOff() {
        mIsOn = false;
        setButtonColour(getButton(), CLEAR_COLOUR);
    }

    public void callListener(@OperatorModified int operatorModification) {
        mListener.onOperatorButtonClicked(mIsOn ? operatorModification : NONE);
    }

    @Override
    public void onInputScreenUpdated(final PitchClassSet set) {
        turnButtonOff();
    }

    public void makeToast(Context context) {
        Toast.makeText(context, "Tap any number(s) to use the operators", Toast.LENGTH_SHORT).show();
    }
}

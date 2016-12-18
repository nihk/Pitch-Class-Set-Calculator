package com.nihk.github.pcsetcalculator.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.widget.Button;

import com.nihk.github.pcsetcalculator.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Nick on 2016-12-07.
 */

public final class ButtonColourUtils {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ INACTIVE, DARK_PINK, PLUM })
    public @interface ButtonColours {}
    public static final int INACTIVE = -1;
    public static final int DARK_PINK = 0;
    public static final int PLUM = 1;

    public static void setButtonColour(final Button button, @ButtonColours final int colour) {
        final Resources resources = button.getContext().getResources();
        final Drawable drawable;

        switch (colour) {
            case DARK_PINK:
                drawable = resources.getDrawable(R.drawable.number_button_active);
                break;

            case PLUM:
                drawable = resources.getDrawable(R.drawable.operator_button_active);
                break;

            case INACTIVE:  // Fall through
            default:
                drawable = resources.getDrawable(R.drawable.button_inactive);
                break;
        }

        button.setBackgroundDrawable(drawable);
    }
}

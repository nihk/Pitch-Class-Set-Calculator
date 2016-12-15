package com.nihk.github.pcsetcalculator.utils;

import android.graphics.PorterDuff;
import android.widget.Button;

/**
 * Created by Nick on 2016-12-07.
 */

public final class ButtonColourUtils {
    public static final int DARK_PINK = 0xFFD68684;
    public static final int PLUM = 0xFF735360;
    public static final int CLEAR_COLOUR = 0;

    public static void setButtonColour(final Button button, final int colour) {
        if (colour == CLEAR_COLOUR) {
            button.getBackground().clearColorFilter();
        } else {
            button.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
        }
    }
}

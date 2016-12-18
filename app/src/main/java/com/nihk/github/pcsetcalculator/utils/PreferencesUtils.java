package com.nihk.github.pcsetcalculator.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Nick on 2016-12-17.
 */

public final class PreferencesUtils {
    private static SharedPreferences sPreferences;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ KEY_T_AND_E, KEY_FORTE_ALGORITHM })
    public @interface PcSetPreferences {}
    public static final String KEY_T_AND_E = "t_e_preference";
    public static final String KEY_FORTE_ALGORITHM = "forte_algorithm_preference";

    public static final char T = 'T';
    public static final char E = 'E';
    public static final char A = 'A';
    public static final char B = 'B';

    private PreferencesUtils() {
        // Prevent instantiation
    }

    public static void initPreferences(Context context) {
        sPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isChecked(@PcSetPreferences String key) {
        return sPreferences != null && sPreferences.getBoolean(key, false /* defValue */);
    }

    public static void registerListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unregisterListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public static boolean hasPcsAorBorTorECharacter(final String text) {
        for (char pc : text.toCharArray()) {
            if (pc == A || pc == B || pc == T || pc == E) {
                return true;
            }
        }

        return false;
    }

    public static String swapAandBwithTandEOrViceVersa(String text) {
        if (isChecked(PreferencesUtils.KEY_T_AND_E)) {
            return text.replace(A, T).replace(B, E);
        } else {
            return text.replace(T, A).replace(E, B);
        }
    }
}

package com.nihk.github.pcsetcalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.TextView;

import com.nihk.github.pcsetcalculator.models.ForteNumber;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;
import com.nihk.github.pcsetcalculator.utils.ForteNumberUtils;
import com.nihk.github.pcsetcalculator.views.PCSetCalculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.nihk.github.pcsetcalculator.utils.PreferencesUtils.KEY_FORTE_ALGORITHM;
import static com.nihk.github.pcsetcalculator.utils.PreferencesUtils.KEY_T_AND_E;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorInstrumentedTests {
    @Rule
    public final ActivityTestRule<PCSetCalculator> mActivityTestRule =
            new ActivityTestRule<>(PCSetCalculator.class);

    @UiThreadTest
    @Test
    public void inputScreenShowsUndoCorrectly() throws Exception {
        final PCSetCalculator pcSetCalculator = mActivityTestRule.getActivity();

        final TextView inputScreen = (TextView) pcSetCalculator.findViewById(R.id.input_screen);
        final Button button1 = (Button) pcSetCalculator.findViewById(R.id.button1);
        final Button buttonA = (Button) pcSetCalculator.findViewById(R.id.buttonA);
        final Button buttonUndo = (Button) pcSetCalculator.findViewById(R.id.buttonUndo);
        button1.performClick();
        buttonA.performClick();
        buttonUndo.performClick();

        assertEquals(inputScreen.getText(), "{1}");
    }

    @UiThreadTest
    @Test
    public void inputScreenShowsButtonOnOffClicksCorrectly() throws Exception {
        final PCSetCalculator pcSetCalculator = mActivityTestRule.getActivity();
        final TextView inputScreen = (TextView) pcSetCalculator.findViewById(R.id.input_screen);
        final Button button1 = (Button) pcSetCalculator.findViewById(R.id.button1);
        final Button button9 = (Button) pcSetCalculator.findViewById(R.id.button9);

        button1.performClick();
        button9.performClick();
        button1.performClick();
        button1.performClick();
        button9.performClick();
        button9.performClick();

        assertEquals(inputScreen.getText(), "{1 9}");
    }

    @UiThreadTest
    @Test
    public void outputScreenIntervalVectorEmptyOnOnePc() throws Exception {
        final PCSetCalculator pcSetCalculator = mActivityTestRule.getActivity();
        final TextView intervalVectorScreen = (TextView) pcSetCalculator.findViewById(R.id.intervalVector);
        final Button button1 = (Button) pcSetCalculator.findViewById(R.id.button1);
        button1.performClick();

        assertEquals(intervalVectorScreen.getText(), "---");
    }

    @UiThreadTest
    @Test
    public void inputScreenUpdatesOnTandEPrefsChange() throws Exception {
        final PCSetCalculator pcSetCalculator = mActivityTestRule.getActivity();
        final TextView inputScreen = (TextView) pcSetCalculator.findViewById(R.id.input_screen);
        final Button buttonA = (Button) pcSetCalculator.findViewById(R.id.buttonA);
        final Button buttonB = (Button) pcSetCalculator.findViewById(R.id.buttonB);
        final Button buttonUndo = (Button) pcSetCalculator.findViewById(R.id.buttonUndo);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(pcSetCalculator);
        sharedPreferences.edit().putBoolean(KEY_T_AND_E, false).commit();

        buttonA.performClick();
        buttonB.performClick();
        sharedPreferences.edit().putBoolean(KEY_T_AND_E, true).commit();

        assertEquals(inputScreen.getText(), "{T E}");
        buttonUndo.performClick();
        assertEquals(inputScreen.getText(), "{T}");
    }

    @UiThreadTest
    @Test
    public void outputScreenNormalFormUpdatesOnAlgorithmPrefsChange() throws Exception {
        final PCSetCalculator pcSetCalculator = mActivityTestRule.getActivity();
        final TextView normalFormScreen = (TextView) pcSetCalculator.findViewById(R.id.normalForm);
        final Button button0 = (Button) pcSetCalculator.findViewById(R.id.button0);
        final Button button1 = (Button) pcSetCalculator.findViewById(R.id.button1);
        final Button button5 = (Button) pcSetCalculator.findViewById(R.id.button5);
        final Button button6 = (Button) pcSetCalculator.findViewById(R.id.button6);
        final Button button8 = (Button) pcSetCalculator.findViewById(R.id.button8);
        final Button button9 = (Button) pcSetCalculator.findViewById(R.id.button9);
        final Button buttonUndo = (Button) pcSetCalculator.findViewById(R.id.buttonUndo);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(pcSetCalculator);
        sharedPreferences.edit().putBoolean(KEY_FORTE_ALGORITHM, false).commit();

        button0.performClick();
        button1.performClick();
        button5.performClick();
        button6.performClick();
        button8.performClick();

        assertEquals(normalFormScreen.getText(), "[0 1 5 6 8]");
        sharedPreferences.edit().putBoolean(KEY_FORTE_ALGORITHM, true).commit();
        assertEquals(normalFormScreen.getText(), "[5 6 8 0 1]");
        button9.performClick();
        sharedPreferences.edit().putBoolean(KEY_FORTE_ALGORITHM, false).commit();
        buttonUndo.performClick();
        assertEquals(normalFormScreen.getText(), "[0 1 5 6 8]");
    }

    @Test
    public void forteAndRahnPrimeFormsAreDifferent() throws Exception {
        final PCSetCalculator pcSetCalculator = mActivityTestRule.getActivity();
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(pcSetCalculator);
        sharedPreferences.edit().putBoolean(KEY_FORTE_ALGORITHM, false).commit();

        final ForteNumber _5_20 = ForteNumberUtils._5_20;
        final ForteNumber _6_Z29 = ForteNumberUtils._6_Z29;
        final ForteNumber _6_31 = ForteNumberUtils._6_31;
        final ForteNumber _7_Z18 = ForteNumberUtils._7_Z18;
        final ForteNumber _7_20 = ForteNumberUtils._7_20;
        final ForteNumber _8_26 = ForteNumberUtils._8_26;

        final PitchClassSet _5_20_Rahn = PitchClassSet.fromForte(_5_20);
        final PitchClassSet _6_Z29_Rahn = PitchClassSet.fromForte(_6_Z29);
        final PitchClassSet _6_31_Rahn = PitchClassSet.fromForte(_6_31);
        final PitchClassSet _7_Z18_Rahn = PitchClassSet.fromForte(_7_Z18);
        final PitchClassSet _7_20_Rahn = PitchClassSet.fromForte(_7_20);
        final PitchClassSet _8_26_Rahn = PitchClassSet.fromForte(_8_26);

        sharedPreferences.edit().putBoolean(KEY_FORTE_ALGORITHM, true).commit();

        final PitchClassSet _5_20_Forte = PitchClassSet.fromForte(_5_20);
        final PitchClassSet _6_Z29_Forte = PitchClassSet.fromForte(_6_Z29);
        final PitchClassSet _6_31_Forte = PitchClassSet.fromForte(_6_31);
        final PitchClassSet _7_Z18_Forte = PitchClassSet.fromForte(_7_Z18);
        final PitchClassSet _7_20_Forte = PitchClassSet.fromForte(_7_20);
        final PitchClassSet _8_26_Forte = PitchClassSet.fromForte(_8_26);

        assertEquals(_5_20_Rahn.getPrimeFormCollection().toString(), "[0, 1, 5, 6, 8]");
        assertEquals(_6_Z29_Rahn.getPrimeFormCollection().toString(), "[0, 2, 3, 6, 7, 9]");
        assertEquals(_6_31_Rahn.getPrimeFormCollection().toString(), "[0, 1, 4, 5, 7, 9]");
        assertEquals(_7_Z18_Rahn.getPrimeFormCollection().toString(), "[0, 1, 4, 5, 6, 7, 9]");
        assertEquals(_7_20_Rahn.getPrimeFormCollection().toString(), "[0, 1, 2, 5, 6, 7, 9]");
        assertEquals(_8_26_Rahn.getPrimeFormCollection().toString(), "[0, 1, 3, 4, 5, 7, 8, 10]");

        assertEquals(_5_20_Forte.getPrimeFormCollection().toString(), "[0, 1, 3, 7, 8]");
        assertEquals(_6_Z29_Forte.getPrimeFormCollection().toString(), "[0, 1, 3, 6, 8, 9]");
        assertEquals(_6_31_Forte.getPrimeFormCollection().toString(), "[0, 1, 3, 5, 8, 9]");
        assertEquals(_7_Z18_Forte.getPrimeFormCollection().toString(), "[0, 1, 2, 3, 5, 8, 9]");
        assertEquals(_7_20_Forte.getPrimeFormCollection().toString(), "[0, 1, 2, 4, 7, 8, 9]");
        assertEquals(_8_26_Forte.getPrimeFormCollection().toString(), "[0, 1, 2, 4, 5, 7, 9, 10]");
    }

    @Test
    public void forteAndRahnNormalFormsAreDifferent() throws Exception {
        final PCSetCalculator pcSetCalculator = mActivityTestRule.getActivity();
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(pcSetCalculator);
        sharedPreferences.edit().putBoolean(KEY_FORTE_ALGORITHM, false).commit();

        final PitchClassSet _5_20_Rahn = PitchClassSet.fromBinary(355);
        final PitchClassSet _6_Z29_Rahn = PitchClassSet.fromBinary(717);
        final PitchClassSet _6_31_Rahn = PitchClassSet.fromBinary(691);
        final PitchClassSet _7_Z18_Rahn = PitchClassSet.fromBinary(755);
        final PitchClassSet _7_20_Rahn = PitchClassSet.fromBinary(743);
        final PitchClassSet _8_26_Rahn = PitchClassSet.fromBinary(1467);

        sharedPreferences.edit().putBoolean(KEY_FORTE_ALGORITHM, true).commit();

        final PitchClassSet _5_20_Forte = PitchClassSet.fromBinary(355);
        final PitchClassSet _6_Z29_Forte = PitchClassSet.fromBinary(717);
        final PitchClassSet _6_31_Forte = PitchClassSet.fromBinary(691);
        final PitchClassSet _7_Z18_Forte = PitchClassSet.fromBinary(755);
        final PitchClassSet _7_20_Forte = PitchClassSet.fromBinary(743);
        final PitchClassSet _8_26_Forte = PitchClassSet.fromBinary(1467);

        assertEquals(_5_20_Rahn.getNormalFormCollection().toString(), "[0, 1, 5, 6, 8]");
        assertEquals(_6_Z29_Rahn.getNormalFormCollection().toString(), "[0, 2, 3, 6, 7, 9]");
        assertEquals(_6_31_Rahn.getNormalFormCollection().toString(), "[0, 1, 4, 5, 7, 9]");
        assertEquals(_7_Z18_Rahn.getPrimeFormCollection().toString(), "[0, 1, 4, 5, 6, 7, 9]");
        assertEquals(_7_20_Rahn.getNormalFormCollection().toString(), "[0, 1, 2, 5, 6, 7, 9]");
        assertEquals(_8_26_Rahn.getNormalFormCollection().toString(), "[0, 1, 3, 4, 5, 7, 8, 10]");

        assertEquals(_5_20_Forte.getNormalFormCollection().toString(), "[5, 6, 8, 0, 1]");
        assertEquals(_6_Z29_Forte.getNormalFormCollection().toString(), "[6, 7, 9, 0, 2, 3]");
        assertEquals(_6_31_Forte.getNormalFormCollection().toString(), "[4, 5, 7, 9, 0, 1]");
        assertEquals(_7_Z18_Forte.getNormalFormCollection().toString(), "[4, 5, 6, 7, 9, 0, 1]");
        assertEquals(_7_20_Forte.getNormalFormCollection().toString(), "[5, 6, 7, 9, 0, 1, 2]");
        assertEquals(_8_26_Forte.getNormalFormCollection().toString(), "[3, 4, 5, 7, 8, 10, 0, 1]");
    }
}

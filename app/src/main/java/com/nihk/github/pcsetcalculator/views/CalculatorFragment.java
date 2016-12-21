package com.nihk.github.pcsetcalculator.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.controllers.CalculatorController;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

/**
 * Created by Nick on 2016-11-27.
 */

public class CalculatorFragment extends Fragment {
    private CalculatorController mController;
    private static final String SHOWCASE_ID = "showcaseId";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false /* attachToRoot */);
        mController = new CalculatorController(view.findViewById(R.id.calculator_table));

        doTutorial(view);

        return view;
    }

    private void doTutorial(final View view) {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setMaskColor(0xC3000000);
        config.setDismissTextColor(0xFF3AFF00);
        config.setDelay(200);
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(getActivity(), SHOWCASE_ID);
        sequence.setConfig(config);

        final String gotIt = "GOT IT";

        sequence.addSequenceItem(view.findViewById(R.id.button4),
                "Welcome! Let's take a second to get oriented:\n\n" +
                    "You can tap any combination of numbers/letters to build your pitch class set",
                gotIt);
        sequence.addSequenceItem(view.findViewById(R.id.buttonTn),
                "Use the Transposition and Inversion buttons to perform operations on that set",
                gotIt);
        sequence.addSequenceItem(view.findViewById(R.id.buttonUndo),
                "Hit Undo and/or Clear to facilitate your input actions. That's it!",
                gotIt);
        sequence.start();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(CalculatorController.KEY_CALCULATOR_CONTROLLER, mController.getCalculatorBundle());
    }

    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mController.setCalculatorFromBundle(savedInstanceState.getBundle(CalculatorController.KEY_CALCULATOR_CONTROLLER));
        }
    }

    public void injectPcSet(String forteNumber) {
        mController.injectPcSet(forteNumber);
    }
}

package com.nihk.github.pcsetcalculator.views;

import android.content.res.Resources;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false /* attachToRoot */);
        mController = new CalculatorController(view.findViewById(R.id.calculator_table));

        doTutorial(view);

        return view;
    }

    private void doTutorial(final View view) {
        final Resources resources = getResources();

        ShowcaseConfig config = new ShowcaseConfig();
        config.setMaskColor(resources.getColor(R.color.showcase_mask));
        config.setDismissTextColor(resources.getColor(R.color.showcase_got_it));
        config.setDelay((long) resources.getInteger(R.integer.showcase_delay));
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(getActivity(),
                resources.getString(R.string.showcase_id));
        sequence.setConfig(config);

        final String gotIt = resources.getString(R.string.got_it);
        sequence.addSequenceItem(view.findViewById(R.id.button4),
                resources.getString(R.string.showcase_part1),
                gotIt);
        sequence.addSequenceItem(view.findViewById(R.id.buttonTn),
                resources.getString(R.string.showcase_part2),
                gotIt);
        sequence.addSequenceItem(view.findViewById(R.id.buttonUndo),
                resources.getString(R.string.showcase_part3),
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

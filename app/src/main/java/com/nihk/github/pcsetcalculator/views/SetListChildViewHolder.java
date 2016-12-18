package com.nihk.github.pcsetcalculator.views;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.ForteNumber;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;
import com.nihk.github.pcsetcalculator.models.SetListChild;
import com.nihk.github.pcsetcalculator.utils.PreferencesUtils;
import com.nihk.github.pcsetcalculator.utils.StringFormatUtils;

import static com.nihk.github.pcsetcalculator.utils.PreferencesUtils.*;
import static com.nihk.github.pcsetcalculator.utils.RahnForteUtils.isPrimeFormDifferentDependingOnAlgorithm;

/**
 * Created by Nick on 2016-12-11.
 */

public class SetListChildViewHolder extends ChildViewHolder<SetListChild>
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    public LinearLayout mLayout;
    public TextView mPrimeForm;
    public TextView mForteNumber;
    public TextView mIntervalVector;
    public TextView mTranspostionalSymmetry;
    public TextView mInversionalSymmetry;
    private SetListItemClickedListener mSetListItemClickedListener;

    public interface SetListItemClickedListener {
        void onSetListItemClicked(String forteNumber);
    }

    public SetListChildViewHolder(@NonNull final View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.child_layout);
        mLayout.setOnClickListener(makeChildClickListener());
        mPrimeForm = (TextView) itemView.findViewById(R.id.child_prime_form);
        mForteNumber = (TextView) itemView.findViewById(R.id.child_forte_number);
        mIntervalVector = (TextView) itemView.findViewById(R.id.child_inteval_vector);
        mTranspostionalSymmetry = (TextView) itemView.findViewById(R.id.child_transpositional_symmetry);
        mInversionalSymmetry = (TextView) itemView.findViewById(R.id.child_inversional_symmetry);
    }

    public void bind(SetListChild setListChild) {
        final String primeForm = setListChild.getPrimeForm();
        final String forteNumber = setListChild.getForteNumber();
        mPrimeForm.setText(primeForm);
        mForteNumber.setText(forteNumber);
        mIntervalVector.setText(setListChild.getIntervalVector());
        mTranspostionalSymmetry.setText(setListChild.getTranspositionalSymmetry());
        mInversionalSymmetry.setText(setListChild.getInversionalSymmetry());

        final boolean hasPcsAorBorTorE = hasPcsAorBorTorE(primeForm);
        final boolean isPrimeFormDifferentDependingOnAlgorithm = isPrimeFormDifferentDependingOnAlgorithm(forteNumber);
        if (hasPcsAorBorTorE || isPrimeFormDifferentDependingOnAlgorithm) {
            PreferencesUtils.registerListener(this);
            if (hasPcsAorBorTorE) {
                maybeSwapAandBwithTandEOrViceVersa();
            }
            if (isPrimeFormDifferentDependingOnAlgorithm) {
                maybeSwapForteAndRahnPrimes();
            }
        }
    }

    private View.OnClickListener makeChildClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mSetListItemClickedListener.onSetListItemClicked(String.valueOf(mForteNumber.getText()));
            }
        };
    }

    public void setSetListItemClickedListener(SetListItemClickedListener listener) {
        mSetListItemClickedListener = listener;
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences,
                                          final String key) {
        if (key.equals(PreferencesUtils.KEY_T_AND_E)) {
            maybeSwapAandBwithTandEOrViceVersa();
        }
        if (key.equals(PreferencesUtils.KEY_FORTE_ALGORITHM)) {
            maybeSwapForteAndRahnPrimes();
        }
    }

    // Handle any changes to preferences for the algorithm used (i.e. Rahn's or Forte's; default
    // is Rahn's)
    private void maybeSwapForteAndRahnPrimes() {
        final ForteNumber forteNumber = new ForteNumber(String.valueOf(mForteNumber.getText()));
        PitchClassSet pitchClassSet = PitchClassSet.fromForte(forteNumber);
        mPrimeForm.setText(StringFormatUtils.makePrimeFormStringRepresentation(pitchClassSet, false /* withSpaces */));
    }

    // The prime form of this viewholder has PCs 10 and 11 in it, therefore these can and will
    // change on preference changes. By default A and B are used.
    private void maybeSwapAandBwithTandEOrViceVersa() {
        String primeForm = String.valueOf(mPrimeForm.getText());
        mPrimeForm.setText(PreferencesUtils.swapAandBwithTandEOrViceVersa(primeForm));
    }
}

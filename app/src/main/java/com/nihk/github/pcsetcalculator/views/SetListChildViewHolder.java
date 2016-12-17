package com.nihk.github.pcsetcalculator.views;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.SetListChild;
import com.nihk.github.pcsetcalculator.utils.PreferencesUtils;

import static com.nihk.github.pcsetcalculator.utils.PreferencesUtils.*;

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
        mPrimeForm.setText(primeForm);
        mForteNumber.setText(setListChild.getForteNumber());
        mIntervalVector.setText(setListChild.getIntervalVector());
        mTranspostionalSymmetry.setText(setListChild.getTranspositionalSymmetry());
        mInversionalSymmetry.setText(setListChild.getInversionalSymmetry());

        if (hasPcsAorBorTorE(primeForm)) {
            PreferencesUtils.registerListener(this);
            maybeSwapAandBwithTandEOrViceVersa();
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
    }

    private void maybeSwapAandBwithTandEOrViceVersa() {
        String primeForm = String.valueOf(mPrimeForm.getText());
        mPrimeForm.setText(PreferencesUtils.swapAandBwithTandEOrViceVersa(primeForm));
    }
}

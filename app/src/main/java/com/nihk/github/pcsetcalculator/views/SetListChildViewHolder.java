package com.nihk.github.pcsetcalculator.views;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.SetListChild;

/**
 * Created by Nick on 2016-12-11.
 */

public class SetListChildViewHolder extends ChildViewHolder<SetListChild> {
    public TextView mPrimeForm;
    public TextView mForteNumber;
    public TextView mIntervalVector;
    public TextView mTranspostionalInvariance;
    public TextView mInversionalInvariance;

    public SetListChildViewHolder(@NonNull final View itemView) {
        super(itemView);

        mPrimeForm = (TextView) itemView.findViewById(R.id.child_prime_form);
        mForteNumber = (TextView) itemView.findViewById(R.id.child_forte_number);
        mIntervalVector = (TextView) itemView.findViewById(R.id.child_inteval_vector);
        mTranspostionalInvariance = (TextView) itemView.findViewById(R.id.child_transpositional_invariance);
        mInversionalInvariance = (TextView) itemView.findViewById(R.id.child_inversional_invariance);
    }

    public void bind(SetListChild setListChild) {
        mPrimeForm.setText(setListChild.getPrimeForm());
        mForteNumber.setText(setListChild.getForteNumber());
        mIntervalVector.setText(setListChild.getIntervalVector());
        mTranspostionalInvariance.setText(setListChild.getTranspositionalInvariance());
        mInversionalInvariance.setText(setListChild.getInversionalInvariance());
    }
}

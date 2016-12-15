package com.nihk.github.pcsetcalculator.views;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.SetListChild;

/**
 * Created by Nick on 2016-12-11.
 */

public class SetListChildViewHolder extends ChildViewHolder<SetListChild> {
    public LinearLayout mLayout;
    public TextView mPrimeForm;
    public TextView mForteNumber;
    public TextView mIntervalVector;
    public TextView mTranspostionalSymmetry;
    public TextView mInversionalSymmetry;
    private Listener mListener;

    public interface Listener {
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
        mPrimeForm.setText(setListChild.getPrimeForm());
        mForteNumber.setText(setListChild.getForteNumber());
        mIntervalVector.setText(setListChild.getIntervalVector());
        mTranspostionalSymmetry.setText(setListChild.getTranspositionalSymmetry());
        mInversionalSymmetry.setText(setListChild.getInversionalSymmetry());
    }

    private View.OnClickListener makeChildClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mListener.onSetListItemClicked(String.valueOf(mForteNumber.getText()));
            }
        };
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }
}

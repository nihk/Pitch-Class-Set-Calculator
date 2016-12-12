package com.nihk.github.pcsetcalculator.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.SetListParent;

/**
 * Created by Nick on 2016-12-11.
 */

public class SetListParentViewHolder extends ParentViewHolder {
    public LinearLayout mSetListParentLayout;
    public TextView mCardinality;
    public ImageView mDropDownArrow;

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    private static final float PIVOT_VALUE = 0.5f;
    private static final long DEFAULT_ROTATE_DURATION_MS = 200;
    private static final boolean HONEYCOMB_AND_ABOVE = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    private static final String TAG = "SetListParentViewHolder";

    public SetListParentViewHolder(final View itemView) {
        super(itemView);

        mSetListParentLayout = (LinearLayout) itemView.findViewById(R.id.setListParentLayout);
        mCardinality = (TextView) itemView.findViewById(R.id.parent_cardinality);
        mDropDownArrow = (ImageView) itemView.findViewById(R.id.parent_arrow_dropdown);

        mSetListParentLayout.setOnClickListener(makeParentOnClickListener());
    }

    private View.OnClickListener makeParentOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (isExpanded()) {
                    collapseView();
                } else {
                    expandView();
                }
            }
        };
    }

    public void bind(SetListParent setListParent) {
        mCardinality.setText(setListParent.getCardinality());
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (!HONEYCOMB_AND_ABOVE) {
            return;
        }

        if (expanded) {
            mDropDownArrow.setRotation(ROTATED_POSITION);
        } else {
            mDropDownArrow.setRotation(INITIAL_POSITION);
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (!HONEYCOMB_AND_ABOVE) {
            return;
        }

        RotateAnimation rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                INITIAL_POSITION,
                RotateAnimation.RELATIVE_TO_SELF, PIVOT_VALUE,
                RotateAnimation.RELATIVE_TO_SELF, PIVOT_VALUE);
        rotateAnimation.setDuration(DEFAULT_ROTATE_DURATION_MS);
        rotateAnimation.setFillAfter(true);
        mDropDownArrow.startAnimation(rotateAnimation);
    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;
    }
}

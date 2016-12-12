package com.nihk.github.pcsetcalculator.models;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by Nick on 2016-12-11.
 */

public class SetListParent implements Parent<SetListChild> {
    private List<SetListChild> mChildItemList;
    private final String mCardinality;
    private boolean mIsInitiallyExpanded;

    public String getCardinality() {
        return mCardinality;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return mIsInitiallyExpanded;
    }

    public void setInitiallyExpanded(final boolean initiallyExpanded) {
        mIsInitiallyExpanded = initiallyExpanded;
    }

    @Override
    public List<SetListChild> getChildList() {
        return mChildItemList;
    }

    public void setChildItemList(List<SetListChild> childItemList) {
        mChildItemList = childItemList;
    }

    public SetListParent(final String cardinality) {
        mCardinality = cardinality;
    }
}

package com.nihk.github.pcsetcalculator.model;

/**
 * Created by Nick on 2016-11-02.
 */

/**
 * A class used for instances of Forte's number system representing set classes, e.g. 3-1.
 * The cardinality is the size of the pitch class collection, but the ordinal position
 * was arbitrarily assigned by Allen Forte.
 */
public class ForteNumber {
    private int mCardinality;
    private int mOrdinalPosition;
    // TODO explain what Z relation is
    private boolean mIsZedRelation;

    private static final char ZED = 'Z';

    public int getCardinality() {
        return mCardinality;
    }

    public int getOrdinalPosition() {
        return mOrdinalPosition;
    }

    public boolean isZedRelation() {
        return mIsZedRelation;
    }

    public ForteNumber(String forteNumber) {
        String[] split = forteNumber.split("-");
        mCardinality = Integer.parseInt(split[0]);
        String postDashPart = split[1];
        mIsZedRelation = Character.toUpperCase(postDashPart.charAt(0)) == ZED;
        mOrdinalPosition = Integer.parseInt(mIsZedRelation
                ? postDashPart.substring(1, postDashPart.length())
                : postDashPart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForteNumber that = (ForteNumber) o;

        if (mCardinality != that.mCardinality) return false;
        if (mOrdinalPosition != that.mOrdinalPosition) return false;
        return mIsZedRelation == that.mIsZedRelation;

    }

    @Override
    public int hashCode() {
        int result = mCardinality;
        result = 31 * result + mOrdinalPosition;
        result = 31 * result + (mIsZedRelation ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%d-%s%d",
                mCardinality,
                mIsZedRelation ? "Z" : "",
                mOrdinalPosition);
    }
}

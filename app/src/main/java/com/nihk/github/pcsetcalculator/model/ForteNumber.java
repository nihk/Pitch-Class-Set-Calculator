package com.nihk.github.pcsetcalculator.model;

/**
 * Created by Nick on 2016-11-02.
 */

import java.util.Locale;

/**
 * A class used for instances of Forte's number system representing set classes, e.g. 3-1.
 * The cardinality is the size of the pitch class collection, but the ordinal position
 * was arbitrarily assigned by Allen Forte.
 */
public final class ForteNumber {
    private final int mCardinality;
    private final int mOrdinalPosition;
    // The "Z-relation" is when two pitch class sets have the same interval vector.
    // E.g. 4-Z29 and 4-Z15 both have the i.v. <111111>
    private final boolean mIsZedRelated;

    private static final char ZED = 'Z';

    public int getCardinality() {
        return mCardinality;
    }

    public int getOrdinalPosition() {
        return mOrdinalPosition;
    }

    public boolean isZedRelated() {
        return mIsZedRelated;
    }

    public ForteNumber(String forteNumber) {
        String[] split = forteNumber.split("-");
        mCardinality = Integer.parseInt(split[0]);
        String postDashPart = split[1];
        mIsZedRelated = Character.toUpperCase(postDashPart.charAt(0)) == ZED;
        mOrdinalPosition = Integer.parseInt(mIsZedRelated
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
        return mIsZedRelated == that.mIsZedRelated;

    }

    @Override
    public int hashCode() {
        int result = mCardinality;
        result = 31 * result + mOrdinalPosition;
        result = 31 * result + (mIsZedRelated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "%d-%s%d",
                mCardinality,
                mIsZedRelated ? "Z" : "",
                mOrdinalPosition);
    }
}

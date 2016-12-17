package com.nihk.github.pcsetcalculator.models;

/**
 * Created by Nick on 2016-11-02.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * A class used for instances of Forte's number system representing set classes, e.g. 3-1.
 * The cardinality is the size of the pitch class collection, but the ordinal position
 * was arbitrarily assigned by Allen Forte.
 */
public final class ForteNumber implements Comparable<ForteNumber>, Parcelable {
    private final int mCardinality;
    private final int mOrdinalPosition;
    // The "Z-relation" is when two pitch class sets have the same interval vector.
    // E.g. 4-Z29 and 4-Z15 both have the i.v. <111111>
    private final boolean mIsZedRelated;

    private static final String DASH = "-";
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
        String[] split = forteNumber.split(DASH);
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
                "%d%s%s%d",
                mCardinality,
                DASH,
                mIsZedRelated ? "Z" : "",
                mOrdinalPosition);
    }

    @Override
    public int compareTo(@NonNull ForteNumber o) {
        if (this == o) {
            return 0;
        }

        int cmp = Integer.valueOf(mCardinality).compareTo(o.mCardinality);

        if (cmp == 0) {
            cmp = Integer.valueOf(this.mOrdinalPosition).compareTo(o.mOrdinalPosition);
        }

        return cmp;
    }

    private ForteNumber(Parcel in) {
        mCardinality = in.readInt();
        mOrdinalPosition = in.readInt();
        mIsZedRelated = in.readByte() != 0;
    }

    public static final Creator<ForteNumber> CREATOR = new Creator<ForteNumber>() {
        @Override
        public ForteNumber createFromParcel(Parcel in) {
            return new ForteNumber(in);
        }

        @Override
        public ForteNumber[] newArray(int size) {
            return new ForteNumber[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(mCardinality);
        dest.writeInt(mOrdinalPosition);
        dest.writeByte((byte) (mIsZedRelated ? 1 : 0));
    }
}

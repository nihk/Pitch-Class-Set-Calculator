package com.nihk.github.pcsetcalculator.models;

/**
 * Created by Nick on 2016-11-02.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A class that holds the zero-based normal form and
 * the Tn value needed to bring that to its actual normal form.
 */
public final class NormalFormMetadata implements Parcelable {
    private final int mZeroBasedNormalForm;
    private final int mTransposition;

    public int getZeroBasedNormalForm() {
        return mZeroBasedNormalForm;
    }

    public int getTransposition() {
        return mTransposition;
    }

    public NormalFormMetadata(int zeroBasedNormalForm, int transposition) {
        this.mZeroBasedNormalForm = zeroBasedNormalForm;
        this.mTransposition = transposition;
    }

    protected NormalFormMetadata(Parcel in) {
        mZeroBasedNormalForm = in.readInt();
        mTransposition = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mZeroBasedNormalForm);
        dest.writeInt(mTransposition);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NormalFormMetadata> CREATOR = new Creator<NormalFormMetadata>() {
        @Override
        public NormalFormMetadata createFromParcel(Parcel in) {
            return new NormalFormMetadata(in);
        }

        @Override
        public NormalFormMetadata[] newArray(int size) {
            return new NormalFormMetadata[size];
        }
    };
}

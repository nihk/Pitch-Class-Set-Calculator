package com.nihk.github.pcsetcalculator.models;

/**
 * Created by Nick on 2016-11-02.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.nihk.github.pcsetcalculator.utils.ForteNumberUtils;
import com.nihk.github.pcsetcalculator.utils.IntervalVectorUtils;
import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;

import java.util.List;

import static com.nihk.github.pcsetcalculator.utils.SetTheoryUtils.*;

/**
 * A distinct collection of pitch classes
 */
public class PitchClassSet implements Parcelable {
    // TODO change Lists to int[]s?
    private List<Integer> mCollection;
    private List<Integer> mNormalFormCollection;
    private List<Integer> mPrimeFormCollection;
    private List<Integer> mIntervalVector;
    private ForteNumber mForteNumber;
    private ForteNumber mZMate;
    private int mOriginalSetBinary;
    private NormalFormMetadata mNormalFormMetadata;
    private int mPrimeFormBinary;

    public List<Integer> getCollection() {
        return mCollection;
    }

    public List<Integer> getPrimeFormCollection() {
        return mPrimeFormCollection;
    }

    public List<Integer> getNormalFormCollection() {
        return mNormalFormCollection;
    }

    public ForteNumber getForteNumber() {
        return mForteNumber;
    }

    public int getOriginalSetBinary() {
        return mOriginalSetBinary;
    }

    public NormalFormMetadata getNormalFormMetadata() {
        return mNormalFormMetadata;
    }

    public int getPrimeFormBinary() {
        return mPrimeFormBinary;
    }

    public List<Integer> getIntervalVector() {
        return mIntervalVector;
    }

    public boolean isEmpty() {
        return mCollection == null || mCollection.size() == 0;
    }

    private PitchClassSet() {
        // Instantiation is done by static constructors
    }

    public static PitchClassSet fromString(String s) {
        int set = stringToSet(s);
        return fromBinary(set);
    }

    public static PitchClassSet fromBinary(int set) {
        PitchClassSet pcs = new PitchClassSet();
        pcs.mOriginalSetBinary = set;
        pcs.mNormalFormMetadata = calculateNormalForm(pcs.mOriginalSetBinary);
        pcs.mPrimeFormBinary = calculatePrimeForm(pcs.mOriginalSetBinary);

        pcs.mCollection = setToList(pcs.mOriginalSetBinary);
        pcs.mPrimeFormCollection = setToList(pcs.mPrimeFormBinary);
        pcs.mNormalFormCollection = setToList(pcs.mNormalFormMetadata.getZeroBasedNormalForm());
        SetTheoryUtils.transpose(pcs.mNormalFormCollection, pcs.mNormalFormMetadata.getTransposition());
        pcs.mForteNumber = ForteNumberUtils.BIMAP.get(pcs.mPrimeFormBinary);
        pcs.mZMate = IntervalVectorUtils.Z_MATES.get(pcs.mForteNumber);
        pcs.mIntervalVector = calculateIntervalVector(pcs.mPrimeFormBinary);

        return pcs;
    }

    public static PitchClassSet fromForte(ForteNumber fn) {
        int set = ForteNumberUtils.BIMAP.inverse().get(fn);
        return fromBinary(set);
    }

    @Override
    public String toString() {
        return "Original collection: " + mCollection.toString()
                + "\nNormal form: " + mNormalFormCollection.toString()
                + "\nPrime form: " + mPrimeFormCollection.toString()
                + "\nForte number: " + mForteNumber.toString();
    }

    public static PitchClassSet emptySet() {
        return fromBinary(0);
    }

    protected PitchClassSet(Parcel in) {
        mForteNumber = in.readParcelable(ForteNumber.class.getClassLoader());
        mZMate = in.readParcelable(ForteNumber.class.getClassLoader());
        mOriginalSetBinary = in.readInt();
        mNormalFormMetadata = in.readParcelable(NormalFormMetadata.class.getClassLoader());
        mPrimeFormBinary = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mForteNumber, flags);
        dest.writeParcelable(mZMate, flags);
        dest.writeInt(mOriginalSetBinary);
        dest.writeParcelable(mNormalFormMetadata, flags);
        dest.writeInt(mPrimeFormBinary);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PitchClassSet> CREATOR = new Creator<PitchClassSet>() {
        @Override
        public PitchClassSet createFromParcel(Parcel in) {
            return new PitchClassSet(in);
        }

        @Override
        public PitchClassSet[] newArray(int size) {
            return new PitchClassSet[size];
        }
    };
}

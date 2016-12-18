package com.nihk.github.pcsetcalculator.models;

/**
 * Created by Nick on 2016-11-02.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.nihk.github.pcsetcalculator.utils.ForteNumberUtils;
import com.nihk.github.pcsetcalculator.utils.IntervalVectorUtils;
import com.nihk.github.pcsetcalculator.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import static com.nihk.github.pcsetcalculator.utils.RahnForteUtils.*;
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
    private int mTranspositionalSymmetry;
    private int mInversionalSymmetry;

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

    public int getTranspositionalSymmetry() {
        return mTranspositionalSymmetry;
    }

    public int getInversionalSymmetry() {
        return mInversionalSymmetry;
    }

    public boolean isEmpty() {
        return mOriginalSetBinary == 0 || mCollection == null || mNormalFormCollection == null
                || mPrimeFormCollection == null || mIntervalVector == null
                || mCollection.size() == 0 || mNormalFormCollection.size() == 0
                || mPrimeFormCollection.size() == 0;
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
        // Temp variable til its decided whether the Forte or Rahn algorithm is currently being used
        final int tempPrimeForm = calculatePrimeForm(pcs.mOriginalSetBinary);

        if (isForteRahnUnequalPrime(tempPrimeForm) && isForteAlgorithmEnabled()) {
            pcs.mPrimeFormBinary = RAHN_TO_FORTE_PRIMES_BINARY.get(tempPrimeForm);
            pcs.mNormalFormMetadata = calculateNormalFormFromFortePrime(pcs.mOriginalSetBinary, pcs.mPrimeFormBinary);
            // Get the Forte number associated with the Rahn prime. Since the BIMAP must have unique keys and values
            // I can't put in the Forte prime with the same ForteNumber value as a Rahn prime.
            // e.g. (binary) prime forms 355 and 395 are both set class 5-20 for their respective algorithm.
            pcs.mForteNumber = ForteNumberUtils.BIMAP.get(tempPrimeForm);
        } else {
            pcs.mPrimeFormBinary = calculatePrimeForm(pcs.mOriginalSetBinary);
            pcs.mForteNumber = ForteNumberUtils.BIMAP.get(pcs.mPrimeFormBinary);
        }

        pcs.mCollection = setToList(pcs.mOriginalSetBinary);
        pcs.mPrimeFormCollection = setToList(pcs.mPrimeFormBinary);
        pcs.mNormalFormCollection = setToList(pcs.mNormalFormMetadata.getZeroBasedNormalForm());
        transpose(pcs.mNormalFormCollection, pcs.mNormalFormMetadata.getTransposition());
        pcs.mZMate = IntervalVectorUtils.Z_MATES.get(pcs.mForteNumber);
        pcs.mIntervalVector = calculateIntervalVector(pcs.mPrimeFormBinary);
        pcs.mTranspositionalSymmetry = transpositionalSymmetry(pcs.mOriginalSetBinary);
        pcs.mInversionalSymmetry = inversionalSymmetry(pcs.mOriginalSetBinary);

        return pcs;
    }

    public static PitchClassSet fromForte(ForteNumber fn) {
        int set = ForteNumberUtils.BIMAP.inverse().get(fn);

        // All ForteNumbers are taken from the static ForteNumberUtils collection, which means the
        // prime form set corresponding to each ForteNumber used the Rahn algorithm. If the user
        // wants to use the Forte algorithm, trade that with the Forte algorithm prime
        if (isForteRahnUnequalPrime(set) && isForteAlgorithmEnabled()) {
            set = RAHN_TO_FORTE_PRIMES_BINARY.get(set);
        }
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

    private PitchClassSet(Parcel in) {
        mForteNumber = in.readParcelable(ForteNumber.class.getClassLoader());
        mZMate = in.readParcelable(ForteNumber.class.getClassLoader());
        mOriginalSetBinary = in.readInt();
        mNormalFormMetadata = in.readParcelable(NormalFormMetadata.class.getClassLoader());
        mPrimeFormBinary = in.readInt();

        mCollection = new ArrayList<>();
        mIntervalVector = new ArrayList<>();
        mNormalFormCollection = new ArrayList<>();
        mPrimeFormCollection = new ArrayList<>();
        in.readList(mCollection, Integer.class.getClassLoader());
        in.readList(mIntervalVector, Integer.class.getClassLoader());
        in.readList(mNormalFormCollection, Integer.class.getClassLoader());
        in.readList(mPrimeFormCollection, Integer.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mForteNumber, flags);
        dest.writeParcelable(mZMate, flags);
        dest.writeInt(mOriginalSetBinary);
        dest.writeParcelable(mNormalFormMetadata, flags);
        dest.writeInt(mPrimeFormBinary);

        dest.writeList(mCollection);
        dest.writeList(mIntervalVector);
        dest.writeList(mNormalFormCollection);
        dest.writeList(mPrimeFormCollection);
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

    private static boolean isForteAlgorithmEnabled() {
        return PreferencesUtils.isChecked(PreferencesUtils.KEY_FORTE_ALGORITHM);
    }

    // At least two PCs are necessary for a set to have an interval (other than 0)
    public boolean hasAnyIntervals() {
        if (mIntervalVector == null) {
            return false;
        }

        for (int i : mIntervalVector) {
            if (i > 0) {
                return true;
            }
        }

        return false;
    }
}

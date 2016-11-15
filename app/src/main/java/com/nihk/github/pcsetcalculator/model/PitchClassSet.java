package com.nihk.github.pcsetcalculator.model;

/**
 * Created by Nick on 2016-11-02.
 */

import com.nihk.github.pcsetcalculator.utils.ForteNumberUtils;
import com.nihk.github.pcsetcalculator.utils.IntervalVectorUtils;
import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;

import java.util.List;

import static com.nihk.github.pcsetcalculator.utils.SetTheoryUtils.*;

/**
 * A distinct collection of pitch classes
 */
public class PitchClassSet {
    // TODO change Lists to int[]s?
    private List<Integer> mCollection;
    private List<Integer> mNormalFormCollection;
    private List<Integer> mPrimeFormCollection;
    private List<Integer> mIntervalVector;
    private ForteNumber mForteNumber;
    private ForteNumber mZMate;
    private int mSetBinary;
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

    public int getSetBinary() {
        return mSetBinary;
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

    private PitchClassSet() {
        // Instantiation is done by static constructors
    }

    public static PitchClassSet fromString(String s) {
        int set = stringToSet(s);
        return fromBinary(set);
    }

    public static PitchClassSet fromBinary(int set) {
        PitchClassSet pcs = new PitchClassSet();
        pcs.mSetBinary = set;
        pcs.mNormalFormMetadata = calculateNormalForm(pcs.mSetBinary);
        pcs.mPrimeFormBinary = calculatePrimeForm(pcs.mSetBinary);

        pcs.mCollection = setToList(pcs.mSetBinary);
        pcs.mPrimeFormCollection = setToList(pcs.mPrimeFormBinary);
        pcs.mNormalFormCollection = setToList(pcs.mNormalFormMetadata.getZeroBasedNormalForm());
        SetTheoryUtils.transpose(pcs.mNormalFormCollection, pcs.mNormalFormMetadata.getTransposition());
        pcs.mForteNumber = ForteNumberUtils.BIMAP.get(pcs.mSetBinary);
        pcs.mZMate = IntervalVectorUtils.Z_MATES.get(pcs.mForteNumber);
        pcs.mIntervalVector = calculateIntervalVector(pcs.mSetBinary);

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
}

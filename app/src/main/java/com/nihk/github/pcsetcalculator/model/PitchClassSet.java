package com.nihk.github.pcsetcalculator.model;

/**
 * Created by Nick on 2016-11-02.
 */

import com.nihk.github.pcsetcalculator.utils.ForteNumberUtils;
import com.nihk.github.pcsetcalculator.utils.PitchClassUtils;

import java.util.List;

import static com.nihk.github.pcsetcalculator.utils.PitchClassUtils.*;

/**
 * A distinct mCollectionBinary pitch classes
 */
public class PitchClassSet {
    // TODO these lists are storing ints like 10 and 11 as those numbers, not A or B
    // TODO should I also store these as strings? Tn and In operations will be necessary
    private List<Integer> mSet;
    private List<Integer> mNormalForm;
    private List<Integer> mPrimeForm;
    private ForteNumber mForteNumber;

    // TODO fields for inversion and transpositions? (so that the original is preserved)

    private int mSetBinaryRepresentation;
    private NormalFormMetadata mNormalFormMetadata;
    private int mPrimeFormBinaryRepresentation;

    public List<Integer> getSet() {
        return mSet;
    }

    public List<Integer> getPrimeForm() {
        return mPrimeForm;
    }

    public List<Integer> getNormalForm() {
        return mNormalForm;
    }

    public ForteNumber getForteNumber() {
        return mForteNumber;
    }

    public int getSetBinaryRepresentation() {
        return mSetBinaryRepresentation;
    }

    public NormalFormMetadata getNormalFormMetadata() {
        return mNormalFormMetadata;
    }

    public int getPrimeFormBinaryRepresentation() {
        return mPrimeFormBinaryRepresentation;
    }

    private PitchClassSet() {
        // Instantiation is done by static constructors
    }

    public static PitchClassSet newInstance(String s) {
        int set = stringToSet(s);
        return newInstance(set);
    }

    public static PitchClassSet newInstance(int set) {
        PitchClassSet pcs = new PitchClassSet();
        pcs.mSetBinaryRepresentation = set;
        pcs.mNormalFormMetadata = calculateNormalForm(pcs.mSetBinaryRepresentation);
        pcs.mPrimeFormBinaryRepresentation = calculatePrimeForm(pcs.mSetBinaryRepresentation);

        pcs.mSet = setToList(pcs.mSetBinaryRepresentation);
        pcs.mPrimeForm = setToList(pcs.mPrimeFormBinaryRepresentation);
        pcs.mNormalForm = setToList(pcs.mNormalFormMetadata.getZeroBasedNormalForm());
        PitchClassUtils.transpose(pcs.mNormalForm, pcs.mNormalFormMetadata.getTransposition());
        pcs.mForteNumber = ForteNumberUtils.BIMAP.get(pcs.mSetBinaryRepresentation);

        return pcs;
    }

    // TODO make toStrings for normal/prime/IC vector have the correct respective brackets
    @Override
    public String toString() {
        return "Original collection: " + mSet.toString()
                + "\nNormal form: " + mNormalForm.toString()
                + "\nPrime form: " + mPrimeForm.toString()
                + "\nForte number: " + mForteNumber.toString();
    }
}

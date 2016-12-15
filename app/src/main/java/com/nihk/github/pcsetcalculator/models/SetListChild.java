package com.nihk.github.pcsetcalculator.models;

/**
 * Created by Nick on 2016-12-11.
 */

public class SetListChild {
    private String mPrimeForm;
    private String mForteNumber;
    private String mIntervalVector;
    private String mTranspositionalSymmetry;
    private String mInversionalSymmetry;

    public String getPrimeForm() {
        return mPrimeForm;
    }

    public String getForteNumber() {
        return mForteNumber;
    }

    public String getIntervalVector() {
        return mIntervalVector;
    }

    public String getTranspositionalSymmetry() {
        return mTranspositionalSymmetry;
    }

    public String getInversionalSymmetry() {
        return mInversionalSymmetry;
    }

    public SetListChild(final String primeForm,
                        final String forteNumber,
                        final String intervalVector,
                        final String transpositionalSymmetry,
                        final String inversionalSymmetry) {
        mPrimeForm = primeForm;
        mForteNumber = forteNumber;
        mIntervalVector = intervalVector;
        mTranspositionalSymmetry = transpositionalSymmetry;
        mInversionalSymmetry = inversionalSymmetry;
    }
}

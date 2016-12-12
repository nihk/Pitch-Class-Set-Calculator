package com.nihk.github.pcsetcalculator.models;

/**
 * Created by Nick on 2016-12-11.
 */

public class SetListChild {
    private String mPrimeForm;
    private String mForteNumber;
    private String mIntervalVector;
    private String mTranspositionalInvariance;
    private String mInversionalInvariance;

    public String getPrimeForm() {
        return mPrimeForm;
    }

    public String getForteNumber() {
        return mForteNumber;
    }

    public String getIntervalVector() {
        return mIntervalVector;
    }

    public String getTranspositionalInvariance() {
        return mTranspositionalInvariance;
    }

    public String getInversionalInvariance() {
        return mInversionalInvariance;
    }

    // TODO change the invariances to ints?
    public SetListChild(final String primeForm,
                        final String forteNumber,
                        final String intervalVector,
                        final String transpositionalInvariance,
                        final String inversionalInvariance) {
        mPrimeForm = primeForm;
        mForteNumber = forteNumber;
        mIntervalVector = intervalVector;
        mTranspositionalInvariance = transpositionalInvariance;
        mInversionalInvariance = inversionalInvariance;
    }
}

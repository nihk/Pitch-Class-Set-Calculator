package com.nihk.github.pcsetcalculator.model;

/**
 * Created by Nick on 2016-11-02.
 */

/**
 * A class that holds the zero-based normal form and
 * the Tn value needed to bring that to its actual normal form.
 */
public final class NormalFormMetadata {
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
}

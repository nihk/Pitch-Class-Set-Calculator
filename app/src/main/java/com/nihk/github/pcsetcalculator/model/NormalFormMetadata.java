package com.nihk.github.pcsetcalculator.model;

/**
 * Created by Nick on 2016-11-02.
 */

import java.util.List;

/**
 * A class that holds the zero-based normal form and
 * the Tn value needed to bring that to its actual normal form.
 */
public class NormalFormMetadata {
    private int mZeroBasedNormalForm;
    private int mTransposition;
    // TODO
    private List<Integer> mNormalForm;

    public int getZeroBasedNormalForm() {
        return mZeroBasedNormalForm;
    }

    public int getTransposition() {
        return mTransposition;
    }

    // TODO: make the constructor build the normal form list of ints
    public NormalFormMetadata(int zeroBasedNormalForm, int transposition) {
        this.mZeroBasedNormalForm = zeroBasedNormalForm;
        this.mTransposition = transposition;
    }
}

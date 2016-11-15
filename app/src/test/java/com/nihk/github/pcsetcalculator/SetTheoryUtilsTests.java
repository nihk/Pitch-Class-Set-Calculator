package com.nihk.github.pcsetcalculator;

import com.nihk.github.pcsetcalculator.model.PitchClassSet;
import com.nihk.github.pcsetcalculator.utils.ForteNumberUtils;
import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;
import com.nihk.github.pcsetcalculator.model.ForteNumber;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nick on 2016-11-05.
 */
public class SetTheoryUtilsTests {
    /**
     * This tests all possible combinations of pitch class sets within the cardinalities
     * that have a Forte number attached to it. It checks that each set has a corresponding
     * Forte number (i.e. that isn't null)
     *
     * @throws Exception
     */
    @Test
    public void setPrimeFormsHaveExistingForteNumber() throws Exception {
        int numPitchClassSets = (int)Math.pow(2, SetTheoryUtils.NUM_PITCH_CLASSES);
        for (int i = 0; i < numPitchClassSets; i++) {
            int bitCount = Integer.bitCount(i);
            if (bitCount < ForteNumberUtils.MIN_FORTE_CARDINALITY
                    || bitCount >= ForteNumberUtils.MAX_FORTE_CARDINALITY) {
                continue;
            }

            String setString = SetTheoryUtils.setToString(i);
            PitchClassSet set = PitchClassSet.fromString(setString);
            Integer key = set.getPrimeFormBinary();

            ForteNumber forteNumber =
                    ForteNumberUtils.BIMAP.get(key);
            assertNotNull("Didn't find prime form: " + key
                    + "\nPitchClassSet in binary: " + Integer.toBinaryString(key), forteNumber);
        }
    }

    // TODO make a few test cases for normal form, transpositions, inversions, etc.

    /**
     * A test to check that the interval vector is correctly assigned during the construction
     * of a PitchClassSet from either a binary integer or a Forte number
     *
     * @throws Exception
     */
    @Test
    public void intervalVectorIsCorrectlyCalculated() throws Exception {
        PitchClassSet pcs1 = PitchClassSet.fromBinary(275);
        PitchClassSet pcs2 = PitchClassSet.fromForte(ForteNumberUtils._5_35);

        assertEquals("From binary", pcs1.getIntervalVector().toString(), "[1, 0, 1, 3, 1, 0]");
        assertEquals("From Forte", pcs2.getIntervalVector().toString(), "[0, 3, 2, 1, 4, 0]");
    }
}

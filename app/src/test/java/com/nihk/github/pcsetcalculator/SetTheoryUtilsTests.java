package com.nihk.github.pcsetcalculator;

import com.nihk.github.pcsetcalculator.models.PitchClassSet;
import com.nihk.github.pcsetcalculator.utils.ForteNumberUtils;
import com.nihk.github.pcsetcalculator.utils.SetTheoryUtils;
import com.nihk.github.pcsetcalculator.models.ForteNumber;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.nihk.github.pcsetcalculator.utils.SetTheoryUtils.*;
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
    public void primeFormsAnHaveExistingForteNumber() throws Exception {
        int numPitchClassSets = (int)Math.pow(2, NUM_PITCH_CLASSES);
        for (int i = 0; i < numPitchClassSets; i++) {
            int bitCount = Integer.bitCount(i);
            if (bitCount < ForteNumberUtils.MIN_FORTE_CARDINALITY
                    || bitCount >= ForteNumberUtils.MAX_FORTE_CARDINALITY) {
                continue;
            }

            String setString = setToString(i);
            PitchClassSet set = PitchClassSet.fromString(setString);
            Integer key = set.getPrimeFormBinary();

            ForteNumber forteNumber =
                    ForteNumberUtils.BIMAP.get(key);
            assertNotNull("Didn't find prime form: " + key
                    + "\nPitchClassSet in binary: " + Integer.toBinaryString(key), forteNumber);
        }
    }

    @Test
    public void normalFormIsCorrectlyCalculated() throws Exception {
        PitchClassSet pcs1 = PitchClassSet.fromString("0468");
        PitchClassSet pcs2 = PitchClassSet.fromString("7A3B0");
        PitchClassSet pcs3 = PitchClassSet.fromString("0136789");
        PitchClassSet pcs4 = PitchClassSet.fromString("024678");

        assertEquals(pcs1.getNormalFormCollection().toString(), "[4, 6, 8, 0]");
        assertEquals(pcs2.getNormalFormCollection().toString(), "[7, 10, 11, 0, 3]");
        assertEquals(pcs3.getNormalFormCollection().toString(), "[6, 7, 8, 9, 0, 1, 3]");
        assertEquals(pcs4.getNormalFormCollection().toString(), "[0, 2, 4, 6, 7, 8]");
    }

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
        PitchClassSet pcs3 = PitchClassSet.fromString("645");

        assertEquals("From binary", pcs1.getIntervalVector().toString(), "[1, 0, 1, 3, 1, 0]");
        assertEquals("From Forte", pcs2.getIntervalVector().toString(), "[0, 3, 2, 1, 4, 0]");
        assertEquals("From String", pcs3.getIntervalVector().toString(), "[2, 1, 0, 0, 0, 0]");
    }

    /**
     * This tests a few cases of inclusion relationships between sets, i.e. super/subset
     * relationships
     *
     * @throws Exception
     */
    // TODO write more cases
    @Test
    public void superAndSubsetsAreValid() throws Exception {
        PitchClassSet pcs1 = PitchClassSet.fromString("9AB0");
        PitchClassSet pcs2 = PitchClassSet.fromString("9A1B0");
        PitchClassSet pcs3 = PitchClassSet.fromString("2468A");
        PitchClassSet pcs4 = PitchClassSet.fromString("123");

        assertTrue("[0, 1, 9, 10, 11] should've been an abstract superset of [0, 9, 10, 11]\n",
                isAbstractSuperset(pcs1.getOriginalSetBinary(), pcs2.getOriginalSetBinary()));
        assertFalse("[2, 4, 6, 8, 10] shouldn't've been an abstract superset of [0, 9, 10, 11]",
                isAbstractSuperset(pcs1.getOriginalSetBinary(), pcs3.getOriginalSetBinary()));
        assertFalse("[1, 2, 3] shouldn't've been a literal subset of [0, 9, 10, 11]",
                isLiteralSubset(pcs1.getOriginalSetBinary(), pcs4.getOriginalSetBinary()));
    }

    @Test
    public void transpositionTests() throws Exception {
        PitchClassSet pcs1 = PitchClassSet.fromString("9AB0");
        PitchClassSet pcs2 = PitchClassSet.fromString("9A1B0");
        PitchClassSet pcs3 = PitchClassSet.fromString("2468A");
        PitchClassSet pcs4 = PitchClassSet.fromString("123");
        PitchClassSet pcs5 = PitchClassSet.fromString("0");
        PitchClassSet pcs6 = PitchClassSet.fromString("6");

        int pcs1Binary = transpose(pcs1.getOriginalSetBinary(), 3);
        int pcs2Binary = transpose(pcs2.getOriginalSetBinary(), 2);
        int pcs3Binary = transpose(pcs3.getOriginalSetBinary(), -3);
        int pcs4Binary = transpose(pcs4.getOriginalSetBinary(), 0);
        int pcs5Binary = transpose(pcs5.getOriginalSetBinary(), 12);
        int pcs6Binary = transpose(pcs6.getOriginalSetBinary(), 6);
        transposeThenSort(pcs1.getCollection(), 3);
        transposeThenSort(pcs2.getCollection(), 2);
        transposeThenSort(pcs3.getCollection(), -3);
        transposeThenSort(pcs4.getCollection(), 0);
        transposeThenSort(pcs5.getCollection(), 12);
        transposeThenSort(pcs6.getCollection(), 6);

        assertEquals(pcs1Binary, 15);
        assertEquals(pcs2Binary, 2063);
        assertEquals(pcs3Binary, 2218);
        assertEquals(pcs4Binary, 14);
        assertEquals(pcs5Binary, 1);
        assertEquals(pcs6Binary, 1);
        assertEquals(pcs1.getCollection().toString(), "[0, 1, 2, 3]");
        assertEquals(pcs2.getCollection().toString(), "[0, 1, 2, 3, 11]");
        assertEquals(pcs3.getCollection().toString(), "[1, 3, 5, 7, 11]");
        assertEquals(pcs4.getCollection().toString(), "[1, 2, 3]");
        assertEquals(pcs5.getCollection().toString(), "[0]");
        assertEquals(pcs6.getCollection().toString(), "[0]");
    }

    @Test
    public void inversionTests() throws Exception {
        PitchClassSet pcs1 = PitchClassSet.fromString("9AB0");
        PitchClassSet pcs2 = PitchClassSet.fromString("9A1B0");
        PitchClassSet pcs3 = PitchClassSet.fromString("2468A");
        PitchClassSet pcs4 = PitchClassSet.fromString("123");
        PitchClassSet pcs5 = PitchClassSet.fromString("0");
        PitchClassSet pcs6 = PitchClassSet.fromString("6");

        int pcs1Binary = invert(pcs1.getOriginalSetBinary());
        int pcs2Binary = invert(pcs2.getOriginalSetBinary());
        int pcs3Binary = invert(pcs3.getOriginalSetBinary());
        int pcs4Binary = invert(pcs4.getOriginalSetBinary());
        int pcs5Binary = invert(pcs5.getOriginalSetBinary());
        int pcs6Binary = invert(pcs6.getOriginalSetBinary());
        invertThenSort(pcs1.getCollection());
        invertThenSort(pcs2.getCollection());
        invertThenSort(pcs3.getCollection());
        invertThenSort(pcs4.getCollection());
        invertThenSort(pcs5.getCollection());
        invertThenSort(pcs6.getCollection());

        assertEquals(pcs1Binary, 15);
        assertEquals(pcs2Binary, 2063);
        assertEquals(pcs3Binary, 1364);
        assertEquals(pcs4Binary, 3584);
        assertEquals(pcs5Binary, 1);
        assertEquals(pcs6Binary, 64);
        assertEquals(pcs1.getCollection().toString(), "[0, 1, 2, 3]");
        assertEquals(pcs2.getCollection().toString(), "[0, 1, 2, 3, 11]");
        assertEquals(pcs3.getCollection().toString(), "[2, 4, 6, 8, 10]");
        assertEquals(pcs4.getCollection().toString(), "[9, 10, 11]");
        assertEquals(pcs5.getCollection().toString(), "[0]");
        assertEquals(pcs6.getCollection().toString(), "[6]");

    }

    @Test
    public void mockDriver() throws Exception {
        PitchClassSet pcs1 = PitchClassSet.fromString("0468");
    }
}

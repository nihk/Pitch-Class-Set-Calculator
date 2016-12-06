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
                    + "\nPitchClassSet in binary: " + Integer.toBinaryString(key),
                    forteNumber);
        }
    }

    @Test
    public void normalFormIsCorrectlyCalculated() throws Exception {
        PitchClassSet pcs1 = PitchClassSet.fromString("0468");
        PitchClassSet pcs2 = PitchClassSet.fromString("7A3B0");
        PitchClassSet pcs3 = PitchClassSet.fromString("0136789");
        PitchClassSet pcs4 = PitchClassSet.fromString("024678");
        PitchClassSet pcs5 = PitchClassSet.fromString("915");
        PitchClassSet pcs6 = PitchClassSet.fromString("2B58");
        PitchClassSet pcs7 = PitchClassSet.fromString("125689AB");
        PitchClassSet pcs8 = PitchClassSet.fromString("4A");
        PitchClassSet pcs9 = PitchClassSet.fromString("124578AB");

        assertEquals("[4, 6, 8, 0]", pcs1.getNormalFormCollection().toString());
        assertEquals("[7, 10, 11, 0, 3]", pcs2.getNormalFormCollection().toString());
        assertEquals("[6, 7, 8, 9, 0, 1, 3]", pcs3.getNormalFormCollection().toString());
        assertEquals("[0, 2, 4, 6, 7, 8]", pcs4.getNormalFormCollection().toString());
        assertEquals("[1, 5, 9]", pcs5.getNormalFormCollection().toString());
        assertEquals("[2, 5, 8, 11]", pcs6.getNormalFormCollection().toString());
        assertEquals("[5, 6, 8, 9, 10, 11, 1, 2]", pcs7.getNormalFormCollection().toString());
        assertEquals("[4, 10]", pcs8.getNormalFormCollection().toString());
        assertEquals("[1, 2, 4, 5, 7, 8, 10, 11]", pcs9.getNormalFormCollection().toString());
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

        assertEquals("From binary", "[1, 0, 1, 3, 1, 0]", pcs1.getIntervalVector().toString());
        assertEquals("From Forte", "[0, 3, 2, 1, 4, 0]", pcs2.getIntervalVector().toString());
        assertEquals("From String", "[2, 1, 0, 0, 0, 0]", pcs3.getIntervalVector().toString());
    }

    /**
     * This tests a few cases of inclusion relationships between sets, i.e. super/subset
     * relationships
     *
     * @throws Exception
     */
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

        assertEquals(15, pcs1Binary);
        assertEquals(2063, pcs2Binary);
        assertEquals(2218, pcs3Binary);
        assertEquals(14, pcs4Binary);
        assertEquals(1, pcs5Binary);
        assertEquals(1, pcs6Binary);
        assertEquals("[0, 1, 2, 3]", pcs1.getCollection().toString());
        assertEquals("[0, 1, 2, 3, 11]", pcs2.getCollection().toString());
        assertEquals("[1, 3, 5, 7, 11]", pcs3.getCollection().toString());
        assertEquals("[1, 2, 3]", pcs4.getCollection().toString());
        assertEquals("[0]", pcs5.getCollection().toString());
        assertEquals("[0]", pcs6.getCollection().toString());
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

        assertEquals(15, pcs1Binary);
        assertEquals(2063, pcs2Binary);
        assertEquals(1364, pcs3Binary);
        assertEquals(3584, pcs4Binary);
        assertEquals(1, pcs5Binary);
        assertEquals(64, pcs6Binary);
        assertEquals("[0, 1, 2, 3]", pcs1.getCollection().toString());
        assertEquals("[0, 1, 2, 3, 11]", pcs2.getCollection().toString());
        assertEquals("[2, 4, 6, 8, 10]", pcs3.getCollection().toString());
        assertEquals("[9, 10, 11]", pcs4.getCollection().toString());
        assertEquals("[0]", pcs5.getCollection().toString());
        assertEquals("[6]", pcs6.getCollection().toString());

    }

    @Test
    public void mockDriver() throws Exception {
        PitchClassSet pcs1 = PitchClassSet.fromString("0468");
    }
}

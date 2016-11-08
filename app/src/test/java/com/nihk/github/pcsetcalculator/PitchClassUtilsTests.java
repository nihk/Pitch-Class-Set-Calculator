package com.nihk.github.pcsetcalculator;

import com.nihk.github.pcsetcalculator.utils.ForteNumberUtils;
import com.nihk.github.pcsetcalculator.utils.PitchClassUtils;
import com.nihk.github.pcsetcalculator.model.ForteNumber;
import com.nihk.github.pcsetcalculator.model.PitchClassSet;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nick on 2016-11-05.
 */
public class PitchClassUtilsTests {
    @Test
    public void setPrimeFormsHaveExistingForteNumber() throws Exception {
        int numPitchClassSets = (int)Math.pow(2, 12);
        for (int i = 0; i < numPitchClassSets; i++) {
            int bitCount = Integer.bitCount(i);
            if (bitCount < 3 || bitCount >= 10) {
                continue;
            }

            String setString = PitchClassUtils.setToString(i);
            PitchClassSet set = PitchClassSet.newInstance(setString);
            Integer key = set.getPrimeFormBinaryRepresentation();

            ForteNumber forteNumber =
                    ForteNumberUtils.BIMAP.get(key);
            assertNotNull("Didn't find prime form: " + key
                    + "\nSet in binary: " + Integer.toBinaryString(key), forteNumber);
        }
    }
}

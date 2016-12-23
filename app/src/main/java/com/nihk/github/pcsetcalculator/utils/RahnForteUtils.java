package com.nihk.github.pcsetcalculator.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.nihk.github.pcsetcalculator.models.ForteNumber;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nihk.github.pcsetcalculator.utils.ForteNumberUtils.*;

/**
 * Created by Nick on 2016-12-17.
 */

/**
 * A utility class for assisting with the Rahn and Forte primes which don't match due
 * to different algorithm usage. Since only 6 set classes differ (and that amount is not
 * subject to change) it's easiest to just handle it on a case-by-case basis.
 */
public final class RahnForteUtils {
    private static final int RAHN_5_20;
    private static final int RAHN_6_Z29;
    private static final int RAHN_6_31;
    private static final int RAHN_7_Z18;
    private static final int RAHN_7_20;
    private static final int RAHN_8_26;

    private static final int FORTE_5_20 =   395;
    private static final int FORTE_6_Z29 =  843;
    private static final int FORTE_6_31 =   811;
    private static final int FORTE_7_Z18 =  815;
    private static final int FORTE_7_20 =   919;
    private static final int FORTE_8_26 =   1719;

    // Zero-based inversions of the prime form. 6-Z29 and 8-26 are the same, so weren't included
    private static final int FORTE_5_20_INVERSION =   419;
    private static final int FORTE_6_31_INVERSION =   851;
    private static final int FORTE_7_Z18_INVERSION =  979;
    private static final int FORTE_7_20_INVERSION =   935;

    private static final List<String> RAHN_FORTE_PRIMES_STRINGS = new ArrayList<String>() {{
        add(_5_20.toString());
        add(_6_Z29.toString());
        add(_6_31.toString());
        add(_7_Z18.toString());
        add(_7_20.toString());
        add(_8_26.toString());
    }};

    public static final BiMap<Integer, Integer> RAHN_TO_FORTE_PRIMES_BINARY = HashBiMap.create();

    private RahnForteUtils() {
        // Prevent instantiation
    }

    static {
        final BiMap<ForteNumber, Integer> inverseBiMap = BIMAP.inverse();
        RAHN_5_20 =    inverseBiMap.get(_5_20);
        RAHN_6_Z29 =   inverseBiMap.get(_6_Z29);
        RAHN_6_31 =    inverseBiMap.get(_6_31);
        RAHN_7_Z18 =   inverseBiMap.get(_7_Z18);
        RAHN_7_20 =    inverseBiMap.get(_7_20);
        RAHN_8_26 =    inverseBiMap.get(_8_26);

        RAHN_TO_FORTE_PRIMES_BINARY.put(RAHN_5_20,  FORTE_5_20);
        RAHN_TO_FORTE_PRIMES_BINARY.put(RAHN_6_Z29, FORTE_6_Z29);
        RAHN_TO_FORTE_PRIMES_BINARY.put(RAHN_6_31,  FORTE_6_31);
        RAHN_TO_FORTE_PRIMES_BINARY.put(RAHN_7_Z18, FORTE_7_Z18);
        RAHN_TO_FORTE_PRIMES_BINARY.put(RAHN_7_20,  FORTE_7_20);
        RAHN_TO_FORTE_PRIMES_BINARY.put(RAHN_8_26,  FORTE_8_26);
    }

    public static boolean isForteRahnUnequalPrime(int set) {
        return RAHN_TO_FORTE_PRIMES_BINARY.containsKey(set);
    }

    // Inversions of the Forte primes, zero based. This is used for getting normal form using the Forte algorithm
    public static final Map<Integer, Integer> FORTE_PRIME_INVERSIONS = new HashMap<Integer, Integer>() {{
        put(FORTE_5_20,     FORTE_5_20_INVERSION);
        put(FORTE_6_Z29,    FORTE_6_Z29);  // Identical inversion
        put(FORTE_6_31,     FORTE_6_31_INVERSION);
        put(FORTE_7_Z18,    FORTE_7_Z18_INVERSION);
        put(FORTE_7_20,     FORTE_7_20_INVERSION);
        put(FORTE_8_26,     FORTE_8_26);  // Identical inversion
    }};

    public static boolean isPrimeFormDifferentDependingOnAlgorithm(final String forteNumber) {
        return RAHN_FORTE_PRIMES_STRINGS.contains(forteNumber);
    }

    public static boolean isPrimeFormDifferentDependingOnAlgorithm(final PitchClassSet pitchClassSet) {
        final ForteNumber forteNumber;

        return pitchClassSet != null && (forteNumber = pitchClassSet.getForteNumber()) != null
                && isPrimeFormDifferentDependingOnAlgorithm(forteNumber.toString());
    }
}

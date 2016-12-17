package com.nihk.github.pcsetcalculator.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 2016-12-17.
 */

public final class RahnForteUtils {
    private RahnForteUtils() {
        // Prevent instantiation
    }

    public static boolean isForteRahnUnequalPrime(int set) {
        return RAHN_TO_FORTE_PRIMES.containsKey(set);
    }

    // These Rahn prime forms are not the same prime forms when using the Forte
    // algorithm. Since it only affets 5 set classes, I used a Map instead of implementing
    // the different algorithm in its entirety.
    public static final BiMap<Integer, Integer> RAHN_TO_FORTE_PRIMES = HashBiMap.create();

    static {
        RAHN_TO_FORTE_PRIMES.put(355, 395);      // 5-20
        RAHN_TO_FORTE_PRIMES.put(717, 843);      // 6-Z29
        RAHN_TO_FORTE_PRIMES.put(691, 811);      // 6-31
        RAHN_TO_FORTE_PRIMES.put(743, 919);      // 7-20
        RAHN_TO_FORTE_PRIMES.put(1467, 1719);    // 8-26
    }

    // Inversions of the Forte primes, zero based.
    public static final Map<Integer, Integer> FORTE_PRIME_INVERSIONS = new HashMap<Integer, Integer>() {{
        put(395, 419);      // 5-20
        put(843, 843);      // 6-Z29
        put(811, 851);      // 6-31
        put(919, 935);      // 7-20
        put(1719, 1719);    // 8-26
    }};
}

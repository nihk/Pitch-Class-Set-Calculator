package com.nihk.github.pcsetcalculator.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.nihk.github.pcsetcalculator.models.ForteNumber;

/**
 * Created by Nick on 2016-11-14.
 */

/**
 * A class to store the zed related Forte number pairs
 */
public final class IntervalVectorUtils {
    private IntervalVectorUtils() {
        // Prevent instantiation
    }

    public static final BiMap<ForteNumber, ForteNumber> Z_MATES = HashBiMap.create();

    static {
        Z_MATES.put(ForteNumberUtils._4_Z29, ForteNumberUtils._4_Z15);
        Z_MATES.put(ForteNumberUtils._4_Z15, ForteNumberUtils._4_Z29);
        Z_MATES.put(ForteNumberUtils._8_Z15, ForteNumberUtils._8_Z29);
        Z_MATES.put(ForteNumberUtils._8_Z29, ForteNumberUtils._8_Z15);
        Z_MATES.put(ForteNumberUtils._5_Z36, ForteNumberUtils._5_Z12);
        Z_MATES.put(ForteNumberUtils._5_Z38, ForteNumberUtils._5_Z18);
        Z_MATES.put(ForteNumberUtils._5_Z17, ForteNumberUtils._5_Z37);
        Z_MATES.put(ForteNumberUtils._5_Z12, ForteNumberUtils._5_Z36);
        Z_MATES.put(ForteNumberUtils._5_Z18, ForteNumberUtils._5_Z38);
        Z_MATES.put(ForteNumberUtils._5_Z37, ForteNumberUtils._5_Z17);
        Z_MATES.put(ForteNumberUtils._7_Z12, ForteNumberUtils._7_Z36);
        Z_MATES.put(ForteNumberUtils._7_Z36, ForteNumberUtils._7_Z12);
        Z_MATES.put(ForteNumberUtils._7_Z18, ForteNumberUtils._7_Z38);
        Z_MATES.put(ForteNumberUtils._7_Z17, ForteNumberUtils._7_Z37);
        Z_MATES.put(ForteNumberUtils._7_Z38, ForteNumberUtils._7_Z18);
        Z_MATES.put(ForteNumberUtils._7_Z37, ForteNumberUtils._7_Z17);
        Z_MATES.put(ForteNumberUtils._6_Z36, ForteNumberUtils._6_Z3);
        Z_MATES.put(ForteNumberUtils._6_Z37, ForteNumberUtils._6_Z4);
        Z_MATES.put(ForteNumberUtils._6_Z3, ForteNumberUtils._6_Z36);
        Z_MATES.put(ForteNumberUtils._6_Z40, ForteNumberUtils._6_Z11);
        Z_MATES.put(ForteNumberUtils._6_Z41, ForteNumberUtils._6_Z12);
        Z_MATES.put(ForteNumberUtils._6_Z42, ForteNumberUtils._6_Z13);
        Z_MATES.put(ForteNumberUtils._6_Z38, ForteNumberUtils._6_Z6);
        Z_MATES.put(ForteNumberUtils._6_Z4, ForteNumberUtils._6_Z37);
        Z_MATES.put(ForteNumberUtils._6_Z11, ForteNumberUtils._6_Z40);
        Z_MATES.put(ForteNumberUtils._6_Z12, ForteNumberUtils._6_Z41);
        Z_MATES.put(ForteNumberUtils._6_Z46, ForteNumberUtils._6_Z24);
        Z_MATES.put(ForteNumberUtils._6_Z17, ForteNumberUtils._6_Z43);
        Z_MATES.put(ForteNumberUtils._6_Z47, ForteNumberUtils._6_Z25);
        Z_MATES.put(ForteNumberUtils._6_Z6, ForteNumberUtils._6_Z38);
        Z_MATES.put(ForteNumberUtils._6_Z43, ForteNumberUtils._6_Z17);
        Z_MATES.put(ForteNumberUtils._6_Z44, ForteNumberUtils._6_Z19);
        Z_MATES.put(ForteNumberUtils._6_Z48, ForteNumberUtils._6_Z26);
        Z_MATES.put(ForteNumberUtils._6_Z10, ForteNumberUtils._6_Z39);
        Z_MATES.put(ForteNumberUtils._6_Z13, ForteNumberUtils._6_Z42);
        Z_MATES.put(ForteNumberUtils._6_Z24, ForteNumberUtils._6_Z46);
        Z_MATES.put(ForteNumberUtils._6_Z19, ForteNumberUtils._6_Z44);
        Z_MATES.put(ForteNumberUtils._6_Z49, ForteNumberUtils._6_Z28);
        Z_MATES.put(ForteNumberUtils._6_Z25, ForteNumberUtils._6_Z47);
        Z_MATES.put(ForteNumberUtils._6_Z28, ForteNumberUtils._6_Z49);
        Z_MATES.put(ForteNumberUtils._6_Z26, ForteNumberUtils._6_Z48);
        Z_MATES.put(ForteNumberUtils._6_Z50, ForteNumberUtils._6_Z29);
        Z_MATES.put(ForteNumberUtils._6_Z39, ForteNumberUtils._6_Z10);
        Z_MATES.put(ForteNumberUtils._6_Z45, ForteNumberUtils._6_Z23);
        Z_MATES.put(ForteNumberUtils._6_Z23, ForteNumberUtils._6_Z45);
        Z_MATES.put(ForteNumberUtils._6_Z29, ForteNumberUtils._6_Z50);
    }
}

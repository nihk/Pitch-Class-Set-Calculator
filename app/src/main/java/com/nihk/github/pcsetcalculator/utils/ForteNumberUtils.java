package com.nihk.github.pcsetcalculator.utils;

import com.nihk.github.pcsetcalculator.models.ForteNumber;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Created by Nick on 2016-11-05.
 */

/**
 * A utility class to store a map of all 208 prime forms (binary representation) to their
 * respective Forte numbers
 */
public final class ForteNumberUtils {
    private ForteNumberUtils() {
        // Prevent instantiation
    }

    public static final int MIN_FORTE_CARDINALITY = 3;
    public static final int MAX_FORTE_CARDINALITY = 9;

    public static final ForteNumber _3_1 = new ForteNumber("3-1");
    public static final ForteNumber _3_2 = new ForteNumber("3-2");
    public static final ForteNumber _3_3 = new ForteNumber("3-3");
    public static final ForteNumber _3_4 = new ForteNumber("3-4");
    public static final ForteNumber _3_5 = new ForteNumber("3-5");
    public static final ForteNumber _3_6 = new ForteNumber("3-6");
    public static final ForteNumber _3_7 = new ForteNumber("3-7");
    public static final ForteNumber _3_8 = new ForteNumber("3-8");
    public static final ForteNumber _3_9 = new ForteNumber("3-9");
    public static final ForteNumber _3_10 = new ForteNumber("3-10");
    public static final ForteNumber _3_11 = new ForteNumber("3-11");
    public static final ForteNumber _3_12 = new ForteNumber("3-12");

    public static final ForteNumber _4_1 = new ForteNumber("4-1");
    public static final ForteNumber _4_2 = new ForteNumber("4-2");
    public static final ForteNumber _4_3 = new ForteNumber("4-3");
    public static final ForteNumber _4_4 = new ForteNumber("4-4");
    public static final ForteNumber _4_5 = new ForteNumber("4-5");
    public static final ForteNumber _4_6 = new ForteNumber("4-6");
    public static final ForteNumber _4_7 = new ForteNumber("4-7");
    public static final ForteNumber _4_8 = new ForteNumber("4-8");
    public static final ForteNumber _4_9 = new ForteNumber("4-9");
    public static final ForteNumber _4_10 = new ForteNumber("4-10");
    public static final ForteNumber _4_11 = new ForteNumber("4-11");
    public static final ForteNumber _4_12 = new ForteNumber("4-12");
    public static final ForteNumber _4_13 = new ForteNumber("4-13");
    public static final ForteNumber _4_14 = new ForteNumber("4-14");
    public static final ForteNumber _4_Z15 = new ForteNumber("4-Z15");
    public static final ForteNumber _4_16 = new ForteNumber("4-16");
    public static final ForteNumber _4_17 = new ForteNumber("4-17");
    public static final ForteNumber _4_18 = new ForteNumber("4-18");
    public static final ForteNumber _4_19 = new ForteNumber("4-19");
    public static final ForteNumber _4_20 = new ForteNumber("4-20");
    public static final ForteNumber _4_21 = new ForteNumber("4-21");
    public static final ForteNumber _4_22 = new ForteNumber("4-22");
    public static final ForteNumber _4_23 = new ForteNumber("4-23");
    public static final ForteNumber _4_24 = new ForteNumber("4-24");
    public static final ForteNumber _4_25 = new ForteNumber("4-25");
    public static final ForteNumber _4_26 = new ForteNumber("4-26");
    public static final ForteNumber _4_27 = new ForteNumber("4-27");
    public static final ForteNumber _4_28 = new ForteNumber("4-28");
    public static final ForteNumber _4_Z29 = new ForteNumber("4-Z29");

    public static final ForteNumber _5_1 = new ForteNumber("5-1");
    public static final ForteNumber _5_2 = new ForteNumber("5-2");
    public static final ForteNumber _5_3 = new ForteNumber("5-3");
    public static final ForteNumber _5_4 = new ForteNumber("5-4");
    public static final ForteNumber _5_5 = new ForteNumber("5-5");
    public static final ForteNumber _5_6 = new ForteNumber("5-6");
    public static final ForteNumber _5_7 = new ForteNumber("5-7");
    public static final ForteNumber _5_8 = new ForteNumber("5-8");
    public static final ForteNumber _5_9 = new ForteNumber("5-9");
    public static final ForteNumber _5_10 = new ForteNumber("5-10");
    public static final ForteNumber _5_11 = new ForteNumber("5-11");
    public static final ForteNumber _5_Z12 = new ForteNumber("5-Z12");
    public static final ForteNumber _5_13 = new ForteNumber("5-13");
    public static final ForteNumber _5_14 = new ForteNumber("5-14");
    public static final ForteNumber _5_15 = new ForteNumber("5-15");
    public static final ForteNumber _5_16 = new ForteNumber("5-16");
    public static final ForteNumber _5_Z17 = new ForteNumber("5-Z17");
    public static final ForteNumber _5_Z18 = new ForteNumber("5-Z18");
    public static final ForteNumber _5_19 = new ForteNumber("5-19");
    public static final ForteNumber _5_20 = new ForteNumber("5-20");
    public static final ForteNumber _5_21 = new ForteNumber("5-21");
    public static final ForteNumber _5_22 = new ForteNumber("5-22");
    public static final ForteNumber _5_23 = new ForteNumber("5-23");
    public static final ForteNumber _5_24 = new ForteNumber("5-24");
    public static final ForteNumber _5_25 = new ForteNumber("5-25");
    public static final ForteNumber _5_26 = new ForteNumber("5-26");
    public static final ForteNumber _5_27 = new ForteNumber("5-27");
    public static final ForteNumber _5_28 = new ForteNumber("5-28");
    public static final ForteNumber _5_29 = new ForteNumber("5-29");
    public static final ForteNumber _5_30 = new ForteNumber("5-30");
    public static final ForteNumber _5_31 = new ForteNumber("5-31");
    public static final ForteNumber _5_32 = new ForteNumber("5-32");
    public static final ForteNumber _5_33 = new ForteNumber("5-33");
    public static final ForteNumber _5_34 = new ForteNumber("5-34");
    public static final ForteNumber _5_35 = new ForteNumber("5-35");
    public static final ForteNumber _5_Z36 = new ForteNumber("5-Z36");
    public static final ForteNumber _5_Z37 = new ForteNumber("5-Z37");
    public static final ForteNumber _5_Z38 = new ForteNumber("5-Z38");

    public static final ForteNumber _6_1 = new ForteNumber("6-1");
    public static final ForteNumber _6_2 = new ForteNumber("6-2");
    public static final ForteNumber _6_Z3 = new ForteNumber("6-Z3");
    public static final ForteNumber _6_Z4 = new ForteNumber("6-Z4");
    public static final ForteNumber _6_5 = new ForteNumber("6-5");
    public static final ForteNumber _6_Z6 = new ForteNumber("6-Z6");
    public static final ForteNumber _6_7 = new ForteNumber("6-7");
    public static final ForteNumber _6_8 = new ForteNumber("6-8");
    public static final ForteNumber _6_9 = new ForteNumber("6-9");
    public static final ForteNumber _6_Z10 = new ForteNumber("6-Z10");
    public static final ForteNumber _6_Z11 = new ForteNumber("6-Z11");
    public static final ForteNumber _6_Z12 = new ForteNumber("6-Z12");
    public static final ForteNumber _6_Z13 = new ForteNumber("6-Z13");
    public static final ForteNumber _6_14 = new ForteNumber("6-14");
    public static final ForteNumber _6_15 = new ForteNumber("6-15");
    public static final ForteNumber _6_16 = new ForteNumber("6-16");
    public static final ForteNumber _6_Z17 = new ForteNumber("6-Z17");
    public static final ForteNumber _6_18 = new ForteNumber("6-18");
    public static final ForteNumber _6_Z19 = new ForteNumber("6-Z19");
    public static final ForteNumber _6_20 = new ForteNumber("6-20");
    public static final ForteNumber _6_21 = new ForteNumber("6-21");
    public static final ForteNumber _6_22 = new ForteNumber("6-22");
    public static final ForteNumber _6_Z23 = new ForteNumber("6-Z23");
    public static final ForteNumber _6_Z24 = new ForteNumber("6-Z24");
    public static final ForteNumber _6_Z25 = new ForteNumber("6-Z25");
    public static final ForteNumber _6_Z26 = new ForteNumber("6-Z26");
    public static final ForteNumber _6_27 = new ForteNumber("6-27");
    public static final ForteNumber _6_Z28 = new ForteNumber("6-Z28");
    public static final ForteNumber _6_Z29 = new ForteNumber("6-Z29");
    public static final ForteNumber _6_30 = new ForteNumber("6-30");
    public static final ForteNumber _6_31 = new ForteNumber("6-31");
    public static final ForteNumber _6_32 = new ForteNumber("6-32");
    public static final ForteNumber _6_33 = new ForteNumber("6-33");
    public static final ForteNumber _6_34 = new ForteNumber("6-34");
    public static final ForteNumber _6_35 = new ForteNumber("6-35");
    public static final ForteNumber _6_Z36 = new ForteNumber("6-Z36");
    public static final ForteNumber _6_Z37 = new ForteNumber("6-Z37");
    public static final ForteNumber _6_Z38 = new ForteNumber("6-Z38");
    public static final ForteNumber _6_Z39 = new ForteNumber("6-Z39");
    public static final ForteNumber _6_Z40 = new ForteNumber("6-Z40");
    public static final ForteNumber _6_Z41 = new ForteNumber("6-Z41");
    public static final ForteNumber _6_Z42 = new ForteNumber("6-Z42");
    public static final ForteNumber _6_Z43 = new ForteNumber("6-Z43");
    public static final ForteNumber _6_Z44 = new ForteNumber("6-Z44");
    public static final ForteNumber _6_Z45 = new ForteNumber("6-Z45");
    public static final ForteNumber _6_Z46 = new ForteNumber("6-Z46");
    public static final ForteNumber _6_Z47 = new ForteNumber("6-Z47");
    public static final ForteNumber _6_Z48 = new ForteNumber("6-Z48");
    public static final ForteNumber _6_Z49 = new ForteNumber("6-Z49");
    public static final ForteNumber _6_Z50 = new ForteNumber("6-Z50");

    public static final ForteNumber _7_1 = new ForteNumber("7-1");
    public static final ForteNumber _7_2 = new ForteNumber("7-2");
    public static final ForteNumber _7_3 = new ForteNumber("7-3");
    public static final ForteNumber _7_4 = new ForteNumber("7-4");
    public static final ForteNumber _7_5 = new ForteNumber("7-5");
    public static final ForteNumber _7_6 = new ForteNumber("7-6");
    public static final ForteNumber _7_7 = new ForteNumber("7-7");
    public static final ForteNumber _7_8 = new ForteNumber("7-8");
    public static final ForteNumber _7_9 = new ForteNumber("7-9");
    public static final ForteNumber _7_10 = new ForteNumber("7-10");
    public static final ForteNumber _7_11 = new ForteNumber("7-11");
    public static final ForteNumber _7_Z12 = new ForteNumber("7-Z12");
    public static final ForteNumber _7_13 = new ForteNumber("7-13");
    public static final ForteNumber _7_14 = new ForteNumber("7-14");
    public static final ForteNumber _7_15 = new ForteNumber("7-15");
    public static final ForteNumber _7_16 = new ForteNumber("7-16");
    public static final ForteNumber _7_Z17 = new ForteNumber("7-Z17");
    public static final ForteNumber _7_Z18 = new ForteNumber("7-Z18");
    public static final ForteNumber _7_19 = new ForteNumber("7-19");
    public static final ForteNumber _7_20 = new ForteNumber("7-20");
    public static final ForteNumber _7_21 = new ForteNumber("7-21");
    public static final ForteNumber _7_22 = new ForteNumber("7-22");
    public static final ForteNumber _7_23 = new ForteNumber("7-23");
    public static final ForteNumber _7_24 = new ForteNumber("7-24");
    public static final ForteNumber _7_25 = new ForteNumber("7-25");
    public static final ForteNumber _7_26 = new ForteNumber("7-26");
    public static final ForteNumber _7_27 = new ForteNumber("7-27");
    public static final ForteNumber _7_28 = new ForteNumber("7-28");
    public static final ForteNumber _7_29 = new ForteNumber("7-29");
    public static final ForteNumber _7_30 = new ForteNumber("7-30");
    public static final ForteNumber _7_31 = new ForteNumber("7-31");
    public static final ForteNumber _7_32 = new ForteNumber("7-32");
    public static final ForteNumber _7_33 = new ForteNumber("7-33");
    public static final ForteNumber _7_34 = new ForteNumber("7-34");
    public static final ForteNumber _7_35 = new ForteNumber("7-35");
    public static final ForteNumber _7_Z36 = new ForteNumber("7-Z36");
    public static final ForteNumber _7_Z37 = new ForteNumber("7-Z37");
    public static final ForteNumber _7_Z38 = new ForteNumber("7-Z38");

    public static final ForteNumber _8_1 = new ForteNumber("8-1");
    public static final ForteNumber _8_2 = new ForteNumber("8-2");
    public static final ForteNumber _8_3 = new ForteNumber("8-3");
    public static final ForteNumber _8_4 = new ForteNumber("8-4");
    public static final ForteNumber _8_5 = new ForteNumber("8-5");
    public static final ForteNumber _8_6 = new ForteNumber("8-6");
    public static final ForteNumber _8_7 = new ForteNumber("8-7");
    public static final ForteNumber _8_8 = new ForteNumber("8-8");
    public static final ForteNumber _8_9 = new ForteNumber("8-9");
    public static final ForteNumber _8_10 = new ForteNumber("8-10");
    public static final ForteNumber _8_11 = new ForteNumber("8-11");
    public static final ForteNumber _8_13 = new ForteNumber("8-13");
    public static final ForteNumber _8_12 = new ForteNumber("8-12");
    public static final ForteNumber _8_14 = new ForteNumber("8-14");
    public static final ForteNumber _8_Z15 = new ForteNumber("8-Z15");
    public static final ForteNumber _8_16 = new ForteNumber("8-16");
    public static final ForteNumber _8_17 = new ForteNumber("8-17");
    public static final ForteNumber _8_18 = new ForteNumber("8-18");
    public static final ForteNumber _8_19 = new ForteNumber("8-19");
    public static final ForteNumber _8_20 = new ForteNumber("8-20");
    public static final ForteNumber _8_21 = new ForteNumber("8-21");
    public static final ForteNumber _8_22 = new ForteNumber("8-22");
    public static final ForteNumber _8_23 = new ForteNumber("8-23");
    public static final ForteNumber _8_24 = new ForteNumber("8-24");
    public static final ForteNumber _8_25 = new ForteNumber("8-25");
    public static final ForteNumber _8_26 = new ForteNumber("8-26");
    public static final ForteNumber _8_27 = new ForteNumber("8-27");
    public static final ForteNumber _8_28 = new ForteNumber("8-28");
    public static final ForteNumber _8_Z29 = new ForteNumber("8-Z29");

    public static final ForteNumber _9_1 = new ForteNumber("9-1");
    public static final ForteNumber _9_2 = new ForteNumber("9-2");
    public static final ForteNumber _9_3 = new ForteNumber("9-3");
    public static final ForteNumber _9_4 = new ForteNumber("9-4");
    public static final ForteNumber _9_5 = new ForteNumber("9-5");
    public static final ForteNumber _9_6 = new ForteNumber("9-6");
    public static final ForteNumber _9_7 = new ForteNumber("9-7");
    public static final ForteNumber _9_8 = new ForteNumber("9-8");
    public static final ForteNumber _9_9 = new ForteNumber("9-9");
    public static final ForteNumber _9_10 = new ForteNumber("9-10");
    public static final ForteNumber _9_11 = new ForteNumber("9-11");
    public static final ForteNumber _9_12 = new ForteNumber("9-12");

    // A BiMap of sets (binary representation) and Forte numbers
    public static final BiMap<Integer, ForteNumber> BIMAP = HashBiMap.create();

    // Generated by copy/pasting a raw list of pitch class sets from the internet, parsing that
    // data, then finally iterating over it to println each respective set class
    static {
        BIMAP.put(7, _3_1);
        BIMAP.put(11, _3_2);
        BIMAP.put(19, _3_3);
        BIMAP.put(35, _3_4);
        BIMAP.put(67, _3_5);
        BIMAP.put(21, _3_6);
        BIMAP.put(37, _3_7);
        BIMAP.put(69, _3_8);
        BIMAP.put(133, _3_9);
        BIMAP.put(73, _3_10);
        BIMAP.put(137, _3_11);
        BIMAP.put(273, _3_12);

        BIMAP.put(15, _4_1);
        BIMAP.put(23, _4_2);
        BIMAP.put(27, _4_3);
        BIMAP.put(39, _4_4);
        BIMAP.put(71, _4_5);
        BIMAP.put(135, _4_6);
        BIMAP.put(51, _4_7);
        BIMAP.put(99, _4_8);
        BIMAP.put(195, _4_9);
        BIMAP.put(45, _4_10);
        BIMAP.put(43, _4_11);
        BIMAP.put(77, _4_12);
        BIMAP.put(75, _4_13);
        BIMAP.put(141, _4_14);
        BIMAP.put(83, _4_Z15);
        BIMAP.put(163, _4_16);
        BIMAP.put(153, _4_17);
        BIMAP.put(147, _4_18);
        BIMAP.put(275, _4_19);
        BIMAP.put(291, _4_20);
        BIMAP.put(85, _4_21);
        BIMAP.put(149, _4_22);
        BIMAP.put(165, _4_23);
        BIMAP.put(277, _4_24);
        BIMAP.put(325, _4_25);
        BIMAP.put(297, _4_26);
        BIMAP.put(293, _4_27);
        BIMAP.put(585, _4_28);
        BIMAP.put(139, _4_Z29);

        BIMAP.put(31, _5_1);
        BIMAP.put(47, _5_2);
        BIMAP.put(55, _5_3);
        BIMAP.put(79, _5_4);
        BIMAP.put(143, _5_5);
        BIMAP.put(103, _5_6);
        BIMAP.put(199, _5_7);
        BIMAP.put(93, _5_8);
        BIMAP.put(87, _5_9);
        BIMAP.put(91, _5_10);
        BIMAP.put(157, _5_11);
        BIMAP.put(107, _5_Z12);
        BIMAP.put(279, _5_13);
        BIMAP.put(167, _5_14);
        BIMAP.put(327, _5_15);
        BIMAP.put(155, _5_16);
        BIMAP.put(283, _5_Z17);
        BIMAP.put(179, _5_Z18);
        BIMAP.put(203, _5_19);
        BIMAP.put(355, _5_20);
        BIMAP.put(307, _5_21);
        BIMAP.put(403, _5_22);
        BIMAP.put(173, _5_23);
        BIMAP.put(171, _5_24);
        BIMAP.put(301, _5_25);
        BIMAP.put(309, _5_26);
        BIMAP.put(299, _5_27);
        BIMAP.put(333, _5_28);
        BIMAP.put(331, _5_29);
        BIMAP.put(339, _5_30);
        BIMAP.put(587, _5_31);
        BIMAP.put(595, _5_32);
        BIMAP.put(341, _5_33);
        BIMAP.put(597, _5_34);
        BIMAP.put(661, _5_35);
        BIMAP.put(151, _5_Z36);
        BIMAP.put(313, _5_Z37);
        BIMAP.put(295, _5_Z38);

        BIMAP.put(63, _6_1);
        BIMAP.put(95, _6_2);
        BIMAP.put(111, _6_Z3);
        BIMAP.put(119, _6_Z4);
        BIMAP.put(207, _6_5);
        BIMAP.put(231, _6_Z6);
        BIMAP.put(455, _6_7);
        BIMAP.put(189, _6_8);
        BIMAP.put(175, _6_9);
        BIMAP.put(187, _6_Z10);
        BIMAP.put(183, _6_Z11);
        BIMAP.put(215, _6_Z12);
        BIMAP.put(219, _6_Z13);
        BIMAP.put(315, _6_14);
        BIMAP.put(311, _6_15);
        BIMAP.put(371, _6_16);
        BIMAP.put(407, _6_Z17);
        BIMAP.put(423, _6_18);
        BIMAP.put(411, _6_Z19);
        BIMAP.put(819, _6_20);
        BIMAP.put(349, _6_21);
        BIMAP.put(343, _6_22);
        BIMAP.put(365, _6_Z23);
        BIMAP.put(347, _6_Z24);
        BIMAP.put(363, _6_Z25);
        BIMAP.put(427, _6_Z26);
        BIMAP.put(603, _6_27);
        BIMAP.put(619, _6_Z28);
        BIMAP.put(717, _6_Z29);
        BIMAP.put(715, _6_30);
        BIMAP.put(691, _6_31);
        BIMAP.put(693, _6_32);
        BIMAP.put(685, _6_33);
        BIMAP.put(683, _6_34);
        BIMAP.put(1365, _6_35);
        BIMAP.put(159, _6_Z36);
        BIMAP.put(287, _6_Z37);
        BIMAP.put(399, _6_Z38);
        BIMAP.put(317, _6_Z39);
        BIMAP.put(303, _6_Z40);
        BIMAP.put(335, _6_Z41);
        BIMAP.put(591, _6_Z42);
        BIMAP.put(359, _6_Z43);
        BIMAP.put(615, _6_Z44);
        BIMAP.put(605, _6_Z45);
        BIMAP.put(599, _6_Z46);
        BIMAP.put(663, _6_Z47);
        BIMAP.put(679, _6_Z48);
        BIMAP.put(667, _6_Z49);
        BIMAP.put(723, _6_Z50);

        BIMAP.put(127, _7_1);
        BIMAP.put(191, _7_2);
        BIMAP.put(319, _7_3);
        BIMAP.put(223, _7_4);
        BIMAP.put(239, _7_5);
        BIMAP.put(415, _7_6);
        BIMAP.put(463, _7_7);
        BIMAP.put(381, _7_8);
        BIMAP.put(351, _7_9);
        BIMAP.put(607, _7_10);
        BIMAP.put(379, _7_11);
        BIMAP.put(671, _7_Z12);
        BIMAP.put(375, _7_13);
        BIMAP.put(431, _7_14);
        BIMAP.put(471, _7_15);
        BIMAP.put(623, _7_16);
        BIMAP.put(631, _7_Z17);
        BIMAP.put(755, _7_Z18);
        BIMAP.put(719, _7_19);
        BIMAP.put(743, _7_20);
        BIMAP.put(823, _7_21);
        BIMAP.put(871, _7_22);
        BIMAP.put(701, _7_23);
        BIMAP.put(687, _7_24);
        BIMAP.put(733, _7_25);
        BIMAP.put(699, _7_26);
        BIMAP.put(695, _7_27);
        BIMAP.put(747, _7_28);
        BIMAP.put(727, _7_29);
        BIMAP.put(855, _7_30);
        BIMAP.put(731, _7_31);
        BIMAP.put(859, _7_32);
        BIMAP.put(1367, _7_33);
        BIMAP.put(1371, _7_34);
        BIMAP.put(1387, _7_35);
        BIMAP.put(367, _7_Z36);
        BIMAP.put(443, _7_Z37);
        BIMAP.put(439, _7_Z38);

        BIMAP.put(255, _8_1);
        BIMAP.put(383, _8_2);
        BIMAP.put(639, _8_3);
        BIMAP.put(447, _8_4);
        BIMAP.put(479, _8_5);
        BIMAP.put(495, _8_6);
        BIMAP.put(831, _8_7);
        BIMAP.put(927, _8_8);
        BIMAP.put(975, _8_9);
        BIMAP.put(765, _8_10);
        BIMAP.put(703, _8_11);
        BIMAP.put(763, _8_12);
        BIMAP.put(735, _8_13);
        BIMAP.put(759, _8_14);
        BIMAP.put(863, _8_Z15);
        BIMAP.put(943, _8_16);
        BIMAP.put(891, _8_17);
        BIMAP.put(879, _8_18);
        BIMAP.put(887, _8_19);
        BIMAP.put(951, _8_20);
        BIMAP.put(1375, _8_21);
        BIMAP.put(1391, _8_22);
        BIMAP.put(1455, _8_23);
        BIMAP.put(1399, _8_24);
        BIMAP.put(1495, _8_25);
        BIMAP.put(1467, _8_26);
        BIMAP.put(1463, _8_27);
        BIMAP.put(1755, _8_28);
        BIMAP.put(751, _8_Z29);

        BIMAP.put(511, _9_1);
        BIMAP.put(767, _9_2);
        BIMAP.put(895, _9_3);
        BIMAP.put(959, _9_4);
        BIMAP.put(991, _9_5);
        BIMAP.put(1407, _9_6);
        BIMAP.put(1471, _9_7);
        BIMAP.put(1503, _9_8);
        BIMAP.put(1519, _9_9);
        BIMAP.put(1759, _9_10);
        BIMAP.put(1775, _9_11);
        BIMAP.put(1911, _9_12);
    }
}
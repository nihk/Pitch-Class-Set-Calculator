package com.nihk.github.pcsetcalculator.utils;

import com.nihk.github.pcsetcalculator.models.ForteNumber;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;

import java.util.List;
import java.util.Locale;

/**
 * Created by Nick on 2016-12-04.
 */

public class StringFormatUtils {
    private static final char SPACE = ' ';
    private static final String PLACEHOLDER = "---";
    private static final String EMPTY = "";
    private static final String UNORDERED_SET_FORMATTER = "{%s}";
    private static final String NORMAL_FORM_FORMATTER = "[%s]";
    private static final String PRIME_FORM_FORMATTER = "(%s)";
    private static final String INTERVAL_VECTOR_FORMATTER = "<%s>";
    private static final String Z_MATE_FORMATTER = "\n(Z-mate: %s)";

    private StringFormatUtils() {
        // Prevent instantiation
    }

    /**
     * Turns a pc set into an unordered set string representation
     *
     * @param set the pc set to be transformed into a string
     * @return    the pc set's original set in an appropriate string representation
     */
    public static String makeUnorderedSetStringRepresentation(PitchClassSet set) {
        return set == null || set.getCollection().size() == 0
                ? EMPTY
                : surroundStringSetWithBrackets(UNORDERED_SET_FORMATTER,
                    set.getCollection(),
                    true /* useHexString */);
    }

    /**
     * Turns a pc set into a prime form string representation
     *
     * @param set the pc set to be transformed into a string
     * @return    the set's prime form in an appropriate string representation
     */
    public static String makePrimeFormStringRepresentation(PitchClassSet set) {
        return set == null || set.getPrimeFormCollection().size() == 0
                ? PLACEHOLDER
                : surroundStringSetWithBrackets(PRIME_FORM_FORMATTER,
                    set.getPrimeFormCollection(),
                    true /* useHexString */);
    }

    /**
     * Turns a pc set into a normal form string representation
     *
     * @param set the pc set to be transformed into a string
     * @return    the set's normal form in an appropriate string representation
     */
    public static String makeNormalFormStringRepresentation(PitchClassSet set) {
        return set == null || set.getNormalFormCollection().size() == 0
                ? PLACEHOLDER
                : surroundStringSetWithBrackets(NORMAL_FORM_FORMATTER,
                    set.getNormalFormCollection(),
                    true /* useHexString */);
    }

    /**
     * Turns a pc set into an interval vector string representation
     *
     * @param set the pc set to be transformed into a string
     * @return    the set's interval vector in an appropriate string representation
     */
    public static String makeIntervalVectorStringRepresentation(PitchClassSet set) {
        return set == null || !ivHasAnyIntervals(set.getIntervalVector())
                ? PLACEHOLDER
                : surroundStringSetWithBrackets(INTERVAL_VECTOR_FORMATTER,
                    set.getIntervalVector(),
                    false /* useHexString */);
    }

    private static boolean ivHasAnyIntervals(List<Integer> iv) {
        for (int i : iv) {
            if (i != 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * A helper method to surround a string with a given pair of brackets
     *
     * @param brackets the brackets to enclose the string
     * @param list     the list of elements to be enclosed by brackets
     * @return         the string representation of the two parameters combined
     */
    private static String surroundStringSetWithBrackets(String brackets,
                                                        List<Integer> list,
                                                        final boolean useHexString) {
        return String.format(Locale.getDefault(),
                brackets,
                makeSpacesBetweenPitchClasses(list, useHexString));
    }

    /**
     * A helper method to make a string of a list with spaces in between each element
     *
     * @param list the list to be transformed into a string
     * @return     a string representation of list with spaces in between
     */
    private static String makeSpacesBetweenPitchClasses(List<Integer> list,
                                                        final boolean useHexString) {
        StringBuilder stringBuilder = new StringBuilder();

        if (list.size() > 0) {
            final int firstInt = list.get(0);
            stringBuilder.append(useHexString ? toHexString(firstInt) : firstInt);
            for (int i = 1; i < list.size(); i++) {
                final int ithInt = list.get(i);
                stringBuilder.append(SPACE).append(useHexString ? toHexString(ithInt) : ithInt);
            }
        }

        return stringBuilder.toString();
    }

    public static String makeForteNumberStringRepresentation(PitchClassSet set) {
        if (set == null || set.getForteNumber() == null) {
            return PLACEHOLDER;
        } else {
            final ForteNumber forteNumber = set.getForteNumber();
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(forteNumber.toString());
            if (forteNumber.isZedRelated()) {
                stringBuilder.append(String.format(Locale.getDefault(),
                        Z_MATE_FORMATTER,
                        IntervalVectorUtils.Z_MATES.get(forteNumber)));
            }
            return stringBuilder.toString();
        }
    }

    private static String toHexString(int i) {
        return Integer.toHexString(i).toUpperCase();
    }
}

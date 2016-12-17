package com.nihk.github.pcsetcalculator.utils;

import com.nihk.github.pcsetcalculator.models.ForteNumber;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;

import java.util.List;
import java.util.Locale;

/**
 * Created by Nick on 2016-12-04.
 */

public final class StringFormatUtils {
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
        return set == null || set.isEmpty()
                ? EMPTY
                : surroundStringSetWithBrackets(UNORDERED_SET_FORMATTER,
                    set.getCollection(),
                    true /* usesLetters */);
    }

    /**
     * Turns a pc set into a prime form string representation
     *
     * @param set the pc set to be transformed into a string
     * @return    the set's prime form in an appropriate string representation
     */
    public static String makePrimeFormStringRepresentation(PitchClassSet set) {
        return set == null || set.isEmpty()
                ? PLACEHOLDER
                : surroundStringSetWithBrackets(PRIME_FORM_FORMATTER,
                    set.getPrimeFormCollection(),
                    true /* usesLetters */);
    }

    // TODO refactor this entire class
    public static String makePrimeFormStringRepresentationNoSpaces(PitchClassSet set) {
        return makePrimeFormStringRepresentation(set).replaceAll(" ", "");
    }

    /**
     * Turns a pc set into a normal form string representation
     *
     * @param set the pc set to be transformed into a string
     * @return    the set's normal form in an appropriate string representation
     */
    public static String makeNormalFormStringRepresentation(PitchClassSet set) {
        return set == null || set.isEmpty()
                ? PLACEHOLDER
                : surroundStringSetWithBrackets(NORMAL_FORM_FORMATTER,
                    set.getNormalFormCollection(),
                    true /* usesLetters */);
    }

    /**
     * Turns a pc set into an interval vector string representation
     *
     * @param set the pc set to be transformed into a string
     * @return    the set's interval vector in an appropriate string representation
     */
    public static String makeIntervalVectorStringRepresentation(PitchClassSet set) {
        return set == null || set.isEmpty() || !set.hasAnyIntervals()
                ? PLACEHOLDER
                : surroundStringSetWithBrackets(INTERVAL_VECTOR_FORMATTER,
                    set.getIntervalVector(),
                    false /* usesLetters */);
    }

    // TODO refactor this entire class
    public static String makeIntervalVectorStringRepresentationNoSpaces(PitchClassSet set) {
        return makeIntervalVectorStringRepresentation(set).replaceAll(" ", "");
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
                                                        final boolean usesLetters) {
        return String.format(Locale.getDefault(),
                brackets,
                makeSpacesBetweenPitchClasses(list, usesLetters));
    }

    /**
     * A helper method to make a string of a list with spaces in between each element
     *
     * @param list the list to be transformed into a string
     * @return     a string representation of list with spaces in between
     */
    private static String makeSpacesBetweenPitchClasses(List<Integer> list,
                                                        final boolean usesLetters) {
        StringBuilder stringBuilder = new StringBuilder();

        if (list.size() > 0) {
            final int firstInt = list.get(0);
            stringBuilder.append(usesLetters ? intToStringBasedOnPrefs(firstInt) : firstInt);
            for (int i = 1; i < list.size(); i++) {
                final int ithInt = list.get(i);
                stringBuilder.append(SPACE).append(usesLetters ? intToStringBasedOnPrefs(ithInt) : ithInt);
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

    public static String intToStringBasedOnPrefs(int i) {
        final boolean isTandE = PreferencesUtils.isChecked(PreferencesUtils.KEY_T_AND_E);
        final char c;
        if (i == 10) {
            c = isTandE ? PreferencesUtils.T : PreferencesUtils.A;
        } else  if (i == 11) {
            c = isTandE ? PreferencesUtils.E : PreferencesUtils.B;
        } else {
            // int converted to String
            return String.valueOf(i);
        }
        // char converted to String
        return String.valueOf(c);
    }
}

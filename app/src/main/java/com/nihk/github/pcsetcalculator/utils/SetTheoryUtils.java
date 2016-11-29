package com.nihk.github.pcsetcalculator.utils;

/**
 * Created by Nick on 2016-11-02.
 */

import com.google.common.primitives.Ints;
import com.nihk.github.pcsetcalculator.models.NormalFormMetadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
// TODO make set to string, so that 10 == A, 11 == B, etc., with proper braces

// TODO method that converts view's input into set data

// TODO method that returns string for view when In or Tn is done

/**
 * Utility class for performing pitch class operations on sets. Although the
 * terms 'set' and 'collection' are typically used interchangeably when referring
 * to groups of pitch classes, in this class I generally refer to binary representations
 * as 'set' and List<Integer> representations as 'collection' to establish a distinction.
 */
public final class SetTheoryUtils {

    // Binary representations of pitch classes
    public static final Map<String, Integer> PC_BITS = new HashMap<String, Integer>() {{
        put("0", 0b000000000001);
        put("1", 0b000000000010);
        put("2", 0b000000000100);
        put("3", 0b000000001000);
        put("4", 0b000000010000);
        put("5", 0b000000100000);
        put("6", 0b000001000000);
        put("7", 0b000010000000);
        put("8", 0b000100000000);
        put("9", 0b001000000000);
        put("A", 0b010000000000);
        put("B", 0b100000000000);
    }};

    public static final int NUM_PITCH_CLASSES = 12;
    public static final int NUM_INTERVAL_CLASSES = 6;
    // A mask of all bits to the left of the twelfth bit set to true; all others false
    private static final int OVERFLOW_MASK = ~0 << (NUM_PITCH_CLASSES);
    // A mask of all bits from the twelfth bit below set to true; all others false
    private static final int MOD_12_MASK = ~OVERFLOW_MASK;
    private static final int NUM_NON_MOD_12_BITS = Integer.bitCount(OVERFLOW_MASK);
    public static final int INTERVAL_VECTOR_LOG_BASE = 2;

    private static final String UNORDERED_SET_FORMATTER = "{%s}";
    private static final String NORMAL_FORM_FORMATTER = "[%s]";
    private static final String PRIME_FORM_FORMATTER = "(%s)";
    private static final String INTERVAL_VECTOR_FORMATTER = "<%s>";

    public static Set<Integer> PRIME_FORMS = ForteNumberUtils.BIMAP.keySet();

    private SetTheoryUtils() {
        // Prevent instantiation
    }

    /**
     * Inverts a collection by reversing its bits then right-packing them so there
     * are no trailing zeroes.
     *
     * @param set the collection of pitch classes
     * @return    an inversion of the collection
     */
    public static int invert(int set) {
        return Integer.reverse(set) >>> Integer.numberOfLeadingZeros(set);
    }

    /**
     * Inverts a pitch class (non-binary) collection around mod 12
     *
     * @param collection the set of pitch classes
     */
    public static void invert(List<Integer> collection) {
        for (int i = 0; i < collection.size(); i++) {
            int inverted = invertPc(collection.get(i));
            collection.set(i, inverted);
        }
    }

    /**
     * Inverts a single pitch class (non-binary representation). NB: does not perform any transposition
     * afterwards; this is therefore just an I0 operation.
     *
     * @param pc the pitch class
     * @return   the pitch class inverted around a mod 12 pitch-space
     */
    public static int invertPc(int pc) {
        if (pc == 0) {
            return 0;
        }

        return NUM_PITCH_CLASSES - mod12(pc);
    }

    /**
     * Transposes a binary representation of a pitch class collection.
     * NB: using this method will not preserve the order of normal or prime form.
     *
     * @param set           the pitch class collection
     * @param transposition the Tn value that each member of the set will be transposed
     * @return              the transposed version of the set (in mod 12)
     */
    public static int transpose(int set, int transposition) {
        int transposedCollection = set << mod12(transposition);
        return mod12Binary(transposedCollection);
    }

    /**
     * Transposes each member of a collection by a given amount.
     * This transposes a list of integers, i.e. not a binary representation
     * of the set; compare to SetTheoryUtils::transpose
     *
     * @param collection    the collection to be transposed
     * @param transposition the Tn value
     */
    public static void transpose(List<Integer> collection, int transposition) {
        for (int i = 0; i < collection.size(); i++) {
            int transposed = transposePc(collection.get(i), transposition);
            collection.set(i, transposed);
        }
    }

    /**
     * Transposes a single pitch class
     *
     * @param pc            the pitch class
     * @param transposition the Tn value to transpose the pitch class
     * @return              the transposed version of the pitch class
     */
    public static int transposePc(int pc, int transposition) {
        return mod12(pc + transposition);
    }

    /**
     * Effectively performs an In operation; that is, inverts then transposes
     * all members of a pitch class collection
     *
     * @param collection    the pitch class collection
     * @param transposition the value by which the collection will be transposed after inversion
     */
    public static void invertThenTranspose(List<Integer> collection, int transposition) {
        invert(collection);
        transpose(collection, transposition);
    }

    /**
     * Returns the complement of a pitch class set
     *
     * @param set the pitch class set
     * @return    the complement of the set
     */
    public static int complement(int set) {
        return ~set & MOD_12_MASK;
    }

    /**
     * Parses a string representation of a pitch class collection and converts
     * it into a binary representation
     * @param s the pitch class collection as a string
     * @return  the pitch class collection as an integer
     */
    public static int stringToSet(String s) {
        int binaryCollection = 0;
        char[] pcs = s.trim().toUpperCase().toCharArray();

        for (char c : pcs) {
            // Convert hex char to int
            int pcNumber = Integer.decode(String.format("0x%c", c));
            binaryCollection |= 1 << pcNumber;
        }

        return binaryCollection;
    }

    /**
     * Converts a binary representation of a pitch class set into
     * a string representation
     *
     * @param set the pitch class set
     * @return    a string version of set
     */
    public static String setToString(int set) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < NUM_PITCH_CLASSES; i++) {
            int pc = 1 << i;
            if ((set & pc) != 0) {
                stringBuilder.append(Integer.toHexString(i));
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Converts the binary representation of a pitch class collection into
     * a list of integers. NB: PCs A and B will necessarily be represented
     * as 10 and 11, respectively, in the list.
     *
     * @param set the binary representation of a pc collection
     * @return    a list of integers representing the pcs found in the
     *            binary collection
     */
    public static List<Integer> setToList(int set) {
        List<Integer> collection = new ArrayList<>();

        for (int i = 0; i < NUM_PITCH_CLASSES; i++) {
            int pitchClass = 1 << i;
            if ((set & pitchClass) != 0) {
                // Since PCs are zero based, the number of trailing zero bits
                // a lone PC has in binary equates to its PC number value
                int pcNumber = Integer.numberOfTrailingZeros(pitchClass);
                collection.add(pcNumber);
            }
        }

        return collection;
    }

    /**
     * Turns a list of strings into an unordered set string representation
     *
     * @param set the list of elements to be transformed into a string
     * @return    the list in an unordered set string representation
     */
    public static String getUnorderedSetStringRepresentation(List<String> set) {
        return surroundStringSetWithBrackets(UNORDERED_SET_FORMATTER, set);
    }

    /**
     * Turns a list of strings into a prime form string representation
     *
     * @param set the list of elements to be transformed into a string
     * @return    the list in a prime form string representation
     */
    public static String getPrimeFormStringRepresentation(List<String> set) {
        return surroundStringSetWithBrackets(PRIME_FORM_FORMATTER, set);
    }

    /**
     * Turns a list of strings into a normal form string representation
     *
     * @param set the list of elements to be transformed into a string
     * @return    the list in a normal form string representation
     */
    public static String getNormalFormStringRepresentation(List<String> set) {
        return surroundStringSetWithBrackets(NORMAL_FORM_FORMATTER, set);
    }

    /**
     * Turns a list of strings into an interval vector string representation
     *
     * @param iv the list of elements to be transformed into a string
     * @return   the list in an interval vector string representation
     */
    public static String getIntervalVectorStringRepresentation(List<String> iv) {
        return surroundStringSetWithBrackets(INTERVAL_VECTOR_FORMATTER, iv);
    }

    /**
     * A helper method to surround a string with a given pair of brackets
     *
     * @param brackets the brackets to enclose the string
     * @param list     the list of elements to be enclosed by brackets
     * @return         the string representation of the two parameters combined
     */
    private static String surroundStringSetWithBrackets(String brackets, List<String> list) {
        return String.format(Locale.getDefault(),
                brackets,
                makeSpacesBetweenPitchClasses(list));
    }

    /**
     * A helper method to make a string of a list with spaces in between each element
     *
     * @param list the list to be transformed into a string
     * @return     a string representation of list with spaces in between
     */
    private static String makeSpacesBetweenPitchClasses(List<String> list) {
        char space = ' ';
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            stringBuilder.append(space).append(list.get(i));
        }

        return stringBuilder.toString();
    }

    /**
     * Calculates the prime form of a pitch class collection, which is either the normal form of
     * itself or its inversion (whichever is more tightly packed), transposed to zero.
     *
     * @param set the collection of pitch classes
     * @return    the prime form of the collection as an integer
     */
    public static int calculatePrimeForm(int set) {
        int revCollection = invert(set);
        return Math.min(calculateNormalForm(set).getZeroBasedNormalForm(),
                calculateNormalForm(revCollection).getZeroBasedNormalForm());
    }

    /**
     * Calculates the normal form of a collection
     *
     * @param set the collection of pitch classes
     * @return    a NormalFormMetadata object holding necessary fields
     *            to assemble the actual normal form. A binary set cannot
     *            represent normal form properly, so this object solves
     *            that deficiency
     */
    public static NormalFormMetadata calculateNormalForm(int set){
        // min ultimately becomes the zero based normal form and thus one of two candidates for prime form
        // (the other being the zero based normal form of collection inverted. See calculatePrimeForm())
        int min = set;
        int numShifts = 0;
        // Since this method calculates the zero based normal form, this value becomes the Tn needed
        // to bring the set back to its original pitch classes while preserving normal form
        int numShiftsForMin = 0;
        int setSize = Integer.bitCount(set);
        // A mask of all bits unset except the first
        int firstBit = 1;
        // A value used to shift the twelfth bit into the first bit position (to facilitate left rotation by 1
        // in a mod 12 bit-space)
        int rightShiftAmount = NUM_PITCH_CLASSES - 1;

        for (int i = 0; i < setSize; i++) {
            // First rotate (or transpose in a mod 12 pitch class space) leftward until the zero bit is set.
            // This Tn value is how many leftward rotations around mod 12 will be needed to have that zero bit set.
            // The + 1 is to wrap the twelfth bit around to become the zeroth bit
            int transposition = Integer.numberOfLeadingZeros(set) - NUM_NON_MOD_12_BITS + 1;
            set = transpose(set, transposition);
            numShifts += transposition;

            if (set < min) {
                // Store the mod 12 complement of the number of shifts. This will become the transposition value in the
                // returned NormalFormMetadata object. The complement is used because we're rotating leftward rather
                // than rightward
                numShiftsForMin = NUM_PITCH_CLASSES - numShifts;
                min = set;
            }

            // Rotate left by one in a mod 12 space to set up the next loop iteration
            set = set << 1 | (set & firstBit) >> rightShiftAmount;
            numShifts++;
        }

        return new NormalFormMetadata(min, numShiftsForMin);
    }

    /**
     * Converts any integer into its mod 12 equivalent.
     *
     * @param n This value could be anything, e.g. a pitch class, a Tn
     *          NB: not a (binary) pc collection, just a single integer representation of a pitch class
     * @return  n converted to mod 12
     */
    public static int mod12(int n) {
        return modX(n, NUM_PITCH_CLASSES);
    }

    /**
     * Helper method for mod 12 and potentially any other universe size
     * The initial loop is to circumvent any potential negative values for n
     *
     * @param n            this value could be anything, e.g. a pitch class, a Tn
     * @param universeSize the size of the universe
     * @return             n adjusted to fit the universe size
     */
    private static int modX(int n, int universeSize) {
        while (n < 0) {
            n += universeSize;
        }

        return n % universeSize;
    }

    /**
     * Shifts all bits outside the mod 12 bit-space rightward until
     * all set bits are within that mod 12 space
     *
     * @param set the pitch class set
     * @return    the pitch class set in mod 12 (binary)
     */
    public static int mod12Binary(int set) {
        int leadingZeroes = Integer.numberOfLeadingZeros(set);
        int overflowBits;

        // A loop that keeps shifting overflow bits rightward by 12 until they are all nestled within mod 12
        while ((overflowBits = (set & OVERFLOW_MASK)) != 0
                && leadingZeroes < NUM_NON_MOD_12_BITS) {

            // Send any overflow bits rightward by 12 and add it to the original set
            set |= overflowBits >>> NUM_PITCH_CLASSES;

            // Update what the new number of leading zeroes should be
            leadingZeroes += NUM_PITCH_CLASSES;

            // Make a mask to realize the new number of leading zeroes
            int leadingZeroesMask = ~0 >>> leadingZeroes;

            // If there will still be overflow bits after the first mod 12 shift, i.e. the new leading zeroes
            // value will be less than the number of non mod 12 bits, then use the leading zeroes mask. Otherwise,
            // use the mod 12 mask because the leading zeroes mask most likely will have spilled into the mod 12
            // bit-space
            set &= leadingZeroes <= NUM_NON_MOD_12_BITS
                    ? leadingZeroesMask
                    : MOD_12_MASK;
        }

        return set;
    }

    /**
     * Calculates the intervallic content of a set
     *
     * @param set the binary representation of a pitch class set
     * @return    an integer array representing the eet's interval vector
     */
    public static List<Integer> calculateIntervalVector(int set) {
        int[] intervalVector = new int[NUM_INTERVAL_CLASSES];
        int setSize = Integer.bitCount(set);

        for (int i = 0; i < setSize; i++) {
            // First shift the set rightwards until it's zero-based
            set = set >>> Integer.numberOfTrailingZeros(set);
            // Gets the highest bit position using Log base 2
            int mostSignificantBitPosition = (int)(Math.log(Integer.highestOneBit(set))
                    / Math.log(INTERVAL_VECTOR_LOG_BASE));

            for (int j = 1; j <= mostSignificantBitPosition; j++) {
                if ((set & (1 << j)) != 0) {
                    // - 1 because arrays are zero based, so an interval of 1 should fill
                    // the first index of the array, 0
                    intervalVector[calculateIntervalClass(j) - 1]++;
                }
            }

            // Shift once to the right to pop off the zeroth bit now that we're done with it
            set >>>= 1;
        }

        return Ints.asList(intervalVector);
    }

    /**
     * Calculates the interval class of an interval. There are only
     * six interval classes: 1-6
     *
     * @param interval an value representing the distance between two pitch classes
     * @return         the interval class value of the interval parameter
     */
    public static int calculateIntervalClass(int interval) {
        interval = mod12(interval);
        return interval <= 6
                ? interval
                : NUM_PITCH_CLASSES - interval;
    }

    /**
     * Set X is the abstract superset of Set Y if any transposed or inverted form of Y is
     * contained in X
     *
     * @param set               the pitch class set
     * @param supersetCandidate a pitch class set which is potentially a superset of set
     * @return                  whether supersetCandidate was indeed an abstract superset of set
     */
    public static boolean isAbstractSuperset(int set, int supersetCandidate) {
        // Only consider proper supersets
        if (Integer.bitCount(supersetCandidate) <= Integer.bitCount(set)) {
            return false;
        }

        int invertedSet = invert(set);

        return isSupersetOfAnyTransposition(set, supersetCandidate)
                || isSupersetOfAnyTransposition(invertedSet, supersetCandidate);
    }

    /**
     * A helper for isAbstractSuperset() which determines if supersetCandidate
     * is indeed a superset of set for any transposition of set
     *
     * @param set               the pitch class set
     * @param supersetCandidate a pitch class set which is potentially a superset of set
     * @return                  whether supersetCandidate was indeed an superset of any
     *                          transposition of set
     */
    private static boolean isSupersetOfAnyTransposition(int set, int supersetCandidate) {
        for (int i = 0; i < NUM_PITCH_CLASSES; i++) {
            if ((set & supersetCandidate) == set) {
                return true;
            }

            set = transpose(set, 1);
        }

        return false;
    }

    /**
     * Set X is the abstract subset of Set Y if any transposed or inverted form of X is
     * contained in Y
     *
     * @param set             the pitch class set
     * @param subsetCandidate a pitch class set which is potentially a subset of set
     * @return                whether subsetCandidate was indeed an abstract subset of set
     */
    public static boolean isAbstractSubset(int set, int subsetCandidate) {
        return isAbstractSuperset(subsetCandidate, set);
    }

    /**
     * Set X is a literal superset of Set Y if all of the notes of Y are contained in X.
     *
     * @param set               the pitch class set
     * @param supersetCandidate a pitch class set which is potentially a superset of set
     * @return                  whether supersetCandidate was indeed a literal superset of set
     */
    public static boolean isLiteralSuperset(int set, int supersetCandidate) {
        // Only consider proper supersets
        return Integer.bitCount(supersetCandidate) > Integer.bitCount(set)
                && (set & supersetCandidate) == set;
    }

    /**
     * Set X is a literal subset of Set Y if all of the notes of X are contained in Y.
     *
     * @param set             the pitch class set
     * @param subsetCandidate a pitch class set which is potentially a subset of set
     * @return                whether subsetCandidate was indeed a literal subset of set
     */
    public static boolean isLiteralSubset(int set, int subsetCandidate) {
        return isLiteralSuperset(subsetCandidate, set);
    }

    /**
     * Calculates the common pitch classes between two sets, through a binary representation
     *
     * @param set1 the first pitch class set
     * @param set2 the second pitch class set
     * @return     a pitch class set of common tones between the first and second arguments
     */
    public static int getCommonTones(int set1, int set2) {
        return set1 & set2;
    }

    /**
     * Joins two pitch class sets into one
     *
     * @param set1 the first pitch class set
     * @param set2 the second pitch class set
     * @return     the composition of the first and second pitch class sets as one set
     */
    public static int combine(int set1, int set2) {
        return set1 | set2;
    }

    /**
     * Joins a set and a transposed version of itself
     *
     * @param set           the pitch class set
     * @param transposition the value by which set should be transposed
     * @return              the composition of set and its transposition
     */
    public static int transpositionallyCombine(int set, int transposition) {
        return combine(set, transpose(set, transposition));
    }

    /**
     * Joins a set and a inversionally transposed version of itself
     *
     * @param set           the pitch class set
     * @param transposition the value by which set should be transposed after inversion
     * @return              the composition of set and its inversional transposition
     */
    public static int inversionallyCombine(int set, int transposition) {
        int invertedSet = invert(set);
        return combine(invertedSet, transpose(invertedSet, transposition));
    }
}

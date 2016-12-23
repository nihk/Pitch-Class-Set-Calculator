package com.nihk.github.pcsetcalculator.utils;

import android.util.SparseArray;

import com.nihk.github.pcsetcalculator.models.ForteNumber;
import com.nihk.github.pcsetcalculator.models.PitchClassSet;
import com.nihk.github.pcsetcalculator.models.SetListChild;
import com.nihk.github.pcsetcalculator.models.SetListParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 2016-12-11.
 */

public final class SetListUtils {
    private SetListUtils() {
        // Prevent instantiation
    }

    // TODO move to string resources
    private static final SetListParent TRICHORDS_PARENT = new SetListParent("Trichords (3)");
    private static final SetListParent TETRACHORDS_PARENT = new SetListParent("Tetrachords (4)");
    private static final SetListParent PENTACHORDS_PARENT = new SetListParent("Pentachords (5)");
    private static final SetListParent HEXACHORDS_PARENT = new SetListParent("Hexachords (6)");
    private static final SetListParent SEPTACHORDS_PARENT = new SetListParent("Septachords (7)");
    private static final SetListParent OCTACHORDS_PARENT = new SetListParent("Octachords (8)");
    private static final SetListParent NONACHORDS_PARENT = new SetListParent("Nonachords (9)");

    public static final List<SetListParent> PARENTS = new ArrayList<SetListParent>() {{
        add(TRICHORDS_PARENT);
        add(TETRACHORDS_PARENT);
        add(PENTACHORDS_PARENT);
        add(HEXACHORDS_PARENT);
        add(SEPTACHORDS_PARENT);
        add(OCTACHORDS_PARENT);
        add(NONACHORDS_PARENT);
    }};

    private static final List<SetListChild> TRICHORDS_CHILDREN = new ArrayList<>();
    private static final List<SetListChild> TETRACHORDS_CHILDREN = new ArrayList<>();
    private static final List<SetListChild> PENTACHORDS_CHILDREN = new ArrayList<>();
    private static final List<SetListChild> HEXACHORDS_CHILDREN = new ArrayList<>();
    private static final List<SetListChild> SEPTACHORDS_CHILDREN = new ArrayList<>();
    private static final List<SetListChild> OCTACHORDS_CHILDREN = new ArrayList<>();
    private static final List<SetListChild> NONACHORDS_CHILDREN = new ArrayList<>();

    private static final int TRICHORDS_CARDINALITY = 3;
    private static final int TETRACHORDS_CARDINALITY = 4;
    private static final int PENTACHORDS_CARDINALITY = 5;
    private static final int HEXACHORDS_CARDINALITY = 6;
    private static final int SEPTACHORDS_CARDINALITY = 7;
    private static final int OCTACHORDS_CARDINALITY = 8;
    private static final int NONACHORDS_CARDINALITY = 9;

    private static final SparseArray<List<SetListChild>> SET_LIST_CHILDREN = new SparseArray<List<SetListChild>>() {{
        put(TRICHORDS_CARDINALITY, TRICHORDS_CHILDREN);
        put(TETRACHORDS_CARDINALITY, TETRACHORDS_CHILDREN);
        put(PENTACHORDS_CARDINALITY, PENTACHORDS_CHILDREN);
        put(HEXACHORDS_CARDINALITY, HEXACHORDS_CHILDREN);
        put(SEPTACHORDS_CARDINALITY, SEPTACHORDS_CHILDREN);
        put(OCTACHORDS_CARDINALITY, OCTACHORDS_CHILDREN);
        put(NONACHORDS_CARDINALITY, NONACHORDS_CHILDREN);
    }};

    static {
        for (ForteNumber forteNumber : ForteNumberUtils.BIMAP.values()) {
            final PitchClassSet pitchClassSet = PitchClassSet.fromForte(forteNumber);
            final String primeForm = StringFormatUtils.makePrimeFormStringRepresentation(pitchClassSet, false /* withSpaces */);
            final String intervalVector = StringFormatUtils.makeIntervalVectorStringRepresentation(pitchClassSet, false /* withSpaces */);

            final SetListChild setListChild = new SetListChild(primeForm, forteNumber.toString(),
                    intervalVector, String.valueOf(pitchClassSet.getTranspositionalSymmetry()),
                    String.valueOf(pitchClassSet.getInversionalSymmetry()));

            final List<SetListChild> setListChildren = SET_LIST_CHILDREN.get(forteNumber.getCardinality());
            setListChildren.add(setListChild);
        }

        TRICHORDS_PARENT.setChildItemList(SET_LIST_CHILDREN.get(TRICHORDS_CARDINALITY));
        TETRACHORDS_PARENT.setChildItemList(SET_LIST_CHILDREN.get(TETRACHORDS_CARDINALITY));
        PENTACHORDS_PARENT.setChildItemList(SET_LIST_CHILDREN.get(PENTACHORDS_CARDINALITY));
        HEXACHORDS_PARENT.setChildItemList(SET_LIST_CHILDREN.get(HEXACHORDS_CARDINALITY));
        SEPTACHORDS_PARENT.setChildItemList(SET_LIST_CHILDREN.get(SEPTACHORDS_CARDINALITY));
        OCTACHORDS_PARENT.setChildItemList(SET_LIST_CHILDREN.get(OCTACHORDS_CARDINALITY));
        NONACHORDS_PARENT.setChildItemList(SET_LIST_CHILDREN.get(NONACHORDS_CARDINALITY));
    }
}

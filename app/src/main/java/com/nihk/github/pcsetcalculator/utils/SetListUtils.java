package com.nihk.github.pcsetcalculator.utils;

import com.nihk.github.pcsetcalculator.models.SetListChild;
import com.nihk.github.pcsetcalculator.models.SetListParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 2016-12-11.
 */

public class SetListUtils {
    public static final List<SetListParent> PARENTS = new ArrayList<SetListParent>() {{
        add(new SetListParent("Trichords"));
        add(new SetListParent("Tetrachords"));
        add(new SetListParent("Pentachords"));
        add(new SetListParent("Hexachords"));
        add(new SetListParent("Septachords"));
        add(new SetListParent("Octachords"));
        add(new SetListParent("Nonachords"));
    }};

    private static final List<SetListChild> CHILDREN = new ArrayList<SetListChild>() {{
        add(new SetListChild("(012)", "3-1", "<120000>", "1", "0"));
        add(new SetListChild("(012)", "3-1", "<120000>", "1", "0"));
        add(new SetListChild("(012)", "3-1", "<120000>", "1", "0"));
    }};

    static {
        for (SetListParent parent : PARENTS) {
            parent.setChildItemList(CHILDREN);
        }
    }
}

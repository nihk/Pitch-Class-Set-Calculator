package com.nihk.github.pcsetcalculator.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Nick on 2016-12-16.
 */

public class CustomRecyclerView extends RecyclerView {
    private static final String TAG = CustomRecyclerView.class.getSimpleName();

    public CustomRecyclerView(final Context context) {
        super(context);
    }

    public CustomRecyclerView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(final Context context, @Nullable final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    // fragment_listofsetclasses.xml uses android:animateLayoutChanges="true" which calls this method
    // and can throw an exception in some Ice Cream Sandwich implementations. This overrides that
    // chance for a thrown exception.
    @Override
    public void scrollTo(int x, int y) {
        // Do nothing, or alternatively uncomment the Log below.
        // Log.w(TAG, "CustomRecyclerView does not support scrolling to an absolute position.");
    }
}

package com.nihk.github.pcsetcalculator.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.SetListChild;
import com.nihk.github.pcsetcalculator.models.SetListParent;

import java.util.List;

/**
 * Created by Nick on 2016-12-11.
 */

public class SetListExpandableAdapter extends ExpandableRecyclerAdapter<SetListParent, SetListChild, SetListParentViewHolder, SetListChildViewHolder>
        implements SetListChildViewHolder.Listener {
    private final LayoutInflater mInflater;
    private Listener mListener;

    public interface Listener {
        void onSetListItemClicked(String forteNumber);
    }

    public void setOnSetListItemClicked(Listener listener) {
        mListener = listener;
    }

    public SetListExpandableAdapter(final Context context, @NonNull final List<SetListParent> parentItemList) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @UiThread
    @NonNull
    @Override
    public SetListParentViewHolder onCreateParentViewHolder(@NonNull final ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_parent_cardinality, viewGroup, false /* attachToRoot */);
        return new SetListParentViewHolder(view);
    }

    @UiThread
    @NonNull
    @Override
    public SetListChildViewHolder onCreateChildViewHolder(@NonNull final ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_child_setclass, viewGroup, false);
        final SetListChildViewHolder viewHolder = new SetListChildViewHolder(view);
        viewHolder.setListener(this);
        return viewHolder;
    }

    @Override
    public void onSetListItemClicked(final String forteNumber) {
        mListener.onSetListItemClicked(forteNumber);
    }

    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull final SetListParentViewHolder setListParentViewHolder,
                                       final int parentPosition,
                                       @NonNull final SetListParent setListParent) {
        setListParentViewHolder.bind(setListParent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull final SetListChildViewHolder setListChildViewHolder,
                                      final int parentPosition,
                                      final int childPosition,
                                      @NonNull final SetListChild setListChild) {
        setListChildViewHolder.bind(setListChild);
    }
}

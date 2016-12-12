package com.nihk.github.pcsetcalculator.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.utils.SetListUtils;

/**
 * Created by Nick on 2016-11-27.
 */

public class SetListFragment extends Fragment implements ExpandableRecyclerAdapter.ExpandCollapseListener {
    private RecyclerView mRecyclerView;
    private SetListExpandableAdapter mExpandableAdapter;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listofsetclasses, container, false /* attachToRoot */);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.setlist_recyclerView);
        SetListExpandableAdapter adapter = new SetListExpandableAdapter(getActivity(), SetListUtils.PARENTS);
        mRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onParentExpanded(final int parentPosition) {

    }

    @Override
    public void onParentCollapsed(final int parentPosition) {

    }
}

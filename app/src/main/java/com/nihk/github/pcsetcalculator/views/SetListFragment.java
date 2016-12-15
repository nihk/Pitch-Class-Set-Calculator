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
import com.nihk.github.pcsetcalculator.models.SetListParent;
import com.nihk.github.pcsetcalculator.utils.SetListUtils;

import java.util.List;

/**
 * Created by Nick on 2016-11-27.
 */

public class SetListFragment extends Fragment implements SetListExpandableAdapter.Listener,
        ExpandableRecyclerAdapter.ExpandCollapseListener {
    private static final String KEY_EXPANDED_STATES = "expandedStates";
    private boolean[] mExpandedStates;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listofsetclasses, container, false /* attachToRoot */);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.setlist_recyclerView);

        final List<SetListParent> parents = SetListUtils.PARENTS;
        if (savedInstanceState != null) {
            mExpandedStates = savedInstanceState.getBooleanArray(KEY_EXPANDED_STATES);
            if (mExpandedStates != null && parents.size() == mExpandedStates.length) {
                for (int i = 0; i < mExpandedStates.length; i++) {
                    final SetListParent parent = parents.get(i);
                    parent.setInitiallyExpanded(mExpandedStates[i]);
                }
            }
        } else {
            mExpandedStates = new boolean[parents.size()];
        }
        final SetListExpandableAdapter adapter = new SetListExpandableAdapter(getActivity(), parents);
        adapter.setListener(this);
        adapter.setExpandCollapseListener(this);
        recyclerView.setAdapter(adapter);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onSetListItemClicked(final String forteNumber) {
        ViewPagerFragment viewPagerFragment = (ViewPagerFragment) getParentFragment();
        viewPagerFragment.onSetListItemClicked(forteNumber);
    }

    @Override
    public void onParentExpanded(final int parentPosition) {
        mExpandedStates[parentPosition] = true;
    }

    @Override
    public void onParentCollapsed(final int parentPosition) {
        mExpandedStates[parentPosition] = false;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBooleanArray(KEY_EXPANDED_STATES, mExpandedStates);
    }
}

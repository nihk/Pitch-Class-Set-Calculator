package com.nihk.github.pcsetcalculator.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.nihk.github.pcsetcalculator.R;
import com.nihk.github.pcsetcalculator.models.SetListParent;
import com.nihk.github.pcsetcalculator.utils.SetListUtils;

/**
 * Created by Nick on 2016-11-27.
 */

public class SetListFragment extends Fragment implements SetListExpandableAdapter.Listener,
        ExpandableRecyclerAdapter.ExpandCollapseListener {
    private static final String KEY_EXPANDED_STATES = "expandedStates";
    private boolean[] mExpandedStates;
    private LinearLayout mKeyLayout;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_listofsetclasses, container, false /* attachToRoot */);
        mKeyLayout = (LinearLayout) view.findViewById(R.id.list_item_child_key_layout);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.setlist_recyclerView);

        setupExpandedStates(savedInstanceState);
        final boolean isAnyParentExpanded = isAnyParentExpanded();
        mKeyLayout.setVisibility(isAnyParentExpanded
                ? View.VISIBLE
                : View.GONE);

        final SetListExpandableAdapter adapter = new SetListExpandableAdapter(getActivity(), SetListUtils.PARENTS);
        adapter.setOnSetListItemClicked(this);
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
        mKeyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onParentCollapsed(final int parentPosition) {
        mExpandedStates[parentPosition] = false;
        if (!isAnyParentExpanded()) {
            mKeyLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBooleanArray(KEY_EXPANDED_STATES, mExpandedStates);
    }

    private void setupExpandedStates(@Nullable final Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mExpandedStates = savedInstanceState.getBooleanArray(KEY_EXPANDED_STATES);
            if (mExpandedStates != null && SetListUtils.PARENTS.size() == mExpandedStates.length) {
                for (int i = 0; i < mExpandedStates.length; i++) {
                    final SetListParent parent = SetListUtils.PARENTS.get(i);
                    parent.setInitiallyExpanded(mExpandedStates[i]);
                }
            }
        } else {
            mExpandedStates = new boolean[SetListUtils.PARENTS.size()];
        }
    }

    private boolean isAnyParentExpanded() {
        for (boolean isExpanded : mExpandedStates) {
            if (isExpanded) {
                return true;
            }
        }

        return false;
    }
}

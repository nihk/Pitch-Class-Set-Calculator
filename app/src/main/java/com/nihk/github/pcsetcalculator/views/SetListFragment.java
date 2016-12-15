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

public class SetListFragment extends Fragment implements SetListExpandableAdapter.Listener {
    private RecyclerView mRecyclerView;
    private SetListExpandableAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Listener mListener;

    private static final String KEY_SET_LIST_FRAGMENT = "setListFragment";

    public interface Listener {
        void onSetListItemClicked(String forteNumber);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listofsetclasses, container, false /* attachToRoot */);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.setlist_recyclerView);
        // TODO
//        if (savedInstanceState == null) {
//            mTestDataItemList = setUpTestData(NUM_TEST_DATA_ITEMS);
//        } else {
//            mTestDataItemList = (ArrayList<HorizontalParent>) savedInstanceState.getSerializable(SAVED_TEST_DATA_ITEM_LIST);
//        }
        mAdapter = new SetListExpandableAdapter(getActivity(), SetListUtils.PARENTS);
        mAdapter.setListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    // todo on save instance state and on restore

    @Override
    public void onSetListItemClicked(final String forteNumber) {
        mListener.onSetListItemClicked(forteNumber);
    }
}

package com.batch.mcs.finalproject.views;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.adapters.FeedEventListAdapter;
import com.batch.mcs.finalproject.databinding.FragmentCalendarFeedBinding;
import com.batch.mcs.finalproject.databinding.FragmentFeedBinding;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;

import java.util.List;

public class FeedFragment extends Fragment {

    RecyclerView recyclerView;
    FeedEventListAdapter feedEventListAdapter;

    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment getInstance() {
        FeedFragment fragment = new FeedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFeedBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false);
        final AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        fragmentBinding.svSearchLayout.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(feedEventListAdapter!=null){
                    feedEventListAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(feedEventListAdapter!=null){
                    feedEventListAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        recyclerView = fragmentBinding.rvDisplayFeed;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);

        appViewModel.getLiveEventAll().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                setupRecyclerView(events);
            }
        });

        return fragmentBinding.getRoot();
    }

    private void setupRecyclerView(List<Event> events) {
        feedEventListAdapter = new FeedEventListAdapter(events);
        recyclerView.setAdapter(feedEventListAdapter);
        feedEventListAdapter.notifyDataSetChanged();
    }

}

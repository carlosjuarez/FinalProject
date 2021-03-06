package com.batch.mcs.finalproject.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.adapters.CalendarFeedEventListAdapter;
import com.batch.mcs.finalproject.databinding.FragmentGroupCalendarFeedBinding;
import com.batch.mcs.finalproject.helperobjects.SelectDate;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.viewmodel.GroupViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class GroupCalendarFeedDisplayFragment extends Fragment {
    RecyclerView recyclerView;
    CalendarFeedEventListAdapter calendarFeedEventListAdapter;


    public GroupCalendarFeedDisplayFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentGroupCalendarFeedBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_calendar_feed, container, false);
        final GroupViewModel groupviewModel = ViewModelProviders.of(getActivity()).get(GroupViewModel.class);

        recyclerView = fragmentBinding.rvCalendardisplayFeed;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);

        groupviewModel.getLiveEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                setupRecyclerView(events);
            }
        });

        groupviewModel.getSelectDateFilter().observe(this, new Observer<SelectDate>() {
            @Override
            public void onChanged(@Nullable SelectDate selectDate) {
                calendarFeedEventListAdapter.getFilter().filter(selectDate.getDate());
            }
        });

        return fragmentBinding.getRoot();
    }

    private void setupRecyclerView(List<Event> events) {
        calendarFeedEventListAdapter = new CalendarFeedEventListAdapter(events);
        recyclerView.setAdapter(calendarFeedEventListAdapter);
        calendarFeedEventListAdapter.notifyDataSetChanged();
    }
}

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
import com.batch.mcs.finalproject.databinding.FragmentCalendarFeedBinding;
import com.batch.mcs.finalproject.helperobjects.SelectDate;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFeedFragment extends BaseFragment {

    RecyclerView recyclerView;
    CalendarFeedEventListAdapter calendarFeedEventListAdapter;


    public CalendarFeedFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentCalendarFeedBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar_feed, container, false);
        final AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        recyclerView = fragmentBinding.rvCalendardisplayFeed;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);

        appViewModel.getLiveEventAll().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                setupRecyclerView(events);
            }
        });

        appViewModel.getSelectDateFilter().observe(this, new Observer<SelectDate>() {
            @Override
            public void onChanged(@Nullable SelectDate selectDate) {
                if(selectDate!=null && calendarFeedEventListAdapter!=null){
                    calendarFeedEventListAdapter.getFilter().filter(selectDate.getDate());
                }
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

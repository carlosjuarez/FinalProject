package com.batch.mcs.finalproject.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.batch.mcs.finalproject.BaseFragment;
import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.FragmentCalendarEventsBinding;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarEventsFragment extends BaseFragment {

    public CalendarEventsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCalendarEventsBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar_events,container,false);
        final AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);



        appViewModel.getLiveEventAll().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                //setupRecyclerView(events);
            }
        });

        return fragmentBinding.getRoot();
    }

}

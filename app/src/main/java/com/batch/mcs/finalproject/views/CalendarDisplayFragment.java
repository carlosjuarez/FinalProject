package com.batch.mcs.finalproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.BaseFragment;
import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.FragmentCalendarBinding;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarDisplayFragment extends BaseFragment {

    public CalendarDisplayFragment() {
    }

    public static CalendarDisplayFragment getInstance(){
        CalendarDisplayFragment calendarDisplayFragment = new CalendarDisplayFragment();
        return calendarDisplayFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCalendarBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar,container,false);

        AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        //appViewModel.initUserChats();

        return fragmentBinding.getRoot();
    }

}

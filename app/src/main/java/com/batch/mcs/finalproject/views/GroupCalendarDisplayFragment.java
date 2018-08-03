package com.batch.mcs.finalproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.FragmentGroupCalendarBinding;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;

public class GroupCalendarDisplayFragment extends BaseFragment {

    public static GroupCalendarDisplayFragment getInstance(){
        GroupCalendarDisplayFragment calendarDisplayFragment = new GroupCalendarDisplayFragment();
        return calendarDisplayFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGroupCalendarBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_calendar,container,false);
        AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        return fragmentBinding.getRoot();
    }
}

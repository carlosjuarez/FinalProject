package com.batch.mcs.finalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFeedFragment extends BaseFragment {

    public CalendarFeedFragment() {
    }

    public static CalendarFeedFragment newInstance() {
        CalendarFeedFragment fragment = new CalendarFeedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar_feed, container, false);
    }

}

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

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.FragmentCalendarEventsBinding;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarEventsFragment extends BaseFragment {

    CalendarView calendarView;

    public CalendarEventsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCalendarEventsBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar_events,container,false);
        final AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        calendarView = fragmentBinding.calendarCalendardisplayCalendar;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //Pass a date to this method
                Calendar calendar = new GregorianCalendar( year, month, day );
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy",Locale.getDefault());
                appViewModel.filterFeedCalendar(sdf.format(calendar.getTimeInMillis()));
            }
        });

        appViewModel.getLiveEventAll().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                if(events!=null&&events.size()>0){
                    String eventDate = events.get(0).getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy", Locale.getDefault());
                    try {
                        long date = sdf.parse(eventDate).getTime();
                        calendarView.setDate(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return fragmentBinding.getRoot();
    }

}

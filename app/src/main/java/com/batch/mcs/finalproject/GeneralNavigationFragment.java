package com.batch.mcs.finalproject;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.adapters.ViewPagerAdapter;
import com.batch.mcs.finalproject.databinding.FragmentGeneralNavigationBinding;
import com.batch.mcs.finalproject.interfaces.CallGroupDisplayListener;
import com.batch.mcs.finalproject.views.BaseFragment;
import com.batch.mcs.finalproject.views.CalendarDisplayFragment;
import com.batch.mcs.finalproject.views.ChatFragment;
import com.batch.mcs.finalproject.views.FeedFragment;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralNavigationFragment extends BaseFragment {

    CallGroupDisplayListener listener;

    public GeneralNavigationFragment() {
        // Required empty public constructor
    }

    public static GeneralNavigationFragment getInstance(){
        GeneralNavigationFragment generalNavigationFragment = new GeneralNavigationFragment();
        return generalNavigationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentGeneralNavigationBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_general_navigation,container,false);
        ViewPager viewPager = fragmentBinding.showViewPager;
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        LinkedHashMap<String, Fragment> userTabs= new LinkedHashMap<>();
        userTabs.put("Calendar", CalendarDisplayFragment.getInstance());
        userTabs.put("Feed", FeedFragment.getInstance());
        userTabs.put("Chat", new ChatFragment());
        userTabs.put("Search", new SearchFragment());

        for(Map.Entry<String, Fragment> entry : userTabs.entrySet()){
            viewPagerAdapter.addFrag(entry.getValue(), entry.getKey());
        }
        viewPager.setAdapter(viewPagerAdapter);
        fragmentBinding.showTabs.setupWithViewPager(viewPager);
        return fragmentBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallGroupDisplayListener) {
            listener = (CallGroupDisplayListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + R.string.error_onattach_callgroupdisplaylistener);
        }
    }

}

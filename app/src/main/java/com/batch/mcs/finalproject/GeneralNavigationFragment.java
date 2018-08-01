package com.batch.mcs.finalproject;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.databinding.ActivityMainBinding;
import com.batch.mcs.finalproject.databinding.FragmentGeneralNavigationBinding;
import com.batch.mcs.finalproject.interfaces.CallGroupDisplayListener;
import com.batch.mcs.finalproject.viewmodel.TabViewModel;

import java.util.HashMap;
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

        HashMap<String, Fragment> userTabs= new HashMap<String, Fragment>();
        userTabs.put("Calendar", new CalendarDisplayFragment());
        userTabs.put("Feed", new CalendarFeedFragment());
        userTabs.put("Chat", new ChatFragment());
        userTabs.put("Search", new SearchFragment());

        FragmentGeneralNavigationBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_general_navigation,container,false);
        TabViewModel tabViewModel = new TabViewModel(getActivity(), userTabs);
        fragmentBinding.setTabViewModel(tabViewModel);

        //De alguna forma tenemos que pasar el listener a los fragments para usar algo como
        //listener.showGroupNavigation(group);
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

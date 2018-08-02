package com.batch.mcs.finalproject;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.adapters.ViewPagerAdapter;
import com.batch.mcs.finalproject.databinding.FragmentGroupNavigationBinding;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.viewmodel.TabViewModel;

import java.util.HashMap;
import java.util.Map;

public class GroupNavigationFragment extends BaseFragment {

    public GroupNavigationFragment() {
        // Required empty public constructor
    }

    public static GroupNavigationFragment newInstance(Group group) {
        GroupNavigationFragment fragment = new GroupNavigationFragment();
        Bundle args = new Bundle();
        args.putParcelable(Resources.getSystem().getString(R.string.parameter_group), group);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //Obtener el grupo y el usuario
            //Me supongo que aqui se debe de crear un viewModel de grupo con los datos de eventos y cosas asi
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentGroupNavigationBinding fragmentGroupNavigationBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_group_navigation, container, false);

        ViewPager viewPager = fragmentGroupNavigationBinding.showGroupViewPager;
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        HashMap<String, Fragment> groupTabs= new HashMap<String, Fragment>();
        groupTabs.put("Calendar", new GroupCalendarDisplayFragment());
        groupTabs.put("Feed", new GroupFeedDisplayFragment());
        groupTabs.put("Members", new GroupMembersDisplayFragment());

        for(Map.Entry<String, Fragment> entry : groupTabs.entrySet()){
            viewPagerAdapter.addFrag(entry.getValue(), entry.getKey());
        }
        viewPager.setAdapter(viewPagerAdapter);
        fragmentGroupNavigationBinding.showTabs.setupWithViewPager(viewPager);
        return fragmentGroupNavigationBinding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

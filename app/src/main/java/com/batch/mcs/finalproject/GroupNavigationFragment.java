package com.batch.mcs.finalproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.adapters.ViewPagerAdapter;
import com.batch.mcs.finalproject.databinding.FragmentGroupNavigationBinding;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.GroupViewModel;
import com.batch.mcs.finalproject.views.BaseFragment;
import com.batch.mcs.finalproject.views.GroupCalendarDisplayFragment;
import com.batch.mcs.finalproject.views.GroupFeedFragment;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GroupNavigationFragment extends BaseFragment {

    private Group group;
    private User user;

    public GroupNavigationFragment() {
        // Required empty public constructor
    }

    public static GroupNavigationFragment newInstance(String tag, Group group, User user) {
        GroupNavigationFragment fragment = new GroupNavigationFragment();
        Bundle args = new Bundle();
        args.putParcelable(tag, group);
        args.putParcelable("parameter_user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            group = bundle.getParcelable(getString(R.string.parameter_group));
            user = bundle.getParcelable("parameter_user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentGroupNavigationBinding fragmentGroupNavigationBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_group_navigation, container, false);

        ViewPager viewPager = fragmentGroupNavigationBinding.showGroupViewPager;
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        LinkedHashMap<String, Fragment> groupTabs= new LinkedHashMap<>();
        groupTabs.put("Calendar", GroupCalendarDisplayFragment.getInstance());
        groupTabs.put("Feed", GroupFeedFragment.getInstance());
        groupTabs.put("Members", GroupMembersDisplayFragment.getInstance());

        for(Map.Entry<String, Fragment> entry : groupTabs.entrySet()){
            viewPagerAdapter.addFrag(entry.getValue(), entry.getKey());
        }
        viewPager.setAdapter(viewPagerAdapter);
        fragmentGroupNavigationBinding.showTabs.setupWithViewPager(viewPager);

        initializeDataFromGroup();

        fragmentGroupNavigationBinding.showTabs.setTabTextColors( getResources().getColor(R.color.colorTabTextUnselected),getResources().getColor(R.color.colorTabTextSelected));
        return fragmentGroupNavigationBinding.getRoot();
    }

    private void initializeDataFromGroup() {
        final GroupViewModel groupViewModel = ViewModelProviders.of(getActivity()).get(GroupViewModel.class);
        groupViewModel.setUser(user);
        groupViewModel.initGroup(group);
        groupViewModel.getLiveGroup().observe(this, new Observer<Group>() {
            @Override
            public void onChanged(@Nullable Group group) {
                groupViewModel.initEvents(group);
                groupViewModel.initMembers(group);
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

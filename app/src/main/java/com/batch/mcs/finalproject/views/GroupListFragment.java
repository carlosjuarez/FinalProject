package com.batch.mcs.finalproject.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import android.widget.Toast;

import com.batch.mcs.finalproject.BaseFragment;
import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.adapters.UserGroupListAdapter;
import com.batch.mcs.finalproject.databinding.FragmentGroupListBinding;
import com.batch.mcs.finalproject.interfaces.CallGroupDisplayListener;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;

import java.util.List;

public class GroupListFragment extends BaseFragment {

    RecyclerView recyclerView;
    UserGroupListAdapter userGroupListAdapter;
    CallGroupDisplayListener listener;

    public GroupListFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGroupListBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_list,container,false);
        final AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        //RecyclerView recyclerView = fragmentBinding
        recyclerView = fragmentBinding.rvListViewRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);

        appViewModel.getLiveGroupAdmin().observe(this, new Observer<List<Group>>() {
            @Override
            public void onChanged(@Nullable List<Group> groups) {
                setupRecyclerView(groups);
            }
        });

        return fragmentBinding.getRoot();
    }

    private void setupRecyclerView(List<Group> groups) {
        userGroupListAdapter = new UserGroupListAdapter(groups,listener);
        recyclerView.setAdapter(userGroupListAdapter);
        userGroupListAdapter.notifyDataSetChanged();
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

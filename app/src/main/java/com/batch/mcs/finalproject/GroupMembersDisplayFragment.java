package com.batch.mcs.finalproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.adapters.GroupMemberListAdapter;
import com.batch.mcs.finalproject.databinding.FragmentGroupMembersBinding;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;
import com.batch.mcs.finalproject.viewmodel.GroupMemberListViewModel;
import com.batch.mcs.finalproject.views.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class GroupMembersDisplayFragment extends BaseFragment implements GroupMemberListAdapter.GroupMemberListAdapsterListner{
    private GroupMemberListAdapter adapter;
    private RecyclerView recyclerView;
    private GroupMemberListViewModel groupMemberListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGroupMembersBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_members,container,false);
        groupMemberListViewModel=ViewModelProviders.of(getActivity()).get(GroupMemberListViewModel.class);
        groupMemberListViewModel.getMembers();
        recyclerView= binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(true);

        groupMemberListViewModel.getLiveGroupMember().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                setUpRecyclerView(users);
            }
        });

        return binding.getRoot();
    }

    private void setUpRecyclerView(List<User> members){
        adapter = new GroupMemberListAdapter(members, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMemberClicked(User member) {
        groupMemberListViewModel.createChat(member);
    }


}

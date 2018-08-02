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

import com.batch.mcs.finalproject.BaseFragment;
import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.FragmentUserListBinding;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;
import com.batch.mcs.finalproject.viewmodel.TabViewModel;

import java.util.List;

public class UserListFragment extends BaseFragment {

    public UserListFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentUserListBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list,container,false);

        AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        //appViewModel.initUserChats();

        appViewModel.getLiveUserChats().observe(this, new Observer<List<Chat>>() {
            @Override
            public void onChanged(@Nullable List<Chat> chats) {
                //populate recyclerview
            }
        });
        return fragmentBinding.getRoot();
    }
}

package com.batch.mcs.finalproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.adapters.SearchFragmentRecyclerViewAdapter;
import com.batch.mcs.finalproject.databinding.FragmentSearchBinding;
import com.batch.mcs.finalproject.interfaces.ViewClickListener;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;
import com.batch.mcs.finalproject.views.BaseFragment;

import java.util.List;

public class SearchFragment extends BaseFragment implements ViewClickListener {

    FragmentSearchBinding fragmentSearchBinding;
    View view;
    SearchFragmentRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    AppViewModel appViewModel;

    public static SearchFragment getInstance(){
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        appViewModel.initAllGroups();

        recyclerView = fragmentSearchBinding.rvSearchLayout;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SearchView searchView = fragmentSearchBinding.svSearchLayout;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(adapter!=null){
                    adapter.getFilter().filter(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        appViewModel.getLiveGroupAll().observe(this, new Observer<List<Group>>() {
            @Override
            public void onChanged(@Nullable List<Group> groups) {

                adapter = new SearchFragmentRecyclerViewAdapter(groups, SearchFragment.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void clickListener(Group group) {
        appViewModel.addGroupToUser(group);
    }
}
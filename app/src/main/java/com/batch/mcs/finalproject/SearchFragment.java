package com.batch.mcs.finalproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.adapters.SearchFragmentRecyclerViewAdapter;
import com.batch.mcs.finalproject.databinding.FragmentSearchBinding;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;

import java.util.List;

public class SearchFragment extends BaseFragment {

    FragmentSearchBinding fragmentSearchBinding;
    View view;
    SearchFragmentRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    public static SearchFragment getInstance(){
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        view = fragmentSearchBinding.getRoot();

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.initMockGroups();
        appViewModel.getLiveGroupAll().observe(this, new Observer<List<Group>>() {
            @Override
            public void onChanged(@Nullable List<Group> groups) {

                SearchView searchView = fragmentSearchBinding.svSearchLayout;
                search(searchView);

                adapter = new SearchFragmentRecyclerViewAdapter(groups, getContext());
                recyclerView = fragmentSearchBinding.rvSearchLayout;
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
            }
        });

        return view;
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}

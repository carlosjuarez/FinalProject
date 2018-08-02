package com.batch.mcs.finalproject.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.batch.mcs.finalproject.BR;
import com.batch.mcs.finalproject.adapters.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabViewModel extends BaseObservable {
    FragmentManager fragmentManager;
    ViewPagerAdapter adapter;

    public TabViewModel(FragmentManager fragmentManager, final HashMap<String, Fragment> fragments){
        this.fragmentManager = fragmentManager;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createViewPager(fragments);
            }
        }, 1000);
    }

    @Bindable
    public PagerAdapter getPagerAdapter() {
        return adapter;
    }

    private void createViewPager(HashMap<String, Fragment> fragmentsHash){
        adapter= new ViewPagerAdapter(fragmentManager);


        notifyPropertyChanged(BR.pagerAdapter);
    }



}

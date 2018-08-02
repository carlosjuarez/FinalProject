package com.batch.mcs.finalproject.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.batch.mcs.finalproject.BR;
import com.batch.mcs.finalproject.CalendarDisplayFragment;
import com.batch.mcs.finalproject.CalendarFeedFragment;
import com.batch.mcs.finalproject.ChatFragment;
import com.batch.mcs.finalproject.SearchFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TabViewModel extends BaseObservable {
    FragmentActivity mContext;
    ViewPagerAdapter adapter;

    public TabViewModel(FragmentActivity context, final HashMap<String, Fragment> fragments){
        mContext= context;
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
        adapter= new ViewPagerAdapter(mContext.getSupportFragmentManager());
        for(Map.Entry<String, Fragment> entry : fragmentsHash.entrySet()){
            adapter.addFrag(entry.getValue(), entry.getKey());
        }

        notifyPropertyChanged(BR.pagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentImgList= new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String img){
            mFragmentList.add(fragment);
            mFragmentImgList.add(img);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentImgList.get(position);
        }

    }

}

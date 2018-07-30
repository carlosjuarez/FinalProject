package com.batch.mcs.finalproject.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.media.Image;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.batch.mcs.finalproject.BR;
import com.batch.mcs.finalproject.CalendarDisplayFragment;
import com.batch.mcs.finalproject.CalendarFeedFragment;
import com.batch.mcs.finalproject.ChatFragment;
import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class TabViewModel extends BaseObservable {
    FragmentActivity mContext;
    ViewPagerAdapter adapter;

    public TabViewModel(FragmentActivity context){
        mContext= context;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createViewPager();
            }
        }, 1000);
    }

    @Bindable
    public PagerAdapter getPagerAdapter() {
        return adapter;
    }

    private void createViewPager(){
        adapter= new ViewPagerAdapter(mContext.getSupportFragmentManager());
        adapter.addFrag(new CalendarDisplayFragment(), "Calendar");
        adapter.addFrag(new CalendarFeedFragment(), "Feed");
        adapter.addFrag(new ChatFragment(), "Chat");
        adapter.addFrag(new SearchFragment(), "Search");

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

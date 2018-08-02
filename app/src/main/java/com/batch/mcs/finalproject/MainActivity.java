package com.batch.mcs.finalproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.batch.mcs.finalproject.databinding.ActivityMainBinding;
import com.batch.mcs.finalproject.databinding.DrawerUserInfoBinding;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.interfaces.CallGroupDisplayListener;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;
import com.batch.mcs.finalproject.viewmodel.TabViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements CallGroupDisplayListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = activityBinding.toolbarlayout.toolbar;
        setSupportActionBar(toolbar);

        drawerLayout = activityBinding.drawerLayout;
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        String userId = Objects.requireNonNull(getIntent().getExtras()).getString(getString(R.string.parameter_userid));

        String userId = "10025";

        final DrawerUserInfoBinding drawerUserInfoBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.drawer_user_info, activityBinding.navView, false);
        navigationView = activityBinding.navView;
        navigationView.addHeaderView(drawerUserInfoBinding.getRoot());

        final AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.initUser(userId);
        appViewModel.getLiveUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                appViewModel.initUserGroups();
                drawerUserInfoBinding.setUser(user);
            }
        });

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContent,GeneralNavigationFragment.getInstance()).commit();

    }


    @Override
    public void showGroupNavigation(Group group) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutContent, GroupNavigationFragment.newInstance(group));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }


}

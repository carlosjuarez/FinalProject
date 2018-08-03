package com.batch.mcs.finalproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.batch.mcs.finalproject.viewmodel.CreateGroupViewModel;
import com.batch.mcs.finalproject.viewmodel.TabViewModel;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements CallGroupDisplayListener {

    private ActionBarDrawerToggle toggle;
    private DrawerUserInfoBinding drawerUserInfoBinding;
    private AppViewModel appViewModel;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = activityBinding.toolbarlayout.toolbar;
        setSupportActionBar(toolbar);

        drawerLayout = activityBinding.drawerLayout;
        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        String userId = Objects.requireNonNull(getIntent().getExtras()).getString(getString(R.string.parameter_userid));

        drawerUserInfoBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.drawer_user_info, activityBinding.navView, false);
        NavigationView navigationView = activityBinding.navView;
        navigationView.addHeaderView(drawerUserInfoBinding.getRoot());

        drawerUserInfoBinding.btnDrawerCreategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNewGroupActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(getString(R.string.parameter_user),appViewModel.getLiveUser().getValue());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        initializeData(userId);

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContent,GeneralNavigationFragment.getInstance()).commit();

    }

    private void initializeData(String userId) {
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.initUser(userId);
        appViewModel.getLiveUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                appViewModel.initMyGroups();
                drawerUserInfoBinding.setUser(user);
            }
        });
        appViewModel.initGroups(userId);
        appViewModel.getLiveGroupMember().observe(this, new Observer<List<Group>>() {
            @Override
            public void onChanged(@Nullable List<Group> groups) {
                appViewModel.initEvents(groups);
            }
        });
    }


    @Override
    public void showGroupNavigation(Group group) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutContent, GroupNavigationFragment.newInstance(getString(R.string.parameter_group),group));
        transaction.addToBackStack(null);
        transaction.commit();
        if(drawerLayout!=null){
            drawerLayout.closeDrawers();
        }
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

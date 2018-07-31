package com.batch.mcs.finalproject;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.batch.mcs.finalproject.databinding.ActivityMainBinding;
import com.batch.mcs.finalproject.interfaces.CallGroupDisplayListener;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements CallGroupDisplayListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar((Toolbar) activityBinding.toolbarlayout.findViewById(R.id.toolbar));

        String userId = Objects.requireNonNull(getIntent().getExtras()).getString(getString(R.string.parameter_userid));

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.init(userId);

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContent,GeneralNavigationFragment.getInstance()).commit();

    }


    @Override
    public void showGroupNavigation(Group group) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutContent, GroupNavigationFragment.newInstance(group));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

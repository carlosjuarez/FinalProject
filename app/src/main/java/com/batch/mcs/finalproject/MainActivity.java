package com.batch.mcs.finalproject;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.batch.mcs.finalproject.databinding.ActivityMainBinding;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;
import com.batch.mcs.finalproject.viewmodel.TabViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar((Toolbar) activityBinding.toolbarlayout.findViewById(R.id.toolbar));

//        String userId = Objects.requireNonNull(getIntent().getExtras()).getString(getString(R.string.parameter_userid));

        String userId = "10025";

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.init(userId);

        TabViewModel tabViewModel = new TabViewModel(this);
        activityBinding.setTabViewModel(tabViewModel);

    }
}

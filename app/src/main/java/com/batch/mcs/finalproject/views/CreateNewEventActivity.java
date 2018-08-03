package com.batch.mcs.finalproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.ActivityCreateEventBinding;
import com.batch.mcs.finalproject.databinding.ActivityCreateGroupBinding;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.CreateEventViewModel;
import com.batch.mcs.finalproject.viewmodel.CreateGroupViewModel;

public class CreateNewEventActivity extends AppCompatActivity{
    CreateEventViewModel createEventViewModel;
    ActivityCreateEventBinding activityCreateGroupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle= getIntent().getExtras();
        Group group= bundle.getParcelable(getString(R.string.parameter_group));
        createEventViewModel = ViewModelProviders.of(this).get(CreateEventViewModel.class);
        createEventViewModel.setGroup(group);

        activityCreateGroupBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_event);
        activityCreateGroupBinding.setEventViewModel(createEventViewModel);
    }

    public void CreateNewGroupViewModel(View view){
        createEventViewModel.createNewEvent();
        finish();
    }

    public void CreateNewGroupCancel(View view){
        finish();
    }
}

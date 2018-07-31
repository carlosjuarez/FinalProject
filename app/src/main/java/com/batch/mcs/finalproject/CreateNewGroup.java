package com.batch.mcs.finalproject;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.batch.mcs.finalproject.databinding.ActivityCreateGroupBinding;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.CreateGroupViewModel;
import com.batch.mcs.finalproject.viewmodel.RegisterUserViewModel;

public class CreateNewGroup  extends AppCompatActivity{
    CreateGroupViewModel createNewGroupViewModel;
    ActivityCreateGroupBinding activityCreateGroupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle= getIntent().getExtras();
        User user= bundle.getParcelable(getString(R.string.parameter_user));
        createNewGroupViewModel = ViewModelProviders.of(this).get(CreateGroupViewModel.class);
        createNewGroupViewModel.setAdmin(user);

        activityCreateGroupBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_group);
        activityCreateGroupBinding.setGroupViewModel(createNewGroupViewModel);
    }

    public void CreateNewGroupViewModel(View view){
        createNewGroupViewModel.createNewGroup();
        finish();
    }

    public void CreateNewGroupCancel(View view){
        finish();
    }
}

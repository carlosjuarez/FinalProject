package com.batch.mcs.finalproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.batch.mcs.finalproject.databinding.ActivityRegisterBinding;
import com.batch.mcs.finalproject.helperobjects.FirebaseResult;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.RegisterUserViewModel;

public class RegisterActivity extends AppCompatActivity {

    RegisterUserViewModel registerUserViewModel;
    ActivityRegisterBinding activityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerUserViewModel = ViewModelProviders.of(this).get(RegisterUserViewModel.class);


        activityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        setSupportActionBar((Toolbar) activityRegisterBinding.toolbarlayout.findViewById(R.id.toolbar));
        activityRegisterBinding.setViewModel(registerUserViewModel);

        registerUserViewModel.getFirebaseResult().observe(this, new Observer<FirebaseResult>() {
            @Override
            public void onChanged(@Nullable FirebaseResult firebaseResult) {
                if (firebaseResult != null) {
                    if(firebaseResult.getMessage() != null && !firebaseResult.getMessage().isEmpty()){
                        Snackbar.make(activityRegisterBinding.getRoot(),firebaseResult.getMessage(),Snackbar.LENGTH_SHORT).show();
                    }else if(firebaseResult.getResult()!=null){
                        if(firebaseResult.getResult() == R.string.firebase_user_created){
                            registerUserViewModel.createUser();
                        }
                        Snackbar.make(activityRegisterBinding.getRoot(),firebaseResult.getResult(),Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerUserViewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    Snackbar.make(activityRegisterBinding.getRoot(),"User: "+user.getId()+" created",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void registerNewUser(View view) {
        registerUserViewModel.registerNewUser();
    }
}

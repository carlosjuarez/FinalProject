package com.batch.mcs.finalproject;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.batch.mcs.finalproject.databinding.ActivityLoginBinding;
import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.helperobjects.FirebaseResult;
import com.batch.mcs.finalproject.models.MockFactory;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.LoginUserViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends BaseActivity {

    LoginUserViewModel loginUserViewModel;
    ActivityLoginBinding activityLoginBinding;
    MockFactory factory = new MockFactory();
    MutableLiveData<User> mutableLiveData = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        loginUserViewModel = ViewModelProviders.of(this).get(LoginUserViewModel.class);

        if(loginUserViewModel.isUserLoggedAndVerified()){
            startApplication();
        }else{
            activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
            setSupportActionBar(activityLoginBinding.toolbar);
            activityLoginBinding.setViewmodel(loginUserViewModel);
        }
        initObservers();
    }

    private void initObservers() {
        loginUserViewModel.getFirebaseResult().observe(this, new Observer<FirebaseResult>() {
            @Override
            public void onChanged(@Nullable FirebaseResult firebaseResult) {
                if (firebaseResult != null) {
                    if(firebaseResult.getMessage() != null && !firebaseResult.getMessage().isEmpty()){
                        Snackbar.make(activityLoginBinding.getRoot(),firebaseResult.getMessage(),Snackbar.LENGTH_SHORT).show();
                    }else if(firebaseResult.getResult()!=null){
                        if(firebaseResult.getResult()==R.string.user_logged_in){
                            if(loginUserViewModel.isUserLoggedAndVerified()){
                                startApplication();
                            }
                        }else{
                            Snackbar.make(activityLoginBinding.getRoot(),firebaseResult.getResult(),Snackbar.LENGTH_SHORT).show();
                        }

                    }
                }

            }
        });

    }

    private void startApplication() {
        Intent intent = new Intent(this,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.parameter_userid),loginUserViewModel.firebaseUser.getUid());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private boolean validateForm(EditText mEmailField, EditText mPasswordField) {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }
        return valid;
    }

    public void logUser(View view) {

        loginUserViewModel.loginUser();

//        FirebaseFirestore ff = FirebaseFirestore.getInstance();
//        FirebaseDatabase fb = new FirebaseDatabase(ff);
//
//        for(int i=0; i<factory.getUsersArrayList().size(); i++) {
//            fb.saveUser(factory.getUsersArrayList().get(i), mutableLiveData);
//        }
//
//        for(int i=0; i<factory.getGroupsArrayList().size(); i++){
//            fb.saveGroup(factory.getUsersArrayList().get(0), factory.getGroupsArrayList().get(i));
//        }
//
//
//        fb.saveEvent(factory.getGroupsArrayList().get(0), factory.getEventsArrayList().get(0));
//        fb.saveEvent(factory.getGroupsArrayList().get(0), factory.getEventsArrayList().get(1));
//        fb.saveEvent(factory.getGroupsArrayList().get(1), factory.getEventsArrayList().get(2));
//        fb.saveEvent(factory.getGroupsArrayList().get(2), factory.getEventsArrayList().get(3));


    }

    public void callPasswordForgotten(View view) {
        Intent intent = new Intent(this,ResetPasswordActivity.class);
        startActivity(intent);
    }

    public void callRegisterNewUser(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}

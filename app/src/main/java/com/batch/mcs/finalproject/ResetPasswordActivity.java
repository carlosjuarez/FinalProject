package com.batch.mcs.finalproject;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.batch.mcs.finalproject.databinding.ActivityResetPasswordBinding;
import com.batch.mcs.finalproject.helperobjects.FirebaseResult;
import com.batch.mcs.finalproject.viewmodel.ResetPasswordViewModel;

public class ResetPasswordActivity extends AppCompatActivity {

    ResetPasswordViewModel resetPasswordViewModel;
    ActivityResetPasswordBinding activityResetPasswordBinding;
    TextView tvEmail;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityResetPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        setSupportActionBar((Toolbar) activityResetPasswordBinding.toolbarlayout.toolbar);

        resetPasswordViewModel = new ResetPasswordViewModel();
        resetPasswordViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                activityResetPasswordBinding.etRecoverypasswordInputEmail.setError(s);
            }
        });

        resetPasswordViewModel.getFirebaseResult().observe(this,
                new Observer<FirebaseResult>() {
                    @Override
                    public void onChanged(@Nullable FirebaseResult firebaseResult) {
                        if (firebaseResult != null) {
                            if(firebaseResult.getMessage() != null && !firebaseResult.getMessage().isEmpty()){
                                Snackbar.make(activityResetPasswordBinding.getRoot(),firebaseResult.getMessage(),Snackbar.LENGTH_SHORT).show();
                            }else if(firebaseResult.getResult()!=null){
                                Snackbar.make(activityResetPasswordBinding.getRoot(),firebaseResult.getResult(),Snackbar.LENGTH_SHORT).show();
                                if(firebaseResult.getResult() == R.string.recovery_email_sended){
                                    finish();
                                }
                            }
                        }
                    }
                });

        activityResetPasswordBinding.setViewModel(resetPasswordViewModel);
    }

    public void sendEmail(View view) {
        resetPasswordViewModel.sendResetPasswordEmail();
    }
}

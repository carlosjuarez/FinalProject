package com.batch.mcs.finalproject;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.batch.mcs.finalproject.databinding.ActivityResetPasswordBinding;
import com.batch.mcs.finalproject.viewmodel.ResetPasswordModelView;

public class ResetPasswordActivity extends AppCompatActivity {

    ResetPasswordModelView resetPasswordModelView;
    ActivityResetPasswordBinding activityResetPasswordBinding;
    TextView tvEmail;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityResetPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);

        resetPasswordModelView = new ResetPasswordModelView();

        resetPasswordModelView.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                activityResetPasswordBinding.etRecoverypasswordInputEmail.setError(s);
            }
        });

        resetPasswordModelView.getSendMessageResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null && !s.isEmpty()) {
                    Snackbar.make(activityResetPasswordBinding.getRoot(), s, Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(activityResetPasswordBinding.getRoot(), R.string.recovery_email_sended, Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        activityResetPasswordBinding.setViewModel(resetPasswordModelView);
    }

    public void sendEmail(View view) {
        resetPasswordModelView.sendResetPasswordEmail();
    }
}

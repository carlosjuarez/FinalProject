package com.batch.mcs.finalproject;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class PasswordForgottenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}

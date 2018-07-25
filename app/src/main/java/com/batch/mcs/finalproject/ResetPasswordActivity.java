package com.batch.mcs.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.batch.mcs.finalproject.authentication.FirebaseAuthentication;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    TextView tvEmail;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        tvEmail = findViewById(R.id.et_recoverypassword_input_email);
        btnSubmit = findViewById(R.id.btn_recoverypassword_submit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String emailAddress = tvEmail.getText().toString();
                FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication(ResetPasswordActivity.this, mAuth);
                firebaseAuthentication.sendRecoveryPassword(emailAddress);
            }
        });





    }
}

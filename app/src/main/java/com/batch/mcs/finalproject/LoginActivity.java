package com.batch.mcs.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.batch.mcs.finalproject.FirebaseAuthentication;

public class LoginActivity extends AppCompatActivity
                                    implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText mEmailField;
    private EditText mPasswordField;
    private FirebaseAuthentication mSession= new FirebaseAuthentication();

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        //text fields
        mEmailField = findViewById(R.id.input_email);
        mPasswordField= findViewById(R.id.input_password);

        //buttons
        findViewById(R.id.button_login_submit).setOnClickListener(this);

        mSession.FirebaseAuthentication(this, mAuth);
    }

    @Override
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.button_login_submit){
            mSession.signIn(mEmailField.getText()
                    .toString(), mPasswordField.getText()
                    .toString(), mEmailField, mPasswordField);
        }
    }
}

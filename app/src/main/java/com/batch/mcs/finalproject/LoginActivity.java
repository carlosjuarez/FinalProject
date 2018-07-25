package com.batch.mcs.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.batch.mcs.finalproject.authentication.FirebaseAuthentication;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try{
            mAuth = FirebaseAuth.getInstance();
            mAuth.sendPasswordResetEmail("juvcarl@gmail.com");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

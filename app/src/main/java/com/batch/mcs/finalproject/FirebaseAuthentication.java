package com.batch.mcs.finalproject;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.batch.mcs.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


public class FirebaseAuthentication {
    private Context context;
    private FirebaseAuth mAuth;

    public void FirebaseAuthentication(Context con, FirebaseAuth mAuthenticate){
        context= con;
        mAuth= mAuthenticate;
    }

    public void signIn(String email, String password, EditText mEmailField, EditText mPasswordField){
        if(!validateForm(mEmailField, mPasswordField)){
            return;
        }

        //Start sign in with email
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Log in Successful", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    //Change to next activity or set boolean flag to true to change activity
                } else {
                    //log in failed
                    Toast.makeText(context, "Log in Not Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateForm(EditText mEmailField, EditText mPasswordField){
        boolean valid= true;

        String email = mEmailField.getText().toString();
        if(TextUtils.isEmpty(email)){
            mEmailField.setError("Required");
            valid= false;
        }else{
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if(TextUtils.isEmpty(password)){
            mPasswordField.setError("Required");
            valid= false;
        }else{
            mPasswordField.setError(null);
        }
        return valid;
    }
}

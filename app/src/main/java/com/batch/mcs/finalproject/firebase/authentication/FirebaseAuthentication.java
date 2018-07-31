package com.batch.mcs.finalproject.firebase.authentication;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.helperobjects.FirebaseResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthentication {

    private static final String Tag = FirebaseAuthentication.class.getSimpleName();
    private FirebaseAuth mAuth;

    public FirebaseAuthentication(FirebaseAuth mAuthenticate) {
        this.mAuth = mAuthenticate;
    }

    public void loginUser(final MutableLiveData<FirebaseResult> firebaseResult, String email, String password) {

        //Start sign in with email
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseResult.postValue(new FirebaseResult(R.string.user_logged_in,null));
                            Log.d(Tag, "sended");
                        } else {
                            if (task.getException() != null && task.getException().getMessage() != null) {
                                String exceptionMessage = task.getException().getMessage();
                                firebaseResult.postValue(new FirebaseResult(null,exceptionMessage));
                            }
                            Log.d(Tag, task.getException().toString());
                        }
                    }
                });
    }

    public void createUser(final MutableLiveData<FirebaseResult> firebaseResult, String emailAddress, String password){

        mAuth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseResult.postValue(new FirebaseResult(R.string.firebase_user_created,null));
                            Log.d(Tag, "sended");
                        } else {
                            if (task.getException() != null && task.getException().getMessage() != null) {
                                String exceptionMessage = task.getException().getMessage();
                                firebaseResult.postValue(new FirebaseResult(null,exceptionMessage));
                            }
                            Log.d(Tag, task.getException().toString());
                        }
                    }
                });
    }

    public void sendRecoveryPassword(final MutableLiveData<FirebaseResult> firebaseResult, String emailAddress) {

        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://finalproject.page.link")
                .setIOSBundleId("com.batch.mcs.finalproject")
                .setAndroidPackageName("com.batch.mcs.finalproject", true, null)
                .build();

        mAuth.sendPasswordResetEmail(emailAddress, actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            firebaseResult.postValue(new FirebaseResult(R.string.recovery_email_sended,null));
                            Log.d(Tag, "sended");
                        } else {
                            if (task.getException() != null && task.getException().getMessage() != null) {
                                String exceptionMessage = task.getException().getMessage();
                                firebaseResult.postValue(new FirebaseResult(null,exceptionMessage));
                            }
                            Log.d(Tag, task.getException().toString());
                        }
                    }
                });
    }

    public void sendValidateMail(final MutableLiveData<FirebaseResult> firebaseResult, FirebaseUser currentUser) {
        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://finalproject.page.link")
                .setIOSBundleId("com.batch.mcs.finalproject")
                .setAndroidPackageName("com.batch.mcs.finalproject", true, null)
                .build();

        currentUser.sendEmailVerification(actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            firebaseResult.postValue(new FirebaseResult(R.string.firebase_verification_email_sent,null));
                            Log.d(Tag, "sended");
                        } else {
                            if (task.getException() != null && task.getException().getMessage() != null) {
                                String exceptionMessage = task.getException().getMessage();
                                firebaseResult.postValue(new FirebaseResult(null,exceptionMessage));
                            }
                            Log.d(Tag, task.getException().toString());
                        }
                    }
                });
    }
}

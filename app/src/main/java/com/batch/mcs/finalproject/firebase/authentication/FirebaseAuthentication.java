package com.batch.mcs.finalproject.firebase.authentication;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthentication {

    private static final String Tag = FirebaseAuthentication.class.getSimpleName();

    private Context context;
    private FirebaseAuth mAuth;

    public FirebaseAuthentication(Context context, FirebaseAuth mAuthenticate) {
        this.context = context;
        this.mAuth = mAuthenticate;
    }

    public FirebaseAuthentication(FirebaseAuth mAuthenticate) {
        this.mAuth = mAuthenticate;
    }

    public void signIn(String email, String password) {

        //Start sign in with email
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            //TODO Change to next activity or set boolean flag to true to change activity
                        }
                    }
                });
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

    public void sendRecoveryPassword(final MutableLiveData<String> sendMessageResult, String emailAddress) {

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
                            sendMessageResult.postValue("");
                            Log.d(Tag, "sended");
                        } else {
                            if (task.getException() != null && task.getException().getMessage() != null) {
                                String exceptionMessage = task.getException().getMessage();
                                sendMessageResult.postValue(exceptionMessage);
                            }
                            Log.d(Tag, task.getException().toString());
                        }
                    }
                });
    }

}

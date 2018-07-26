package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.firebase.authentication.FirebaseAuthentication;
import com.batch.mcs.finalproject.helperobjects.FirebaseResult;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordViewModel extends ViewModel {

    public String emailAddress = "";
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<FirebaseResult> firebaseResult;

    public ResetPasswordViewModel() {
        if (errorMessage == null) {
            errorMessage = new MutableLiveData<>();
        }
        if (firebaseResult == null) {
            firebaseResult = new MutableLiveData<>();
        }
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<FirebaseResult> getFirebaseResult() {
        return firebaseResult;
    }


    public void sendResetPasswordEmail() {
        if (validateData()) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication(mAuth);
            firebaseAuthentication.sendRecoveryPassword(firebaseResult, emailAddress);
        }
    }

    private boolean validateData() {
        if(emailAddress.isEmpty()){
            firebaseResult.setValue(new FirebaseResult(R.string.empty_values_recovery_password,null));
            return false;
        }
        return true;
    }

}

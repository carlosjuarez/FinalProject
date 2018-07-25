package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.batch.mcs.finalproject.firebase.authentication.FirebaseAuthentication;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordModelView extends ViewModel {

    public String emailAddress;
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<String> sendMessageResult;

    public ResetPasswordModelView() {
        if (errorMessage == null) {
            errorMessage = new MutableLiveData<>();
        }
        if (sendMessageResult == null) {
            sendMessageResult = new MutableLiveData<>();
        }
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<String> getSendMessageResult() {
        return sendMessageResult;
    }

    public boolean validateEmail() {
        //errorMessage.setValue("There's an error");
        return true;
    }


    public void sendResetPasswordEmail() {
        if (validateEmail()) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication(mAuth);
            firebaseAuthentication.sendRecoveryPassword(sendMessageResult, emailAddress);
        }
    }

}

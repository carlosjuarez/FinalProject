package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.firebase.authentication.FirebaseAuthentication;
import com.batch.mcs.finalproject.helperobjects.FirebaseResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginUserViewModel extends ViewModel {

    private FirebaseAuth mAuth;
    public String email = "";
    public String password = "";
    public FirebaseUser firebaseUser;
    private MutableLiveData<FirebaseResult> firebaseResult;
    private FirebaseAuthentication firebaseAuthentication;

    public LoginUserViewModel(){
        if(mAuth ==null){
            mAuth = FirebaseAuth.getInstance();
        }
        if(mAuth.getCurrentUser()!=null){
            mAuth.getCurrentUser().reload();
        }
        firebaseAuthentication = new FirebaseAuthentication(mAuth);

        if(firebaseResult==null){
            firebaseResult = new MutableLiveData<>();
        }
    }

    public boolean isUserLoggedAndVerified() {
        firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            if(firebaseUser.isEmailVerified()){
                return true;
            }else{
                firebaseResult.setValue(new FirebaseResult(R.string.check_email_before,null));
            }
        }
        return false;
    }

    public void loginUser() {
        if(validateData()){
            firebaseAuthentication.loginUser(firebaseResult,email,password);
        }
    }

    private boolean validateData() {
        if(email.isEmpty() || password.isEmpty()){
            firebaseResult.setValue(new FirebaseResult(R.string.empty_values_login,null));
            return false;
        }
        return true;
    }

    public MutableLiveData<FirebaseResult> getFirebaseResult() {
        return firebaseResult;
    }
}

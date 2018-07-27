package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.firebase.authentication.FirebaseAuthentication;
import com.batch.mcs.finalproject.helperobjects.FirebaseResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterUserViewModel extends ViewModel {

    public String confirmPassword = "";
    public String password = "";
    public String city = "";
    public String emailAddress = "";
    public String lastName = "";
    public String firstName = "";

    private User newUser;
    private MutableLiveData<User> userLiveData;
    private MutableLiveData<FirebaseResult> firebaseResult;
    private FirebaseAuth mAuth;
    private FirebaseAuthentication firebaseAuthentication;

    public RegisterUserViewModel(){
        if(firebaseResult==null){
            firebaseResult = new MutableLiveData<>();
        }
        if(mAuth==null){
            mAuth = FirebaseAuth.getInstance();
        }
        if(firebaseAuthentication==null){
            firebaseAuthentication = new FirebaseAuthentication(mAuth);
        }
        if(userLiveData==null){
            userLiveData = new MutableLiveData<>();
        }
    }

    public MutableLiveData<FirebaseResult> getFirebaseResult() {
        return firebaseResult;
    }

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    private boolean validateData(){
        if(confirmPassword.isEmpty()){
            firebaseResult.postValue(new FirebaseResult(R.string.field_confirmPassword_empty,null));
            return false;
        }
        if(password.isEmpty()){
            firebaseResult.postValue(new FirebaseResult(R.string.field_password_empty,null));
            return false;
        }
        if(city.isEmpty()){
            firebaseResult.postValue(new FirebaseResult(R.string.field_city_empty,null));
            return false;
        }
        if(emailAddress.isEmpty()){
            firebaseResult.postValue(new FirebaseResult(R.string.field_emailAddress_empty,null));
            return false;
        }
        if(lastName.isEmpty()){
            firebaseResult.postValue(new FirebaseResult(R.string.field_lastName_empty,null));
            return false;
        }
        if(firstName.isEmpty()){
            firebaseResult.postValue(new FirebaseResult(R.string.field_firstName_empty,null));
            return false;
        }

        if(!password.equals(confirmPassword)){
            firebaseResult.postValue(new FirebaseResult(R.string.password_different, null));
            return false;
        }
        return true;
    }

    public void registerNewUser(){
        if (validateData()) {
            FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication(mAuth);
            firebaseAuthentication.createUser(firebaseResult, emailAddress, password);
        }
    }

    public void createUser(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String id = firebaseUser.getUid();
        newUser = new User();
        newUser.setId(id);
        newUser.setName(firstName);
        newUser.setLastName(lastName);
        newUser.setCity(city);
        newUser.setEmail(emailAddress);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
        firebaseDatabase.createUser(newUser,userLiveData);
        sendVerificationEmail();
    }

    private void sendVerificationEmail() {
        if(mAuth.getCurrentUser()!=null){
            firebaseAuthentication.sendValidateMail(firebaseResult,mAuth.getCurrentUser());
        }
    }
}

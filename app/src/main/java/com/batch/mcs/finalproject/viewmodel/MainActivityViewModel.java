package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.User;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<User> mutableLiveData;

    public void init(String userId) {
        if(mutableLiveData == null){
            FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
            FirebaseDatabase firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
            mutableLiveData = new MutableLiveData<>();
            firebaseDatabase.loadUser(userId, mutableLiveData);
        }
    }

    public MutableLiveData<User> getMutableLiveData() {
        return mutableLiveData;
    }
}

package com.batch.mcs.finalproject.firebase.firestore;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.helperobjects.FirebaseResult;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class FirebaseDatabase {

    FirebaseFirestore db;

    public FirebaseDatabase(FirebaseFirestore db){
        this.db = db;
    }

    public void createUser(final User user, final MutableLiveData<User> userLiveData){

        db.collection("users").document(user.getId()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                userLiveData.postValue(user);
            }
        });
    }

    public String createGroup(User user, Group group){
        String admin = user.getId();
        group.setIdAdmin(admin);
        DocumentReference newGroupRef = db.collection("group").document();
        String myGId = newGroupRef.getId();
        group.setId(myGId);
        newGroupRef.set(group);

        return myGId;

    }

    public MutableLiveData<User> loadUser(String idUser, final MutableLiveData<User> mutableLiveData){

        final DocumentReference docRef = db.collection("users").document(idUser);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                try{
                    if(e!=null){
                        throw e;
                    }else{
                        User user = new Gson().fromJson(snapshot.getData().toString(), User.class);
                        mutableLiveData.setValue(user);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }


            }
        });

        return mutableLiveData;
    }


    public void updateUser(User user){
        db.collection("users").document(user.getId()).set(user, SetOptions.merge()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }
}



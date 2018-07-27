package com.batch.mcs.finalproject.firebase.firestore;

import android.support.annotation.NonNull;
import android.util.Log;

import com.batch.mcs.finalproject.Models.Group;
import com.batch.mcs.finalproject.Models.User;
import com.batch.mcs.finalproject.ReturnValueFromLoad;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class FirebaseDatabase {

    User loadUser;
    FirebaseFirestore db;

    public FirebaseDatabase(FirebaseFirestore db){
        this.db = db;
    }

    public void createUser(User user){

        DocumentReference newUserRef = db.collection("users").document();
        String myId = newUserRef.getId();
        user.setId(myId);
        newUserRef.set(user);
    }

    public void createGroup(User user, Group group){

        String admin = user.getId();
        group.setIdAdmin(admin);
        DocumentReference newGroupRef = db.collection("group").document();
        String myGId = newGroupRef.getId();
        group.setId(myGId);
        newGroupRef.set(group.toMap());
        DocumentReference newUserRef = db.collection("users").document(admin);

        if(user.getMyGroups() == null){
            Map<String, Boolean> test = new HashMap<>();
            test.put(myGId, true);
            user.setMyGroups(test);
        }else {
            user.getMyGroups().put(myGId, true);
        }

        newUserRef.update("myGroups",user.getMyGroups())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });

    }

    public void loadUser(String idUser, final ReturnValueFromLoad returnValuegFromLoad){

        DocumentReference docRef = db.collection("users").document(idUser);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                returnValuegFromLoad.saveLoadedSnapshow(documentSnapshot);
            }

        });

    }


}



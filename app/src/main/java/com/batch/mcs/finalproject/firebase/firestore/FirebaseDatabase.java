package com.batch.mcs.finalproject.firebase.firestore;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;


import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.helperobjects.FirebaseResult;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.Message;
import com.batch.mcs.finalproject.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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

    public void createGroup(User user, Group group){

        String admin = user.getId();
        group.setIdAdmin(admin);
        DocumentReference newGroupRef = db.collection("groups").document();
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

    public void saveEvent(Group group, Event event){

        String admin = group.getId();
        event.setAdminId(admin);
        DocumentReference newEventRef = db.collection("events").document();
        String myEId = newEventRef.getId();
        event.setId(myEId);
        newEventRef.set(event.toMap());
        DocumentReference newGroupRef = db.collection("groups").document();

        if(group.getIdEvents() == null){
            Map<String, Boolean> test = new HashMap<>();
            test.put(myEId, true);
            group.setIdEvents(test);
        }else {
            group.getIdEvents().put(myEId, true);
        }

        newGroupRef.update("idEvents",group.getIdEvents())
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

    public void saveChat(User user, Chat chat){

        String admin = user.getId();
        chat.setAdmin(admin);
        DocumentReference newChatRef = db.collection("chats").document();
        String myCId = newChatRef.getId();
        chat.setId(myCId);
        newChatRef.set(chat.toMap());
        DocumentReference newUserRef = db.collection("users").document(admin);

        if(user.getChats() == null){
            Map<String, Boolean> test = new HashMap<>();
            test.put(myCId, true);
            user.setChats(test);
        }else {
            user.getChats().put(myCId, true);
        }

        newUserRef.update("chats",user.getChats())
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

    public void saveMessage(Chat chat, Message message){

        String admin = chat.getId();
        DocumentReference newMessageRef = db.collection("messages").document();
        String myMId = newMessageRef.getId();
        message.setId(myMId);
        newMessageRef.set(message.toMap());
        DocumentReference newChatRef = db.collection("chats").document(admin);

        if(chat.getMessages() == null){
            Map<String, Boolean> test = new HashMap<>();
            test.put(myMId, true);
            chat.setMessages(test);
        }else {
            chat.getMessages().put(myMId, true);
        }

        newChatRef.update("chats",chat.getMessages())
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

    public MutableLiveData<User> loadUser(String idUser, final MutableLiveData<User> mutableLiveData){

        final DocumentReference docRef = db.collection("users").document(idUser);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    User user = new Gson().fromJson(documentSnapshot.getData().toString(), User.class);
                    mutableLiveData.setValue(user);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        return mutableLiveData;
    }

    public MutableLiveData<Group> loadGroup(String idGroup, final MutableLiveData<Group> mutableLiveData){

        final DocumentReference docRef = db.collection("groups").document(idGroup);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    Group group = new Gson().fromJson(documentSnapshot.getData().toString(), Group.class);
                    mutableLiveData.setValue(group);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        return mutableLiveData;
    }

    public MutableLiveData<Event> loadEvent(String idEvent, final MutableLiveData<Event> mutableLiveData){

        final DocumentReference docRef = db.collection("events").document(idEvent);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    Event event = new Gson().fromJson(documentSnapshot.getData().toString(), Event.class);
                    mutableLiveData.setValue(event);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        return mutableLiveData;
    }

    public MutableLiveData<Chat> loadChat(String idChat, final MutableLiveData<Chat> mutableLiveData){

        final DocumentReference docRef = db.collection("chats").document(idChat);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    Chat chats = new Gson().fromJson(documentSnapshot.getData().toString(), Chat.class);
                    mutableLiveData.setValue(chats);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        return mutableLiveData;
    }

    public MutableLiveData<Message> loadMessage(String idChat, final MutableLiveData<Message> mutableLiveData){

        final DocumentReference docRef = db.collection("chats").document(idChat);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    Message message = new Gson().fromJson(documentSnapshot.getData().toString(), Message.class);
                    mutableLiveData.setValue(message);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        return mutableLiveData;
    }


}



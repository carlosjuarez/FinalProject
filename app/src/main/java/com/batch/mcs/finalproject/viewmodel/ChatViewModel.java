package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.Message;
import com.batch.mcs.finalproject.models.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ChatViewModel extends ViewModel {

    private MutableLiveData<User> liveUser;
    private MutableLiveData<Chat> liveChat;
    private MutableLiveData<List<Message>> liveMessages;
    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;

    public void init(String userId){

        if(liveUser == null){
            firebaseFirestore= FirebaseFirestore.getInstance();
            firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
            liveUser = new MutableLiveData<>();
            firebaseDatabase.loadUser(userId, liveUser);
        }

        if(liveChat == null){
            liveChat = new MutableLiveData<>();
            firebaseDatabase.loadChat(userId, liveChat);
        }
    }




}

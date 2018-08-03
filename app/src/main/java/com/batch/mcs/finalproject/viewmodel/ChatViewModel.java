package com.batch.mcs.finalproject.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.Message;
import com.batch.mcs.finalproject.models.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChatViewModel extends ViewModel {


    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseDatabase firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
    private MutableLiveData<Chat> liveChat = new MutableLiveData<>();
     private MutableLiveData<List<Message>> liveUserMessages = new MutableLiveData<>();

    public void initChat(String userId) {
        firebaseDatabase.loadChat(userId, liveChat);
    }

    public void initMessages(Activity activity, String chatId) {
        firebaseDatabase.loadChatMessages(activity, chatId, liveUserMessages);
    }

    public MutableLiveData<Chat> getLiveChat() {
        return liveChat;
    }

    public MutableLiveData<List<Message>> getLiveUserMessages() {
        return liveUserMessages;
    }

    public void updateLiveChat(MutableLiveData<Chat> liveChat) {
        this.liveChat = liveChat;
    }

    public void updateLiveMessages(MutableLiveData<List<Message>> liveUserMessages) {
        this.liveUserMessages = liveUserMessages;
    }

}

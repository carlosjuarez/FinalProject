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


    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;
    private MutableLiveData<User> liveUser = new MutableLiveData<>();
    private MutableLiveData<Group> liveGroup;
    private MutableLiveData<List<Group>> liveGroupAdmin = new MutableLiveData<>();
    private MutableLiveData<List<Group>> liveGroupMember = new MutableLiveData<>();
    private MutableLiveData<List<Group>> liveGroupAll;
    private MutableLiveData<Event> liveEvent;
    private MutableLiveData<Chat> liveChat;
    private MutableLiveData<Message> liveMessage;
    private MutableLiveData<List<Chat>> liveUserChats = new MutableLiveData<>();

    public void initUser(String userId) {
        if (userId != null && !userId.isEmpty()){
            firebaseDatabase.loadUser(userId, liveUser);
        }
    }

    public void initGroup(String idGroup){
        firebaseDatabase.loadGroup(idGroup, liveGroup);
    }

    public void initUserGroups() {
        if(liveUser.getValue()!=null){
            firebaseDatabase.loadGroupAdmin(liveUser.getValue(), liveGroupAdmin);
            firebaseDatabase.loadGroupMember(liveUser.getValue(), liveGroupMember);
        }
    }

    public void initAllGroups(){
        if(liveGroupAll == null){
            liveGroupAll = new MutableLiveData<>();
            firebaseDatabase.loadGroupAdmin(liveUser.getValue(), liveGroupAll);
        }
    }

    public void initEvents() {
        if (liveEvent == null) {
            liveEvent = new MutableLiveData<>();
            for (int i = 0; i < liveGroupAdmin.getValue().size(); i++) {
                Map<String, Boolean> map = liveGroupAdmin.getValue().get(i).getIdEvents();
                Set<String> set = map.keySet();
                for (String eId : set) {
                    firebaseDatabase.loadEvent(eId, liveEvent);
                }
            }

            for (int i = 0; i < liveGroupMember.getValue().size(); i++) {
                Map<String, Boolean> map = liveGroupMember.getValue().get(i).getIdEvents();
                Set<String> set = map.keySet();
                for (String eId : set) {
                    firebaseDatabase.loadEvent(eId, liveEvent);
                }
            }
        }
    }

    public void initChat(String userId, Activity activity) {
        if(liveChat.getValue()!=null){
            firebaseDatabase.loadChat(activity,userId, liveChat);
        }
    }



}

package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.ArrayMap;
import android.view.View;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.Message;
import com.batch.mcs.finalproject.models.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AppViewModel extends ViewModel {

    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;
    private MutableLiveData<User> liveUser;
    private MutableLiveData<Group> liveGroup;
    private MutableLiveData<List<Group>> liveGroupAdmin;
    private MutableLiveData<List<Group>> liveGroupMember;
    private MutableLiveData<List<Group>> liveGroupAll;
    private MutableLiveData<Event> liveEvent;
    private MutableLiveData<Chat> liveChat;
    private MutableLiveData<Message> liveMessage;

    public void init(String userId) {
        if(liveUser == null){
            firebaseFirestore= FirebaseFirestore.getInstance();
            firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
            liveUser = new MutableLiveData<>();
            firebaseDatabase.loadUser(userId, liveUser);
        }

        if(liveGroup == null){
            liveGroup = new MutableLiveData<>();
            firebaseDatabase.loadGroup(liveUser.getValue().getId(), liveGroup);
        }

        if(liveGroupAdmin == null){
            liveGroupAdmin = new MutableLiveData<>();
            firebaseDatabase.loadGroupAdmin(liveUser.getValue(), liveGroupAdmin);
        }

        if(liveGroupMember == null){
            liveGroupMember = new MutableLiveData<>();
            firebaseDatabase.loadGroupMember(liveUser.getValue(), liveGroupMember);
        }

        if(liveGroupMember == null){
            liveGroupMember = new MutableLiveData<>();
            firebaseDatabase.loadGroupAdmin(liveUser.getValue(), liveGroupMember);
        }

        if(liveGroupAll == null){
            liveGroupAll = new MutableLiveData<>();
            firebaseDatabase.loadGroupAdmin(liveUser.getValue(), liveGroupAll);
        }

        if(liveEvent == null && liveGroupMember != null && liveGroupAdmin != null){
            liveEvent = new MutableLiveData<>();
            for(int i = 0; i < liveGroupAdmin.getValue().size(); i++) {
                Map<String, Boolean> map = liveGroupAdmin.getValue().get(i).getIdEvents();
                    Set<String> set = map.keySet();
                    for(String eId : set) {
                        firebaseDatabase.loadEvent(eId, liveEvent);
                    }
                }

            for(int i = 0; i < liveGroupMember.getValue().size(); i++) {
                Map<String, Boolean> map = liveGroupMember.getValue().get(i).getIdEvents();
                Set<String> set = map.keySet();
                for(String eId : set) {
                    firebaseDatabase.loadEvent(eId, liveEvent);
                }
            }
        }

        if(liveChat == null){
            liveChat = new MutableLiveData<>();
            firebaseDatabase.loadChat(liveUser.getValue().getId(), liveChat);
        }

        if(liveMessage == null){
            liveMessage = new MutableLiveData<>();
            firebaseDatabase.loadMessage(liveChat.getValue().getId(), liveMessage);
        }
    }

    public void saveGroup(Group group){
        String gId = firebaseDatabase.saveGroup(liveUser.getValue(),group);
        User user = liveUser.getValue();
        if(user.getMyGroups()!=null){
            user.getMyGroups().put(gId,true);
        }else{
            Map<String,Boolean> map = new ArrayMap<String, Boolean>();
            map.put(gId,true);
            user.setMyGroups(map);
        }
        updateliveUser(user);

    }

    public void saveEvent(Event event, Group group){
        String eId = firebaseDatabase.saveEvent(group,event);
        if(group.getIdEvents()!=null){
            group.getIdEvents().put(eId,true);
        }else{
            Map<String,Boolean> map = new ArrayMap<String, Boolean>();
            map.put(eId,true);
            group.setIdEvents(map);
        }
        updateliveGroup(group);

    }

    public void saveChat(Chat chat, User creator, User member){
        String cId = firebaseDatabase.saveChat(creator, member, chat);
        if(creator.getChats()!=null){
            creator.getChats().put(cId,true);
        }else{
            Map<String,Boolean> map = new ArrayMap<String, Boolean>();
            map.put(cId,true);
            creator.setChats(map);
        }
        updateliveUser(creator);
        updateliveUser(member);

    }

    public void saveMessage(Message message, Chat chat){
        String mId = firebaseDatabase.saveMessage(chat,message);
        if(chat.getMessages()!=null){
            chat.getMessages().put(mId,true);
        }else{
            Map<String,Boolean> map = new ArrayMap<String, Boolean>();
            map.put(mId,true);
            chat.setMessages(map);
        }
        updateliveChat(chat);

    }

    public void updateliveUser(User user){
        liveUser.setValue(user);
        firebaseDatabase.updateUser(user);
    }

    public void updateliveGroup(Group group){
        firebaseDatabase.updateGroup(group);
    }

    public void updateliveEvent(Event event){
        firebaseDatabase.updateEvent(event);
    }

    public void updateliveChat(Chat chat){
        firebaseDatabase.updateChat(chat);
    }

    public void updateliveMessage(Message message){
        firebaseDatabase.updateMessage(message);
    }

    public MutableLiveData<User> getLiveUser() {
        return liveUser;
    }

    public MutableLiveData<List<Group>> getLiveGroupAdmin() {
        return liveGroupAdmin;
    }

    public MutableLiveData<List<Group>> getLiveGroupMember() {
        return liveGroupMember;
    }

    public MutableLiveData<List<Group>> getLiveGroupAll() {
        return liveGroupAll;
    }

    public MutableLiveData<Event> getLiveEvent() {
        return liveEvent;
    }

    public MutableLiveData<Chat> getLiveChat() {
        return liveChat;
    }

    public MutableLiveData<Message> getLiveMessage() {
        return liveMessage;
    }
}

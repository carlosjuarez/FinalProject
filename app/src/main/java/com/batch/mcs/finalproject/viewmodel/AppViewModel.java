package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.ArrayMap;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.helperobjects.SelectDate;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.Message;
import com.batch.mcs.finalproject.models.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppViewModel extends ViewModel {

    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;
    private MutableLiveData<User> liveUser = new MutableLiveData<>();
    private MutableLiveData<List<Group>> liveGroupAdmin = new MutableLiveData<>();
    private MutableLiveData<List<Group>> liveGroupMember = new MutableLiveData<>();
    private MutableLiveData<List<Event>> liveEventAll = new MutableLiveData<>();
    private MutableLiveData<List<Chat>> liveUserChats = new MutableLiveData<>();
    private MutableLiveData<SelectDate> selectDateFilter = new MutableLiveData<>();
    private MutableLiveData<List<Group>> liveGroupAll = new MutableLiveData<>();

    public AppViewModel(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
    }

    public void initUser(String userId) {
        if (userId != null && !userId.isEmpty()){
            firebaseDatabase.loadUser(userId, liveUser);
        }
    }

    //Once i have a user I will get the groups that i own
    public void initMyGroups() {
        if (liveUser.getValue() != null) {
            firebaseDatabase.loadGroupsOwnedByUser(liveUser.getValue(), liveGroupAdmin);
        }
    }

    public void initGroups(String idUser){
        firebaseDatabase.loadGroups(idUser, liveGroupMember);
    }

    public void initEvents(List<Group> groups) {
        ArrayList<String> eventIds = new ArrayList<>();
        for(Group group : groups){
            if(group.getIdEvents()!=null){
                for(Map.Entry<String,Boolean> entry : group.getIdEvents().entrySet()){
                    eventIds.add(entry.getKey());
                }
            }
        }
        if(eventIds.size()>0){
            firebaseDatabase.loadEvents(eventIds,liveEventAll);
        }
    }

    public void initAllGroups(){
        firebaseDatabase.loadGroupsAll(liveGroupAll);
    }

    public void initUserChats(){
        firebaseDatabase.loadMyChats(liveUser.getValue().getId(), liveUserChats);
    }

    public void updateliveUser(User user){
        firebaseDatabase.updateUser(user);
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

    public MutableLiveData<List<Chat>> getLiveUserChats() {
        return liveUserChats;
    }

    public MutableLiveData<List<Event>> getLiveEventAll() { return liveEventAll; }


    //Calendar feed filter
    public void filterFeedCalendar(String string) {
        SelectDate selectDate = new SelectDate(0,0,0,string);
        selectDateFilter.setValue(selectDate);
    }

    public MutableLiveData<SelectDate> getSelectDateFilter() {
        return selectDateFilter;
    }


    public MutableLiveData<List<Group>> getLiveGroupAll() {
        return liveGroupAll;
    }

    public void addGroupToUser(Group group) {
        User user = liveUser.getValue();
        if(group.getIdMembers()!=null){
            group.getIdMembers().put(user.getId(),true);
        }else{
            Map<String,Boolean> members = new HashMap<>();
            members.put(group.getId(),true);
            user.setGroups(members);
        }

        firebaseDatabase.updateGroup(group);

        if(user.getGroups()!=null){
            user.getGroups().put(group.getId(),true);
        }else{
            Map<String,Boolean> groups = new HashMap<>();
            groups.put(group.getId(),true);
            user.setGroups(groups);
        }

        firebaseDatabase.updateUser(user);
    }
}

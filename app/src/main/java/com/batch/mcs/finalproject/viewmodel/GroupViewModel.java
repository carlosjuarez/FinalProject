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
import java.util.Set;

public class GroupViewModel extends ViewModel {

    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;

    private Group group;
    private MutableLiveData<List<User>> liveUsers = new MutableLiveData<>();
    private MutableLiveData<Group> liveGroup = new MutableLiveData<>();
    private MutableLiveData<List<Event>> liveEvents = new MutableLiveData<>();
    private MutableLiveData<SelectDate> selectDateFilter = new MutableLiveData<>();
    private User user;


    public GroupViewModel(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
    }

    public void initGroup(Group group){
        firebaseDatabase.loadGroup(group.getId(), liveGroup);
    }

    public void initEvents(Group group) {
        ArrayList<String> eventIds = new ArrayList<>();
        if(group.getIdEvents()!=null){
            for(Map.Entry<String,Boolean> entry : group.getIdEvents().entrySet()){
                eventIds.add(entry.getKey());
            }
            if(eventIds.size()>0){
                firebaseDatabase.loadEvents(eventIds,liveEvents);
            }
        }
    }

    public void initMembers(Group group) {
        ArrayList<String> userIds = new ArrayList<>();
        for(Map.Entry<String,Boolean> entry : group.getIdMembers().entrySet()){
            userIds.add(entry.getKey());
        }
        if(userIds.size()>0){
            firebaseDatabase.loadUsers(userIds,liveUsers);
        }
    }


    public MutableLiveData<List<User>> getLiveUsers() {
        return liveUsers;
    }

    public MutableLiveData<Group> getLiveGroup() {
        return liveGroup;
    }

    public MutableLiveData<List<Event>> getLiveEvents() {
        return liveEvents;
    }

    //Calendar feed filter
    public void filterFeedCalendar(String string) {
        SelectDate selectDate = new SelectDate(0,0,0,string);
        selectDateFilter.setValue(selectDate);
    }

    public MutableLiveData<SelectDate> getSelectDateFilter() {
        return selectDateFilter;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void createChat(User member) {
        Chat chat = new Chat();
        chat.setAdmin(user.getId());
        chat.setAdminName(user.getName()+" "+user.getLastName());
        chat.setMember(member.getId());
        chat.setMemberName(member.getName()+" "+member.getLastName());
        firebaseDatabase.saveChat(chat);
    }
}

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
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AppViewModel extends ViewModel {

    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;

    //This is just for the groups and events
    private MutableLiveData<User> liveUser = new MutableLiveData<>();
    private MutableLiveData<List<Group>> liveGroupAdmin = new MutableLiveData<>();
    private MutableLiveData<List<Group>> liveGroupMember = new MutableLiveData<>();
    private MutableLiveData<List<Event>> liveEventAll = new MutableLiveData<>();
    private MutableLiveData<List<Chat>> liveUserChats = new MutableLiveData<>();

    private MutableLiveData<SelectDate> selectDateFilter = new MutableLiveData<>();

    public AppViewModel(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
    }

    //Get User
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
            for(Map.Entry<String,Boolean> entry : group.getIdEvents().entrySet()){
                eventIds.add(entry.getKey());
            }
        }
        if(eventIds.size()>0){
            firebaseDatabase.loadEvents(eventIds,liveEventAll);
        }
    }

    public void initUserChats(){

        if(liveUserChats == null){
            liveUserChats = new MutableLiveData<>();
            firebaseDatabase.loadChatUsers(liveUser.getValue(),liveUserChats);
        }
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
    public void filterFeedCalendar(int year,int month, int day) {
        SelectDate selectDate = new SelectDate(year,month,day);
        selectDateFilter.setValue(selectDate);
    }

    public MutableLiveData<SelectDate> getSelectDateFilter() {
        return selectDateFilter;
    }



}

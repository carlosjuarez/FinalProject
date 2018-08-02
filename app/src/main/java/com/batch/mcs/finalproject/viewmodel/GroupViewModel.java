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

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupViewModel extends ViewModel {

    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;
    private MutableLiveData<User> liveUser = new MutableLiveData<>();
    private MutableLiveData<Group> liveGroup;
    private MutableLiveData<List<Group>> liveGroupAdmin = new MutableLiveData<>();
    private MutableLiveData<List<Group>> liveGroupMember = new MutableLiveData<>();
    private MutableLiveData<List<Group>> liveGroupAll ;
    private MutableLiveData<List<Event>> liveEventAll = new MutableLiveData<>();
    private MutableLiveData<Event> liveEvent;
    private MutableLiveData<Chat> liveChat;
    private MutableLiveData<Message> liveMessage;
    private MutableLiveData<List<Chat>> liveUserChats = new MutableLiveData<>();
    private MutableLiveData<SelectDate> selectDateFilter = new MutableLiveData<>();

    public GroupViewModel(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
    }

    public void initUser(String userId) {
        if (userId != null && !userId.isEmpty()){
            firebaseDatabase.loadUser(userId, liveUser);
        }
    }

    public void initGroup(String idGroup){
        firebaseDatabase.loadGroup(idGroup, liveGroup);
    }

    public void initAllEvents() {
        firebaseDatabase.loadEventsAll(liveUser.getValue(),liveEventAll);
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

    public void initUserChats(){

        if(liveUserChats == null){
            liveUserChats = new MutableLiveData<>();
            firebaseDatabase.loadChatUsers(liveUser.getValue(),liveUserChats);
        }
//        if(liveChat == null){
//            liveChat = new MutableLiveData<>();
//            firebaseDatabase.loadChat(liveUser.getValue().getId(), liveChat);
//        }
//
//        if(liveMessage == null){
//            liveMessage = new MutableLiveData<>();
//            firebaseDatabase.loadMessage(liveChat.getValue().getId(), liveMessage);
//        }
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

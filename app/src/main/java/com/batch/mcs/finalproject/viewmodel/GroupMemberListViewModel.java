package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class GroupMemberListViewModel extends ViewModel {
    private MutableLiveData<List<User>> liveGroupMember= new MutableLiveData<>();
    private MutableLiveData<User> liveUser= new MutableLiveData<>();

    public void getMembers(){
        ArrayList<User> memberList= new ArrayList<>();
        liveGroupMember.setValue(memberList);
    }

    public void createChat(User targetUser){

    }

    public MutableLiveData<List<User>> getLiveGroupMember() {
        return liveGroupMember;
    }
}

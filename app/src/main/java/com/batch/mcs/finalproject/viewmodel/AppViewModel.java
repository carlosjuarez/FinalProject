package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.ArrayMap;
import android.view.View;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.Map;

public class AppViewModel extends ViewModel {

    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;
    private MutableLiveData<User> liveUser;


    public void init(String userId) {
        if(liveUser == null){
            firebaseFirestore= FirebaseFirestore.getInstance();
            firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
            liveUser = new MutableLiveData<>();
            firebaseDatabase.loadUser(userId, liveUser);
        }
    }

    public void saveGroup(Group group){
        String gId = firebaseDatabase.createGroup(liveUser.getValue(),group);
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

    public void updateliveUser(User user){
        liveUser.setValue(user);
        firebaseDatabase.updateUser(user);
    }

    public MutableLiveData<User> getLiveUser() {
        return liveUser;
    }


}

package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.ArrayMap;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class CreateGroupViewModel extends ViewModel {
    public String groupName = "";
    public String groupDescription = "";
    private User admin;
    public Bitmap groupPicture = null;
    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;


    private boolean validateData() {
        if (groupName.isEmpty() || groupDescription.isEmpty()) {
            return false;
        }
        return true;
    }

    public void saveGroup(Group group){
        firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
        String gId = firebaseDatabase.saveGroup(admin,group);
        if(admin.getMyGroups()!=null){
            admin.getMyGroups().put(gId,true);
        }else{
            Map<String,Boolean> map = new ArrayMap<String, Boolean>();
            map.put(gId,true);
            admin.setMyGroups(map);
        }
        updateliveUser(admin);

    }

    public void updateliveUser(User user){
        firebaseDatabase.updateUser(admin);
    }

    public void setAdmin(User user)
    {
        admin= user;
    }



    public void createNewGroup(){
        Group newGroup= new Group();
        newGroup.setName(groupName);
        newGroup.setDescription(groupDescription);
        newGroup.setIdAdmin(admin.getId());
        saveGroup(newGroup);

    }
}

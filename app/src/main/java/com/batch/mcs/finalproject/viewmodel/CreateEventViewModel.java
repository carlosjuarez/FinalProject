package com.batch.mcs.finalproject.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;
import android.util.ArrayMap;

import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class CreateEventViewModel extends ViewModel {

    public String groupAdmin="";
    public String eventName= "";
    public String eventCity= "";
    public String eventImage= "";
    public String eventDescription= "";
    public String eventDate= "";
    public String eventLocation= "";
    public int eventPrice= 0;
    public Bitmap groupPicture = null;
    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;
    Group group;

    private boolean validateData() {
        if (eventName.isEmpty() || eventDescription.isEmpty() || eventDate.isEmpty() || eventCity.isEmpty() || eventLocation.isEmpty()) {
            return false;
        }
        return true;
    }

    public void saveEvent(Event event){
        firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseDatabase = new FirebaseDatabase(firebaseFirestore);
        event.setAdminId(group.getId());
        String gId = firebaseDatabase.saveEvent(event);

        if(group.getIdEvents()!=null){
            group.getIdEvents().put(gId, true);
        }else{
            Map<String,Boolean> map = new ArrayMap<String, Boolean>();
            map.put(gId,true);
            group.setIdEvents(map);
        }
        updateliveGroup(group);

    }


    public void updateliveGroup(Group group){
        firebaseDatabase.updateGroup(group);
    }

    public void setGroup(Group group)
    {
        this.group= group;
    }

    public void createNewEvent(){
        Event newEvent= new Event();
        newEvent.setName(eventName);
        newEvent.setDescription(eventDescription);
        newEvent.setCity(eventCity);
        newEvent.setDate(eventDate);
        newEvent.setLocation(eventLocation);
        newEvent.setPrice(eventPrice);
        newEvent.setAdminId(group.getIdAdmin());
        saveEvent(newEvent);

    }
}

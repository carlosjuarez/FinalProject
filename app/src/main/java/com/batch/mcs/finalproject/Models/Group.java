package com.batch.mcs.finalproject.Models;

import android.arch.lifecycle.LiveData;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group extends LiveData implements Parcelable {
    private String name;
    private String id;
    private String image;
    private String description;
    private String idAdmin;
    private List<String> idModerators;
    private List<String> idMembers;
    private List<String> idEvents;

    public Group() {
        //Empty constructor
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected Group(Parcel in) {
        name = in.readString();
        id = in.readString();
        image = in.readString();
        description = in.readString();
        idAdmin = in.readString();
        idModerators = in.createStringArrayList();
        idMembers = in.createStringArrayList();
        idEvents = in.createStringArrayList();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public List<String> getIdModerators() {
        return idModerators;
    }

    public void setIdModerators(List<String> idModerators) {
        this.idModerators = idModerators;
    }

    public List<String> getIdMembers() {
        return idMembers;
    }

    public void setIdMembers(List<String> idMembers) {
        this.idMembers = idMembers;
    }

    public List<String> getIdEvents() {
        return idEvents;
    }

    public void setIdEvents(List<String> idEvents) {
        this.idEvents = idEvents;
    }

    //Livedata
    @Override
    protected void onActive() {
        // Start listening
    }

    @Override
    protected void onInactive() {
        // Stop listening
    }

    //Database
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("description", description);
        result.put("id", id);
        result.put("idAdmin", idAdmin);
        result.put("image", image);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeString(image);
        parcel.writeString(description);
        parcel.writeString(idAdmin);
        parcel.writeStringList(idModerators);
        parcel.writeStringList(idMembers);
        parcel.writeStringList(idEvents);
    }
}

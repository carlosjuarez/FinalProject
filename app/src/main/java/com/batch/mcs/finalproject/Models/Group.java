package com.batch.mcs.finalproject.Models;

import android.arch.lifecycle.LiveData;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Group extends LiveData implements Parcelable {

    private String name;
    private String id;
    private String image;
    private String description;
    private String admin;
    private List<User> moderators;
    private List<User> members;
    private List<Event> events;

    public Group(){
        //Empty constructor
    }

    //All getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getAdmin() {
        return admin;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Event> getEvents() {
        return events;
    }

    //All setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setModerators(List<User> moderators) {
        this.moderators = moderators;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    //Parcelable
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
        parcel.writeString(admin);
        parcel.writeTypedList(moderators);
        parcel.writeTypedList(members);
        parcel.writeTypedList(events);
    }

    protected Group(Parcel in) {
        name = in.readString();
        id = in.readString();
        image = in.readString();
        description = in.readString();
        admin = in.readString();
        moderators = in.createTypedArrayList(User.CREATOR);
        members = in.createTypedArrayList(User.CREATOR);
        events = in.createTypedArrayList(Event.CREATOR);
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

    //Livedata
    @Override
    protected void onActive() {
        // Start listening
    }

    @Override
    protected void onInactive() {
        // Stop listening
    }

}

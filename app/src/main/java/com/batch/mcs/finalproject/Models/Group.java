package com.batch.mcs.finalproject.Models;

import android.arch.lifecycle.LiveData;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Group extends LiveData implements Parcelable {

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
    private String name;
    private String id;
    private String image;
    private String description;
    private String admin;
    private List<User> moderators;
    private List<User> members;
    private List<Event> events;

    public Group() {
        //Empty constructor
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

    //All getters
    public String getName() {
        return name;
    }

    //All setters
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public void setModerators(List<User> moderators) {
        this.moderators = moderators;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Event> getEvents() {
        return events;
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

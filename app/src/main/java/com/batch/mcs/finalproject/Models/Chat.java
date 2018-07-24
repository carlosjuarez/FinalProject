package com.batch.mcs.finalproject.Models;

import android.arch.lifecycle.LiveData;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Chat extends LiveData implements Parcelable {

    private String admin;
    private String id;
    private List<User> members;
    private List<Message> messages;

    public Chat(){
        //Empty constructor
    }

    //All getters
    public String getAdmin() {
        return admin;
    }

    public String getId() {
        return id;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Message> getMessages() {
        return messages;
    }

    //All setters

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    //Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(admin);
        parcel.writeString(id);
        parcel.writeTypedList(members);
        parcel.writeTypedList(messages);
    }

    protected Chat(Parcel in) {
        admin = in.readString();
        id = in.readString();
        members = in.createTypedArrayList(User.CREATOR);
        messages = in.createTypedArrayList(Message.CREATOR);
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };
}

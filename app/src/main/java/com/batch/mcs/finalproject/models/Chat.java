package com.batch.mcs.finalproject.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

public class Chat implements Parcelable {

    private String admin;
    private String id;
    private Map<String, Boolean> members;
    private Map<String, Boolean> messages;

    public Chat() {
        //Empty constructor
    }

    public Chat(String admin) {
        this.admin = admin;
    }

    protected Chat(Parcel in) {
        admin = in.readString();
        id = in.readString();
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Boolean> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Boolean> members) {
        this.members = members;
    }

    public Map<String, Boolean> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, Boolean> messages) {
        this.messages = messages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("admin", admin);
        result.put("id", id);
        return result;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(admin);
        dest.writeString(id);
    }
}

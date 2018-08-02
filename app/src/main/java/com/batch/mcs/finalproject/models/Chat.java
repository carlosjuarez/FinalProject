package com.batch.mcs.finalproject.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

public class Chat implements Parcelable {

    private String admin;
    private String adminName;
    private String member;
    private String memberName;
    boolean newMessage;
    private String id;
    private Map<String, Boolean> messages = null;

    public Chat() {
        //Empty constructor
    }

    protected Chat(Parcel in) {
        admin = in.readString();
        adminName = in.readString();
        member = in.readString();
        memberName = in.readString();
        newMessage = in.readByte() != 0;
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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public boolean isNewMessage() {
        return newMessage;
    }

    public void setNewMessage(boolean newMessage) {
        this.newMessage = newMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(admin);
        dest.writeString(adminName);
        dest.writeString(member);
        dest.writeString(memberName);
        dest.writeByte((byte) (newMessage ? 1 : 0));
        dest.writeString(id);
    }
}

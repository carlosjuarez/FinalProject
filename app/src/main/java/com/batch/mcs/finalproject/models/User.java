package com.batch.mcs.finalproject.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

public class User implements Parcelable {
    private String name;
    private String lastName;
    private String city;
    private String image;
    private String email;
    private String id;
    private Map<String, Boolean> myGroups = null;
    private Map<String, Boolean> groups = null;

    public User() {
        //Empty constructor
    }

    public User(String name, String lastName, String city, String email) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
    }

    protected User(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        city = in.readString();
        image = in.readString();
        email = in.readString();
        id = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Boolean> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(Map<String, Boolean> myGroups) {
        this.myGroups = myGroups;
    }

    public Map<String, Boolean> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, Boolean> groups) {
        this.groups = groups;
    }

    //Database
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("lastName", lastName);
        result.put("id", id);
        result.put("email", email);
        result.put("city", city);
        result.put("image", image);

        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", myGroups=" + myGroups +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(city);
        dest.writeString(image);
        dest.writeString(email);
        dest.writeString(id);
    }
}
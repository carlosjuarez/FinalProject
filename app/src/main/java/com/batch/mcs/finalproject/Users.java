package com.batch.mcs.finalproject;

import android.arch.lifecycle.LiveData;
import android.os.Parcel;
import android.os.Parcelable;

public class Users extends LiveData implements Parcelable{

    private String name;
    private String lastName;
    private String city;
    private String image;
    private String email;
    private String id;

    //All the getters
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    //All the setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(lastName);
        parcel.writeString(city);
        parcel.writeString(image);
        parcel.writeString(email);
        parcel.writeString(id);
    }

    protected Users(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        city = in.readString();
        image = in.readString();
        email = in.readString();
        id = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    //Override livedata methods
    @Override
    protected void onActive() {
        // Start listening
    }

    @Override
    protected void onInactive() {
        // Stop listening
    }
}

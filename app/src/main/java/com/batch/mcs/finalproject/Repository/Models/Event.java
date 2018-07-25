package com.batch.mcs.finalproject.Repository.Models;

import android.arch.lifecycle.LiveData;
import android.os.Parcel;
import android.os.Parcelable;

public class Event extends LiveData implements Parcelable {

    private String name;
    private String adminId;
    private String city;
    private String image;
    private String description;
    private String id;
    private String date;
    private String location;
    private enum privacy{PUBLIC, RESTRICTED, PRIVATE};
    private int price;

    public Event(){
        //Empty constructor
    }

    //All getters
    public String getName() {
        return name;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getPrice() {
        return price;
    }

    //All setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    //Parcelable

    protected Event(Parcel in) {
        name = in.readString();
        adminId = in.readString();
        city = in.readString();
        image = in.readString();
        description = in.readString();
        id = in.readString();
        date = in.readString();
        location = in.readString();
        price = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(adminId);
        parcel.writeString(city);
        parcel.writeString(image);
        parcel.writeString(description);
        parcel.writeString(id);
        parcel.writeString(date);
        parcel.writeString(location);
        parcel.writeInt(price);
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

package com.batch.mcs.finalproject.models;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class Event implements Parcelable {

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
    private String name;
    private String adminId;
    private String city;
    private String image;
    private String description;
    private String id;
    private String date;
    private String location;
    private int price;


    public Event() {
        //Empty constructor
    }

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

    @BindingAdapter("eventImage")
    public static void loadImage(ImageView view, String imageUrl){
        Glide.with(view.getContext())
        .load(imageUrl).into(view);
    }


    //All getters
    public String getName() {
        return name;
    }

    //All setters
    public void setName(String name) {
        this.name = name;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    //Parcelable

    public void setPrice(int price) {
        this.price = price;
    }

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

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("description", description);
        result.put("adminId", adminId);
        result.put("id", id);
        result.put("date", date);
        result.put("city", city);
        result.put("image", image);
        result.put("location", location);
        result.put("price", price);

        return result;
    }


}

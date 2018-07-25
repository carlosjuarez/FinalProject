package com.batch.mcs.finalproject.Repository.Models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import java.util.List;

public class User extends FirebaseListener implements Parcelable{

    private String name;
    private String lastName;
    private String city;
    private String image;
    private String email;
    private String id;
    private List<Chat> chats;
    private Query query;
    private final RealtimeListener listener = new RealtimeListener();

    public User(Query query) {
        this.query = query;
        listener.setQuery(query);

    }

    public User(DatabaseReference ref) {
        this.query = ref;
        listener.setQuery(query);
    }

    public User(){
        //Empty constructor
    }

    //All the getters
    public List<Chat> getChats() {
        return chats;
    }

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

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    //Parcelable
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
        parcel.writeTypedList(chats);
    }

    protected User(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        city = in.readString();
        image = in.readString();
        email = in.readString();
        id = in.readString();
        chats = in.createTypedArrayList(Chat.CREATOR);
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

    //Livedata
    @Override
    protected void onActive() {
        query.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        query.removeEventListener(listener);
    }


}




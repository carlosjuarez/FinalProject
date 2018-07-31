package com.batch.mcs.finalproject.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

public class Group implements Parcelable {
    private String name;
    private String id;
    private String image;
    private String description;
    private String idAdmin;
    private Map<String, Boolean>  idModerators = null;
    private Map<String, Boolean>  idMembers = null;
    private Map<String, Boolean> idEvents = null;

    public Group() {
        //Empty constructor
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected Group(Parcel in) {
        name = in.readString();
        id = in.readString();
        image = in.readString();
        description = in.readString();
        idAdmin = in.readString();
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

    public String getName() {
        return name;
    }

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

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Map<String, Boolean> getIdModerators() {
        return idModerators;
    }

    public void setIdModerators(Map<String, Boolean> idModerators) {
        this.idModerators = idModerators;
    }

    public Map<String, Boolean> getIdMembers() {
        return idMembers;
    }

    public void setIdMembers(Map<String, Boolean> idMembers) {
        this.idMembers = idMembers;
    }

    public Map<String, Boolean> getIdEvents() {
        return idEvents;
    }

    public void setIdEvents(Map<String, Boolean> idEvents) {
        this.idEvents = idEvents;
    }

    //Database
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("description", description);
        result.put("id", id);
        result.put("idAdmin", idAdmin);
        result.put("image", image);

        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(image);
        dest.writeString(description);
        dest.writeString(idAdmin);
    }
}

package com.example.vstorage.network.dto;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("Name")
    private String name;

    private String description;

    @SerializedName("Data")
    private String data;

    @SerializedName("icon")
    private android.media.Image image;

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("ParentObjectId")
    private String parentObjectId;

    @SerializedName("TypeId")
    private int type;

    public Image() {}

    public Image(String name, String description, String data, android.media.Image image, String objectId, String parentObjectId) {
        this.name = name;
        this.description = description;
        this.data = data;
        this.image = image;
        this.objectId = objectId;
        this.parentObjectId = parentObjectId;
    }

    public Image(String name, String data, String parentObjectId, String objectId, int type) {
        this.name = name;
        this.data = data;
        this.objectId = objectId;
        this.parentObjectId = parentObjectId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public android.media.Image getImage() {
        return image;
    }

    public void setImage(android.media.Image image) {
        this.image = image;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(String parentObjectId) {
        this.parentObjectId = parentObjectId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

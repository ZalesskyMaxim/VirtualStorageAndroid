package com.example.vstorage.network.dto;

import com.google.gson.annotations.SerializedName;

public class Folder {
    @SerializedName("Name")
    private String name;

    private String description;

    @SerializedName("IsRootFolder")
    private Boolean isRootFolder;

    @SerializedName("icon")
    private Image image;

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("ParentObjectId")
    private String parentObjectId;

    @SerializedName("TypeId")
    private int type;

    public Folder() {}

    public Folder(String name, String description, Boolean isRootFolder, Image image, String objectId, String parentObjectId) {
        this.name = name;
        this.description = description;
        this.isRootFolder = isRootFolder;
        this.image = image;
        this.objectId = objectId;
        this.parentObjectId = parentObjectId;
    }

    public Folder(String name, Boolean isRootFolder, String parentObjectId, String objectId, int type) {
        this.name = name;
        this.isRootFolder = isRootFolder;
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

    public Boolean getRootFolder() {
        return isRootFolder;
    }

    public void setRootFolder(Boolean rootFolder) {
        isRootFolder = rootFolder;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
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

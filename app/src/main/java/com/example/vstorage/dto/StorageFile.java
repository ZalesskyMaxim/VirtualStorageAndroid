package com.example.vstorage.dto;

import java.util.ArrayList;

public class StorageFile {
    private String name;
    private String description;
    private String data;
    private Boolean isFolder;
    private Boolean isRootFolder;
    private String parentObjectId;
    private String objectId;
    private int type;

    private ArrayList<StorageFile> parentList;
    private ArrayList<StorageFile> childrenList;

    public StorageFile() {}

    public StorageFile(String name, String description) {
        this.name = name;
        this.description = description;
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

    public Boolean getFolder() {
        return isFolder;
    }

    public void setFolder(Boolean folder) {
        isFolder = folder;
    }

    public String getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(String parentObjectId) {
        this.parentObjectId = parentObjectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Boolean getRootFolder() {
        return isRootFolder;
    }

    public void setRootFolder(Boolean rootFolder) {
        isRootFolder = rootFolder;
    }
}

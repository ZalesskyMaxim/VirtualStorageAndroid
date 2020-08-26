package com.example.vstorage.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "folder")
public class FolderEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IdFolder")
    private int idFolder;

    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "IsRootForlder")
    private Boolean isRootFolder;
    @ColumnInfo(name = "ParentObjectId")
    private String parentObjectId;
    @ColumnInfo(name = "ObjectId")
    private String objectId;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "Icon")
    private byte[] icon;
    @ColumnInfo(name = "TypeId")
    private int typeId;

    @Ignore
    public FolderEntity(String name, Boolean isRootFolder, String parentObjectId, String objectId, byte[] icon) {
        this.name = name;
        this.isRootFolder = isRootFolder;
        this.parentObjectId = parentObjectId;
        this.objectId = objectId;
        this.icon = icon;
    }

    public FolderEntity(String name, Boolean isRootFolder, String parentObjectId, String objectId, int typeId) {
        this.name = name;
        this.isRootFolder = isRootFolder;
        this.parentObjectId = parentObjectId;
        this.objectId = objectId;
        this.typeId = typeId;
    }

    public int getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(int idFolder) {
        this.idFolder = idFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRootFolder() {
        return isRootFolder;
    }

    public void setRootFolder(Boolean rootFolder) {
        isRootFolder = rootFolder;
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

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}

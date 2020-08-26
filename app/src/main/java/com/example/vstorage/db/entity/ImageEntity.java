package com.example.vstorage.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "image")
public class ImageEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IdImage")
    private int idImage;

    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Data")
    private String data;
    @ColumnInfo(name = "ParentObjectId")
    private String parentObjectId;
    @ColumnInfo(name = "ObjectId")
    private String objectId;
    @ColumnInfo(name = "TypeId")
    private int typeId;

    public ImageEntity(String name, String data, String parentObjectId, String objectId, int typeId) {
        this.name = name;
        this.data = data;
        this.parentObjectId = parentObjectId;
        this.objectId = objectId;
        this.typeId = typeId;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}

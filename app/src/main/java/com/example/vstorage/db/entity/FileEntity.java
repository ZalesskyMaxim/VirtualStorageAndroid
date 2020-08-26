package com.example.vstorage.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "file")
public class FileEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IdFile")
    private int idFile;

    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Data")
    private String data;
    @ColumnInfo(name = "ParentObjectId")
    private String parentObjectId;
    @ColumnInfo(name = "ObjectId")
    private String objectId;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "Icon")
    private byte[] icon;
    @ColumnInfo(name = "TypeId")
    private int typeId;

    @Ignore
    public FileEntity(String name, String data, String parentObjectId, String objectId, byte[] icon) {
        this.name = name;
        this.data = data;
        this.parentObjectId = parentObjectId;
        this.objectId = objectId;
        this.icon = icon;
    }

    public FileEntity(String name, String data, String parentObjectId, String objectId, int typeId) {
        this.name = name;
        this.data = data;
        this.parentObjectId = parentObjectId;
        this.objectId = objectId;
        this.typeId = typeId;
    }

    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
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

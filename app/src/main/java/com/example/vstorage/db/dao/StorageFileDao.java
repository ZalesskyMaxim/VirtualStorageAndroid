package com.example.vstorage.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.vstorage.dto.StorageFile;

import java.util.List;

@Dao
public interface StorageFileDao {
    @Query("SELECT name AS name, ParentObjectId AS parentObjectId, ObjectId AS objectId, TypeId AS type\n" +
            "FROM file f WHERE f.ParentObjectId == 0\n" +
            "UNION\n" +
            "SELECT name AS name, ParentObjectId AS parentObjectId, ObjectId AS objectId, TypeId AS type\n" +
            "FROM folder fl WHERE fl.ParentObjectId == 0\n" +
            "UNION\n" +
            "SELECT name AS name, ParentObjectId AS parentObjectId, ObjectId AS objectId, TypeId AS type\n" +
            "FROM image i WHERE i.ParentObjectId == 0")
    LiveData<List<StorageFile>> getAllRootFiles();

    @Query("SELECT Data FROM image WHERE ObjectId==:objectId")
    LiveData<String> getImageUrl(String objectId);

    @Query("SELECT Data FROM file WHERE ObjectId==:objectId")
    LiveData<String> getFileText(String objectId);

    @Query("SELECT name AS name, ParentObjectId AS parentObjectId, ObjectId AS objectId, TypeId AS type\n" +
            "FROM file f WHERE f.ParentObjectId == :objectId\n" +
            "UNION\n" +
            "SELECT name AS name, ParentObjectId AS parentObjectId, ObjectId AS objectId, TypeId AS type\n" +
            "FROM folder fl WHERE fl.ParentObjectId == :objectId\n" +
            "UNION\n" +
            "SELECT name AS name, ParentObjectId AS parentObjectId, ObjectId AS objectId, TypeId AS type\n" +
            "FROM image i WHERE i.ParentObjectId == :objectId")
    LiveData<List<StorageFile>> getAllFilesById(String objectId);
}

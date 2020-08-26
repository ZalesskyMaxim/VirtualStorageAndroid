package com.example.vstorage.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vstorage.db.entity.FolderEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FolderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFolders(ArrayList<FolderEntity> folder);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFolder(FolderEntity folder);

    @Update
    void updateFolder(FolderEntity folder);

    @Delete
    void deleteFolder(FolderEntity folder);

    @Query("DELETE FROM folder")
    void deleteAllFolders();

    @Query("SELECT * FROM folder ORDER BY IdFolder DESC")
    LiveData<List<FolderEntity>> getAllFolders();
}

package com.example.vstorage.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vstorage.db.entity.FileEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFiles(ArrayList<FileEntity> file);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFile(FileEntity file);

    @Update
    void updateFile(FileEntity file);

    @Delete
    void deleteFile(FileEntity file);

    @Query("DELETE FROM file")
    void deleteAllFiles();

    @Query("SELECT * FROM file ORDER BY IdFile DESC")
    LiveData<List<FileEntity>> getAllFiles();
}

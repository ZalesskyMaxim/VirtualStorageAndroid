package com.example.vstorage.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vstorage.db.entity.ImageEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImages(ArrayList<ImageEntity> images);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(ImageEntity image);

    @Update
    void updateImage(ImageEntity image);

    @Delete
    void deleteImage(ImageEntity image);

    @Query("DELETE FROM image")
    void deleteAllImages();

    @Query("SELECT * FROM image ORDER BY IdImage DESC")
    LiveData<List<ImageEntity>> getAllImages();
}

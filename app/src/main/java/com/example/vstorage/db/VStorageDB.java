package com.example.vstorage.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vstorage.db.dao.FileDao;
import com.example.vstorage.db.dao.FolderDao;
import com.example.vstorage.db.dao.ImageDao;
import com.example.vstorage.db.dao.StorageFileDao;
import com.example.vstorage.db.entity.FileEntity;
import com.example.vstorage.db.entity.FolderEntity;
import com.example.vstorage.db.entity.ImageEntity;

@Database(entities = {FileEntity.class, FolderEntity.class, ImageEntity.class}, version = 1, exportSchema = true)
public abstract class VStorageDB extends RoomDatabase {
    private static volatile VStorageDB dbInstance;
    private static final String DB_NAME = "StorageDB";

    public abstract FileDao fileDao();
    public abstract FolderDao folderDao();
    public abstract ImageDao imageDao();
    public abstract StorageFileDao storageFileDao();

    @NonNull
    public static VStorageDB getInstance(Context context) {
        VStorageDB localInstance = dbInstance;
        if (localInstance == null) {
            synchronized (VStorageDB.class) {
                localInstance = dbInstance;
                if (localInstance == null) {
                    dbInstance = localInstance = Room.databaseBuilder(context.getApplicationContext(), VStorageDB.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return localInstance;
    }

}

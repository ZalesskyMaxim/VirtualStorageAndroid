package com.example.vstorage.db.mapper;

import com.example.vstorage.db.entity.FileEntity;
import com.example.vstorage.dto.StorageFile;
import com.example.vstorage.network.dto.File;

import java.util.ArrayList;

public class FileMapper {

    public static FileEntity toEntity(File file) {
        return new FileEntity(file.getName(), file.getData(), file.getParentObjectId(), file.getObjectId(), file.getType());
    }

    public static FileEntity toEntity(StorageFile storageFile) {
        return new FileEntity(storageFile.getName(), storageFile.getData(), storageFile.getParentObjectId(), storageFile.getObjectId(), storageFile.getType());
    }

    public static File toObject(FileEntity fileEntity) {
        return new File(fileEntity.getName(), fileEntity.getData(), fileEntity.getParentObjectId(), fileEntity.getObjectId(), fileEntity.getTypeId());
    }

    public static File toObject(StorageFile storageFile) {
        return new File(storageFile.getName(), storageFile.getData(), storageFile.getParentObjectId(), storageFile.getObjectId(), storageFile.getType());
    }

    public static ArrayList<FileEntity> toEntityList(ArrayList<File> files){
        ArrayList<FileEntity> list = new ArrayList<>();
        for (File file : files) {
            list.add(new FileEntity(file.getName(), file.getData(), file.getParentObjectId(), file.getObjectId(), file.getType()));
        }
        return list;
    }

    public static ArrayList<File> toObjectList(ArrayList<FileEntity> fileEntities){
        ArrayList<File> list = new ArrayList<>();
        for (FileEntity fileEntity : fileEntities) {
            list.add(new File(fileEntity.getName(), fileEntity.getData(), fileEntity.getParentObjectId(), fileEntity.getObjectId(), fileEntity.getTypeId()));
        }
        return list;
    }
}

package com.example.vstorage.db.mapper;

import com.example.vstorage.db.entity.FolderEntity;
import com.example.vstorage.dto.StorageFile;
import com.example.vstorage.network.dto.Folder;

import java.util.ArrayList;

public class FolderMapper {
    public static FolderEntity toEntity(Folder folder) {
        return new FolderEntity(folder.getName(), folder.getRootFolder(), folder.getParentObjectId(), folder.getObjectId(), folder.getType());
    }

    public static FolderEntity toEntity(StorageFile storageFolder) {
        return new FolderEntity(storageFolder.getName(), storageFolder.getRootFolder(), storageFolder.getParentObjectId(), storageFolder.getObjectId(), storageFolder.getType());
    }

    public static Folder toObject(FolderEntity folderEntity) {
        return new Folder(folderEntity.getName(), folderEntity.getRootFolder(), folderEntity.getParentObjectId(), folderEntity.getObjectId(), folderEntity.getTypeId());
    }

    public static Folder toObject(StorageFile strorageFolder) {
        return new Folder(strorageFolder.getName(), strorageFolder.getRootFolder(), strorageFolder.getParentObjectId(), strorageFolder.getObjectId(), strorageFolder.getType());
    }

    public static ArrayList<FolderEntity> toEntityList(ArrayList<Folder> folders){
        ArrayList<FolderEntity> list = new ArrayList<>();
        for (Folder folder : folders) {
            list.add(new FolderEntity(folder.getName(), folder.getRootFolder(), folder.getParentObjectId(), folder.getObjectId(), folder.getType()));
        }
        return list;
    }

    public static ArrayList<Folder> toObjectList(ArrayList<FolderEntity> folderEntities){
        ArrayList<Folder> list = new ArrayList<>();
        for (FolderEntity folderEntity : folderEntities) {
            list.add(new Folder(folderEntity.getName(), folderEntity.getRootFolder(), folderEntity.getParentObjectId(), folderEntity.getObjectId(), folderEntity.getTypeId()));
        }
        return list;
    }
}

package com.example.vstorage.db.mapper;

import com.example.vstorage.db.entity.ImageEntity;
import com.example.vstorage.dto.StorageFile;
import com.example.vstorage.network.dto.Image;

import java.util.ArrayList;

public class ImageMapper {
    public static ImageEntity toEntity(Image image) {
        return new ImageEntity(image.getName(), image.getData(), image.getParentObjectId(), image.getObjectId(), image.getType());
    }

    public static ImageEntity toEntity(StorageFile storageImage) {
        return new ImageEntity(storageImage.getName(), storageImage.getData(), storageImage.getParentObjectId(), storageImage.getObjectId(), storageImage.getType());
    }

    public static Image toObject(ImageEntity imageEntity) {
        return new Image(imageEntity.getName(), imageEntity.getData(), imageEntity.getParentObjectId(), imageEntity.getObjectId(), imageEntity.getTypeId());
    }

    public static Image toObject(StorageFile storageImage) {
        return new Image(storageImage.getName(), storageImage.getData(), storageImage.getParentObjectId(), storageImage.getObjectId(), storageImage.getType());
    }

    public static ArrayList<ImageEntity> toEntityList(ArrayList<Image> images){
        ArrayList<ImageEntity> list = new ArrayList<>();
        for (Image image : images) {
            list.add(new ImageEntity(image.getName(), image.getData(), image.getParentObjectId(), image.getObjectId(), image.getType()));
        }
        return list;
    }

    public static ArrayList<Image> toObjectList(ArrayList<ImageEntity> imageEntities){
        ArrayList<Image> list = new ArrayList<>();
        for (ImageEntity imageEntity : imageEntities) {
            list.add(new Image(imageEntity.getName(), imageEntity.getData(), imageEntity.getParentObjectId(), imageEntity.getObjectId(), imageEntity.getTypeId()));
        }
        return list;
    }
}

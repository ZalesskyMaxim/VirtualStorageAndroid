package com.example.vstorage.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vstorage.db.repository.StorageRepository;
import com.example.vstorage.network.dto.File;
import com.example.vstorage.network.dto.Folder;
import com.example.vstorage.network.dto.Image;
import com.example.vstorage.dto.StorageFile;

import java.util.ArrayList;
import java.util.List;

public class BaseViewModel extends AndroidViewModel {

    private StorageRepository repository;
    private LiveData<List<StorageFile>> getAllFiles;
    private LiveData<String> imageUrl;
    BaseViewModel(@NonNull Application application) {
        super(application);
        repository = new StorageRepository(application);
        getAllFiles = repository.getGetAllRootFiles();

    }

    void insertFiles(ArrayList<File> files) {
        repository.insertFiles(files);
    }

    void insertFile(StorageFile file) {
        repository.insertFile(file);
    }

    void insertFolders(ArrayList<Folder> folders){
        repository.insertFolders(folders);
    }

    void insertFolder(StorageFile folder){
        repository.insertFolder(folder);
    }

    void insertImages(ArrayList<Image> images){
        repository.insertImages(images);
    }

    void insertImage(StorageFile image){
        repository.insertImage(image);
    }

    LiveData<List<StorageFile>> getAllRootFiles() {
        return getAllFiles;
    }

    LiveData<String> getImageUrl(String imageObjectId)
    {
        return repository.getImageUrl(imageObjectId);
    }

    LiveData<String> getFileText(String textObjectId)
    {
        return repository.getFileText(textObjectId);
    }
}

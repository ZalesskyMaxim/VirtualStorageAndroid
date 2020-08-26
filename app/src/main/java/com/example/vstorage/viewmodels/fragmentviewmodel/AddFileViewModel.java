package com.example.vstorage.viewmodels.fragmentviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.vstorage.dto.StorageFile;

public class AddFileViewModel extends AndroidViewModel {
    private MutableLiveData<StorageFile> fileMutableLiveData;
    private String folderId;
    public AddFileViewModel(@NonNull Application application) {
        super(application);
        fileMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<StorageFile> getFileMutableLiveData() {
        return fileMutableLiveData;
    }

    public void postFile(StorageFile file) {
        fileMutableLiveData.postValue(file);
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }
}

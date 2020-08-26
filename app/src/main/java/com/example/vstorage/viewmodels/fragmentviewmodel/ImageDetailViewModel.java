package com.example.vstorage.viewmodels.fragmentviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ImageDetailViewModel extends AndroidViewModel {
    private MutableLiveData<String> imageUrlMutableLiveData;
    public ImageDetailViewModel(@NonNull Application application) {
        super(application);
        imageUrlMutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<String> getImageUrlMutableLiveData() {
        return imageUrlMutableLiveData;
    }

    public void postValue(String url) {
        imageUrlMutableLiveData.postValue(url);
    }
}

package com.example.vstorage.viewmodels.fragmentviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class TextDetailViewModel extends AndroidViewModel {
    private MutableLiveData<String> textMutableLiveData;
    public TextDetailViewModel(@NonNull Application application) {
        super(application);
        textMutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<String> getTextMutableLiveData() {
        return textMutableLiveData;
    }

    public void postValue(String data) {
        textMutableLiveData.postValue(data);
    }
}

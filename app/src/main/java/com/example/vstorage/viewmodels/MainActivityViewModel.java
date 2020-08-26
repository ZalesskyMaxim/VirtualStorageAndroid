package com.example.vstorage.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vstorage.dto.StorageFile;
import com.example.vstorage.listener.Action;
import com.example.vstorage.network.dto.File;
import com.example.vstorage.network.dto.Folder;
import com.example.vstorage.network.dto.Image;
import com.example.vstorage.preferences.AppPreferences;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

import static com.example.vstorage.network.ApiService.getApiServiceInstance;

public class MainActivityViewModel extends BaseViewModel {
    private MutableLiveData<ArrayList<File>> allFiles;
    private LiveData<List<StorageFile>> allRootFiles;
    private LiveData<String> imageUrl;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        allRootFiles = getAllRootFiles();
    }

    public boolean isLogged() {
        return !AppPreferences.getToken().isEmpty();
    }

    public void logout(Action<Boolean> logoutAction) {
        getApiServiceInstance().logout(new Action<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody v) {
                if (logoutAction != null) { //TODO: use LiveData object
                    AppPreferences.clearToken();
                    logoutAction.onSuccess(true);
                }
            }

            @Override
            public void onError(String error) {

            }
        });

    }

    public LiveData<List<StorageFile>> getAllFiles() {
        return allRootFiles;
    }

    public LiveData<String> getUrl(String imageObjectId) {
        return getImageUrl(imageObjectId);
    }

    public LiveData<String> getText(String textObjectId) {
        return getFileText(textObjectId);
    }

    private void requestData() {
        getApiServiceInstance().getRootFiles(new Action<ArrayList<File>>() {
            @Override
            public void onSuccess(ArrayList<File> model) {
                allFiles.setValue(model);
            }
        });
    }

    public void downloadAllData(){
        getApiServiceInstance().getAllFolders(new Action<ArrayList<Folder>>() {
            @Override
            public void onSuccess(ArrayList<Folder> model) {
                insertFolders(model);

                getApiServiceInstance().getAllFiles(new Action<ArrayList<File>>() {
                    @Override
                    public void onSuccess(ArrayList<File> model) {
                        insertFiles(model);
                    }
                });

                getApiServiceInstance().getAllImages(new Action<ArrayList<Image>>() {
                    @Override
                    public void onSuccess(ArrayList<Image> model) {
                        insertImages(model);
                    }
                });
            }
        });

    }

    public void insertStorageFile(StorageFile storageFile) {
        switch (storageFile.getType()) {
            case 1:
                getApiServiceInstance().insertFolder(storageFile, new Action<Folder>() {
                    @Override
                    public void onSuccess(Folder model) {
                        insertFolder(storageFile);
                    }
                });
                break;
            case 2:
                getApiServiceInstance().insertFile(storageFile, new Action<File>() {
                    @Override
                    public void onSuccess(File model) {
                        insertFile(storageFile);
                    }
                });
                break;
            case 3:
                getApiServiceInstance().insertImage(storageFile, new Action<Image>() {
                    @Override
                    public void onSuccess(Image model) {
                        insertImage(storageFile);
                    }
                });
                break;
            default:
                break;
        }
    }
}

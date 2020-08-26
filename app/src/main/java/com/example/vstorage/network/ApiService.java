package com.example.vstorage.network;

//import android.content.res.Resources;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.vstorage.db.mapper.FileMapper;
import com.example.vstorage.db.mapper.FolderMapper;
import com.example.vstorage.db.mapper.ImageMapper;
import com.example.vstorage.dto.StorageFile;
import com.example.vstorage.listener.Action;
import com.example.vstorage.network.dto.File;
import com.example.vstorage.network.dto.Folder;
import com.example.vstorage.network.dto.Image;
import com.example.vstorage.network.request.LoginRequest;
import com.example.vstorage.network.response.ImageResponse;
import com.example.vstorage.network.response.LoginResponse;
import com.example.vstorage.network.response.LogoutResponse;
import com.example.vstorage.preferences.AppPreferences;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.example.vstorage.VStorageApp;

public class ApiService {
    private static final String BASE_URL = "https://api.backendless.com/254ECC2B-01F5-ADD1-FF6A-7F42FD416A00/E8897374-397C-4760-893E-DF17F12EFC84/";

    private static volatile ApiService apiServiceInstance;
    private StorageApi api;

    @NonNull
    public static ApiService getApiServiceInstance() {
        ApiService localInstance = apiServiceInstance;
        if (localInstance == null) {
            synchronized (ApiService.class) {
                localInstance = apiServiceInstance;
                if (localInstance == null) {
                    apiServiceInstance = localInstance = new ApiService();
                }
            }
        }
        return localInstance;
    }

    private ApiService()
    {
        //Resources resources = VStorageApp.getRes();

        Interceptor clientInterceptor = chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body());

            String token = AppPreferences.getToken();
            if (!token.isEmpty()) {
                requestBuilder.header("user-token", token);
                requestBuilder.header("Authorization", token);
            }

            return chain.proceed(requestBuilder.build());
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(clientInterceptor)
                .build();

        Retrofit retrofitService = new Retrofit.Builder()
                //.baseUrl(resources.getString(R.string.BASE_URL))
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new NullOrEmptyConverterFactory())
                .client(client)
                .build();

        this.api = retrofitService.create(StorageApi.class);
    }

    public void login(LoginRequest loginRequest, Action<LoginResponse> action) {
        api.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (action != null) {
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null){
                        if (response.errorBody() != null) {
                            action.onError(response.errorBody().toString());
                        }
                    }
                    System.out.println("response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }

    public void insertFolder(StorageFile folder, Action<Folder> action) {
        api.insertFolder(FolderMapper.toObject(folder)).enqueue(new Callback<Folder>() {
            @Override
            public void onResponse(Call<Folder> call, Response<Folder> response) {
                if (response.isSuccessful()) {
                    if (action != null) {
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null) {
                        if (response.errorBody() != null) {
                            action.onError(response.errorBody().toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Folder> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }

    public void insertFile(StorageFile file, Action<File> action) {
        api.insertFile(FileMapper.toObject(file)).enqueue(new Callback<File>() {
            @Override
            public void onResponse(Call<File> call, Response<File> response) {
                if (response.isSuccessful()) {
                    if (action != null) {
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null) {
                        if (response.errorBody() != null) {
                            action.onError(response.errorBody().toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<File> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }

    public void uploadImage(String filename, String filePath, Action<ImageResponse> action) {
        java.io.File file = new java.io.File(filePath);
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        String f1 = file.getAbsolutePath();
        String f3 = file.getPath();
        String f4 = file.getParent();
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);

        String imageName = filename + "." + getFileExtension(file);
        api.uploadImage(imageName, part).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    if (action != null) {
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null) {
                        if (response.errorBody() != null) {
                            action.onError(response.errorBody().toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }

    private static String getFileExtension(java.io.File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public void insertImage(StorageFile file, Action<Image> action) {
        api.insertImage(ImageMapper.toObject(file)).enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                if (response.isSuccessful()) {
                    if (action != null) {
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null) {
                        if (response.errorBody() != null) {
                            action.onError(response.errorBody().toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }

    public void logout(Action<ResponseBody> action) {
        Gson gson = new Gson();
        Objects.requireNonNull(api.logout()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (action != null){
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null) {
                        try {
                            if (response.errorBody() != null) {
                                LogoutResponse logoutResponse = gson.fromJson(response.errorBody().string(), LogoutResponse.class);
                                action.onError(logoutResponse.getMessage());
                            }
                        } catch (IOException e) {
                            Log.println(Log.ERROR, e.getMessage(), Objects.requireNonNull(e.getLocalizedMessage()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }

    public void getRootFiles(Action<ArrayList<File>> action) {
        api.getFolders().enqueue(new Callback<ArrayList<File>>() {
            @Override
            public void onResponse(Call<ArrayList<File>> call, retrofit2.Response<ArrayList<File>> response) {
                if (response.isSuccessful()) {
                    if (action != null){
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null){
                        if (response.errorBody() != null) {
                            action.onError(response.errorBody().toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<File>> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }

    public void getAllFolders(Action<ArrayList<Folder>> action) {
        api.getAllFolders().enqueue(new Callback<ArrayList<Folder>>() {
            @Override
            public void onResponse(Call<ArrayList<Folder>> call, retrofit2.Response<ArrayList<Folder>> response) {
                if (response.isSuccessful()) {
                    if (action != null){
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null){
                        if (response.errorBody() != null) {
                            action.onError(response.errorBody().toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Folder>> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }

    public void getAllFiles(Action<ArrayList<File>> action) {
        api.getAllFiles().enqueue(new Callback<ArrayList<File>>() {
            @Override
            public void onResponse(Call<ArrayList<File>> call, retrofit2.Response<ArrayList<File>> response) {
                if (response.isSuccessful()) {
                    if (action != null){
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null){
                        if (response.errorBody() != null) {
                            action.onError(response.errorBody().toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<File>> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }

    public void getAllImages(Action<ArrayList<Image>> action) {
        api.getAllImages().enqueue(new Callback<ArrayList<Image>>() {
            @Override
            public void onResponse(Call<ArrayList<Image>> call, retrofit2.Response<ArrayList<Image>> response) {
                if (response.isSuccessful()) {
                    if (action != null){
                        action.onSuccess(response.body());
                    }
                } else {
                    if (action != null){
                        if (response.errorBody() != null) {
                            action.onError(response.errorBody().toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Image>> call, Throwable t) {
                if (action != null){
                    action.onError(t.getMessage());
                }
            }
        });
    }
}


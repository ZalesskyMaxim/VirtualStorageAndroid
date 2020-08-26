package com.example.vstorage.network;

import androidx.annotation.Nullable;

import com.example.vstorage.network.dto.File;
import com.example.vstorage.network.dto.Folder;
import com.example.vstorage.network.dto.Image;
import com.example.vstorage.network.request.LoginRequest;
import com.example.vstorage.network.response.ImageResponse;
import com.example.vstorage.network.response.LoginResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface StorageApi {
    @POST("users/login")
    Call<LoginResponse> login(@Body LoginRequest model);

    @Nullable
    @GET("users/logout")
    Call<ResponseBody> logout();

    @GET("data/folder")
    Call<ArrayList<File>> getFolders();

    @GET("data/folder")
    Call<ArrayList<Folder>> getAllFolders();

    @GET("data/file")
    Call<ArrayList<File>> getAllFiles();

    @GET("data/image")
    Call<ArrayList<Image>> getAllImages();

    @POST("data/file")
    Call<File> insertFile(@Body File file);

    @POST("data/folder")
    Call<Folder> insertFolder(@Body Folder folder);

    @POST("data/image")
    Call<Image> insertImage(@Body Image image);

    @Multipart
    @POST("files/images/{filename}?overwrite=true")
    Call<ImageResponse> uploadImage(@Path("filename") String filename, @Part MultipartBody.Part file);
}

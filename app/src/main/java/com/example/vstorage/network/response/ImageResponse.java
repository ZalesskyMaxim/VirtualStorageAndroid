package com.example.vstorage.network.response;

import com.google.gson.annotations.SerializedName;

public class ImageResponse {

    @SerializedName("fileURL")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package com.example.vstorage.network.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class LogoutResponse {
    @Nullable
    @SerializedName("code")
    private int code;

    @Nullable
    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

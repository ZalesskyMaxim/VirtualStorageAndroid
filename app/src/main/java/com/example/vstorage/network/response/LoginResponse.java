package com.example.vstorage.network.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("user-token")
    private String userToken;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}

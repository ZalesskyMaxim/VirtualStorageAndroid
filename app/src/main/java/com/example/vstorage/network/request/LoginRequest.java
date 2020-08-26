package com.example.vstorage.network.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("login")
    private String Login;
    @SerializedName("password")
    private String Password;

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

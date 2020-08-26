package com.example.vstorage.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.vstorage.network.request.LoginRequest;
import com.example.vstorage.network.response.LoginResponse;
import com.example.vstorage.listener.Action;
import com.example.vstorage.preferences.AppPreferences;

import static com.example.vstorage.network.ApiService.getApiServiceInstance;

public class LoginViewModel extends BaseViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void logIn(String email, String passw, Action<LoginResponse> listener) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(email);
        loginRequest.setPassword(passw);

        getApiServiceInstance().login(loginRequest, new Action<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponseModel) {
                if (listener != null) {
                    AppPreferences.setToken(loginResponseModel.getUserToken());
                    listener.onSuccess(loginResponseModel);
                }
            }

            @Override
            public void onError(String error) {

            }
        });

    }
}

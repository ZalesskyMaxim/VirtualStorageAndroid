package com.example.vstorage;

import android.app.Application;

import com.example.vstorage.preferences.AppPreferences;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppPreferences.initPreferences(getApplicationContext());
    }
}

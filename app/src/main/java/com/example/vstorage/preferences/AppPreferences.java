package com.example.vstorage.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private static SharedPreferences prefs;

    private static final String APP_PREFERENCES = "SharedStorage";
    private static final String TOKEN_KEY = "TOKEN_KEY";

    public static void initPreferences(Context applicationContext) {
        prefs = applicationContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void setToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public static String getToken() {
        return prefs.getString(TOKEN_KEY, "");
    }

    public static void clearToken() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }
}

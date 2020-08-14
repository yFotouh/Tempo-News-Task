package com.task.temponewstask.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.task.temponewstask.AppClass;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AppPrefs {
    private static AppPrefs appPrefs;
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public AppPrefs() {
        pref = PreferenceManager.getDefaultSharedPreferences(AppClass.Companion.getInstance());
        editor = pref.edit();
    }

    public void saveRefreshToken(String token) {
        editor.putString(REFRESH_TOKEN, token);
        editor.apply();
    }

    public String getRefreshToken() {
        return pref.getString(REFRESH_TOKEN, null);
    }

    public static AppPrefs get() {
        if (appPrefs == null)
            appPrefs = new AppPrefs();
        return appPrefs;
    }

    public <T> void saveAsJson(T data, Class<T> clazz) {
        editor.putString(clazz.getSimpleName(), new Gson().toJson(data));
        editor.apply();
    }

    public <T> T fromJson(Class<T> clazz) {
        String json = pref.getString(clazz.getSimpleName(), "");
        return new Gson().fromJson(json, clazz);
    }
}

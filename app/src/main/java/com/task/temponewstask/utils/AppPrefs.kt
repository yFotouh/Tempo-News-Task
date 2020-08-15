package com.task.temponewstask.utils

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.task.temponewstask.AppClass.Companion.instance

class AppPrefs {
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
    fun saveRefreshToken(token: String?) {
        editor.putString(REFRESH_TOKEN, token)
        editor.apply()
    }

    val refreshToken: String?
        get() = pref.getString(REFRESH_TOKEN, null)

    fun <T> saveAsJson(data: T, clazz: Class<T>) {
        editor.putString(clazz.simpleName, Gson().toJson(data))
        editor.apply()
    }

    fun <T> fromJson(clazz: Class<T>): T {
        val json = pref.getString(clazz.simpleName, "")
        return Gson().fromJson(json, clazz)
    }

    companion object {
        private var appPrefs: AppPrefs? = null
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        fun get(): AppPrefs? {
            if (appPrefs == null) appPrefs = AppPrefs()
            return appPrefs
        }
    }

    init {
        pref = PreferenceManager.getDefaultSharedPreferences(instance)
        editor = pref.edit()
    }
}
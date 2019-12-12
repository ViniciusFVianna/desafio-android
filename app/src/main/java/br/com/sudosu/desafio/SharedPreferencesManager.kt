package br.com.sudosu.desafio

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


class SharedPreferencesManager(private val context: Context) {

    var sharedPrefs: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    /* Write */

    fun putBoolean(key: String, value: Boolean) {
        sharedPrefs.edit().putBoolean(key, value).apply()
    }

    fun putString(key: String, value: String) {
        sharedPrefs.edit().putString(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        sharedPrefs.edit().putInt(key, value).apply()
    }

    fun putFloat(key: String, value: Float) {
        sharedPrefs.edit().putFloat(key, value).apply()
    }

    fun putLong(key: String, value: Long) {
        sharedPrefs.edit().putLong(key, value).apply()
    }

    fun putStringSet(key: String, value: Set<String>) {
        sharedPrefs.edit().putStringSet(key, value).apply()
    }

    /* Read */

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPrefs.getBoolean(key, defaultValue)
    }

    fun getString(key: String, defaultValue: String?): String? {
        return sharedPrefs.getString(key, defaultValue)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPrefs.getInt(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPrefs.getFloat(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return sharedPrefs.getLong(key, defaultValue)
    }

    fun getStringSet(key: String, defaultValue: Set<String>?): Set<String>? {
        return sharedPrefs.getStringSet(key, defaultValue)
    }

    fun clear() {
        sharedPrefs.edit().clear().apply()
    }
}
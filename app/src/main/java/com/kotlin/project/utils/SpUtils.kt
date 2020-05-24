package com.kotlin.project.utils

import android.content.Context
import android.content.SharedPreferences
import com.kotlin.project.BuildConfig
import com.kotlin.project.MApplication

object SpUtils {
    const val SP_PREFIX = "${BuildConfig.APPLICATION_ID}.SHARED_PREF"
    private const val SHARED_PREF = "${SP_PREFIX}_${BuildConfig.BUILD_TYPE}"

    private val prefs: SharedPreferences by lazy {
        MApplication.applicationContext.getSharedPreferences(
            SHARED_PREF, Context.MODE_PRIVATE
        )
    }

    /**
     * get saved value
     */
    @Suppress("UNCHECKED_CAST")
    fun getValue(key: String, default: Any): Any = with(prefs) {
        return when (default) {
            is Int -> getInt(key, default)
            is String -> getString(key, default) ?: ""
            is Long -> getLong(key, default)
            is Float -> getFloat(key, default)
            is Boolean -> getBoolean(key, default)
            else -> throw IllegalArgumentException("SharedPreferences type error")
        }
    }

    fun getString(key: String, default: String = ""): String {
        return getValue(key, default) as String
    }

    fun getInt(key: String, default: Int = 0): Int {
        return getValue(key, default) as Int
    }

    fun getLong(key: String, default: Long = 0): Long {
        return getValue(key, default) as Long
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return getValue(key, default) as Boolean
    }

    fun getFloat(key: String, default: Float = 0f): Float {
        return getValue(key, default) as Float
    }

    /**
     * save value
     */
    fun saveValue(key: String, value: Any) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(key, value)
            is Int -> putInt(key, value)
            is String -> putString(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
            else -> throw IllegalArgumentException("SharedPreferences type error")
        }.apply()
    }

    /**
     * clear all the data saved
     */
    fun clear() {
        prefs.edit().clear().apply()
    }

    /**
     * delete data by key
     */
    fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }
}
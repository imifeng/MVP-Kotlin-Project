package com.kotlin.project.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kotlin.project.net.gson.GsonSerializer
import java.lang.reflect.Type

object GsonUtils {

    private val gson: Gson by lazy {
        GsonBuilder()
            .registerTypeAdapterFactory(GsonSerializer<Any?>())
            .create()
    }

    fun toJson(any: Map<*, *>?): String {
        return gson.toJson(any)
    }

    fun toJson(any: Any?): String {
        if (any == null) {
            return ""
        } else if (any is String && TextUtils.isEmpty(any as String?)) {
            return ""
        }
        return gson.toJson(any)
    }

    fun <T> fromJsonT(json: String?, cls: Class<T>): T {
        return gson.fromJson(json, cls)
    }

    fun fromJson(json: String?, cls: Class<*>): Any {
        return gson.fromJson(json, cls)
    }

    fun <T> fromJsonSafe(json: String?, typeOfT: Type): T? {
        try {
            return gson.fromJson(json, typeOfT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> fromJson(json: String?, type: Type): T {
        return gson.fromJson(json, type)
    }

}
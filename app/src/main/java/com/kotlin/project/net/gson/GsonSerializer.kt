package com.kotlin.project.net.gson

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken

/**
 * @author Finn
 * @date 2020
 */
class GsonSerializer<T> : TypeAdapterFactory {
    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        val rawType = type.rawType as Class<T>
        if (rawType == String::class.java) {
            return StringTypeAdapter() as TypeAdapter<T>
        }
        if (rawType == Long::class.java) {
            return LongTypeAdapter() as TypeAdapter<T>
        }
        if (rawType == Long::class.javaPrimitiveType) {
            return LongTypeAdapter() as TypeAdapter<T>
        }
        if (rawType == Int::class.java) {
            return IntTypeAdapter() as TypeAdapter<T>
        }
        if (rawType == Int::class.javaPrimitiveType) {
            return IntTypeAdapter() as TypeAdapter<T>
        }
        if (rawType == Double::class.java) {
            return DoubleTypeAdapter() as TypeAdapter<T>
        }
        if (rawType == Double::class.javaPrimitiveType) {
            return DoubleTypeAdapter() as TypeAdapter<T>
        }
        if (rawType == Boolean::class.java) {
            return BooleanTypeAdapter() as TypeAdapter<T>
        }
        if (rawType == Boolean::class.javaPrimitiveType) {
            return BooleanTypeAdapter() as TypeAdapter<T>
        }
        return null
    }
}
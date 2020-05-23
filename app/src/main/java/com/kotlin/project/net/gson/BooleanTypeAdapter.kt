package com.kotlin.project.net.gson

import android.text.TextUtils
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

/**
 * @author Finn
 * @date 2020
 */
class BooleanTypeAdapter : TypeAdapter<Boolean>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): Boolean {
        // TODO Auto-generated method stub
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return false
        }
        if (reader.peek() == JsonToken.NUMBER) {
            return reader.nextInt() != 0
        }
        return if (reader.peek() == JsonToken.STRING) {
            quearyString(reader.nextString())
        } else reader.nextBoolean()
    }

    @Throws(IOException::class)
    override fun write(
        writer: JsonWriter,
        value: Boolean?
    ) {
        // TODO Auto-generated method stub
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }

    private fun quearyString(data: String): Boolean {
        return if (TextUtils.isEmpty(data)) {
            false
        } else {
            java.lang.Boolean.parseBoolean(data)
        }
    }
}
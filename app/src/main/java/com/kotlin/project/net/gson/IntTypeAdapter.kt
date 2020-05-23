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
class IntTypeAdapter : TypeAdapter<Int>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): Int {
        // TODO Auto-generated method stub
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return 0
        }
        if (reader.peek() == JsonToken.BOOLEAN) {
            return if (reader.nextBoolean()) 1 else 0
        }
        return if (reader.peek() == JsonToken.STRING) {
            queryString(reader.nextString())
        } else reader.nextDouble().toInt()
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: Int?) {
        // TODO Auto-generated method stub
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value as Long)
    }

    private fun queryString(data: String): Int {
        return if (TextUtils.isEmpty(data)) {
            0
        } else {
            data.toInt()
        }
    }
}
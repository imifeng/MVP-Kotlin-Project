package com.kotlin.project.net.gson

import android.text.TextUtils
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.kotlin.project.utils.FormatUtils
import java.io.IOException

/**
 * @author Finn
 * @date 2020
 */
class LongTypeAdapter : TypeAdapter<Long>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): Long {
        // TODO Auto-generated method stub
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return 0L
        }
        return if (reader.peek() == JsonToken.STRING) {
            queryString(reader.nextString())
        } else try {
            reader.nextDouble().toLong()
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }

    @Throws(IOException::class)
    override fun write(
        writer: JsonWriter,
        value: Long?
    ) {
        // TODO Auto-generated method stub
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value.toString())
    }

    private fun queryString(data: String): Long {
        return if (TextUtils.isEmpty(data)) {
            0L
        } else {
            FormatUtils.getLong(data)
        }
    }
}
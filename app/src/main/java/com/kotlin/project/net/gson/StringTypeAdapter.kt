package com.kotlin.project.net.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

/**
 * @author Finn
 * @date 2020
 */
class StringTypeAdapter : TypeAdapter<String>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): String {
        // TODO Auto-generated method stub
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return ""
        }
        return reader.nextString()
    }

    @Throws(IOException::class)
    override fun write(
        writer: JsonWriter,
        value: String?
    ) {
        // TODO Auto-generated method stub
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}
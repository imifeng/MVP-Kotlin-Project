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
class DoubleTypeAdapter : TypeAdapter<Double?>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): Double? {
        // TODO Auto-generated method stub
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return 0.0
        }
        if (reader.peek() == JsonToken.BOOLEAN) {
            return if (reader.nextBoolean()) 1.0 else 0.0
        }
        return if (reader.peek() == JsonToken.STRING) {
            quearyString(reader.nextString())
        } else reader.nextDouble()
    }

    @Throws(IOException::class)
    override fun write(
        writer: JsonWriter,
        value: Double?
    ) {
        // TODO Auto-generated method stub
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }

    private fun quearyString(data: String): Double {
        return if (TextUtils.isEmpty(data)) {
            0.0
        } else {
            data.toDouble()
        }
    }
}
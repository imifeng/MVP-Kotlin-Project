package com.kotlin.project.utils

import com.google.gson.Gson

object GsonUtils {

    private val mGson by lazy { Gson() }

    fun get(): Gson {
        return mGson
    }

}
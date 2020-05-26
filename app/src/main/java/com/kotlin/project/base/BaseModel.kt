package com.kotlin.project.base

import com.kotlin.project.net.RetrofitClient

/**
 * @author Finn
 * @date 2020
 */
open class BaseModel {

    //用于管理网络请求层, 以及数据缓存层
    var repository: RetrofitClient
        private set

    init {
        repository = RetrofitClient.instance
    }
}
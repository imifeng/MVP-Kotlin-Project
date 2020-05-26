package com.kotlin.project.net

/**
 * @author Finn
 * @date 2020
 */
interface NetRequestListener<T> {
    fun onSuccess(response: T)
    fun onFailed(e: Throwable?)
}
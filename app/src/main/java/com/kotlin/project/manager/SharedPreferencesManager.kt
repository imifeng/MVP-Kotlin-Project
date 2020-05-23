package com.kotlin.project.manager

import com.kotlin.project.utils.SpUtils

/**
 * @author Finn
 * @date 2020
 */
object SharedPreferencesManager {
    private const val KEY_JSON_IS_FIRST_OPEN = "isFirstOpen"

    /**
     * KEY_JSON_IS_FIRST_OPEN
     */
    fun saveIsFirstOpen(isFirstOpen: Boolean) {
        SpUtils.saveValue(KEY_JSON_IS_FIRST_OPEN, isFirstOpen)
    }

    val isFirstOpen: Boolean
        get() = SpUtils.getBoolean(KEY_JSON_IS_FIRST_OPEN, true)
}
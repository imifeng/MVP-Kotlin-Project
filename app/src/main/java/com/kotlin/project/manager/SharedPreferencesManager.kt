package com.kotlin.project.manager

import android.text.TextUtils
import com.kotlin.project.mvp.model.bean.User
import com.kotlin.project.utils.GsonUtils
import com.kotlin.project.utils.SpUtils

/**
 * @author Finn
 * @date 2020
 */
object SharedPreferencesManager {

    private const val TAG = "SharedPreferencesManager"
    private const val KEY_IS_FIRST_OPEN = "${SpUtils.SP_PREFIX}.IS_FIRST_OPEN"
    private const val KEY_USER_TOKEN = "${SpUtils.SP_PREFIX}.KEY_USER_TOKEN"
    private const val KEY_USER = "${SpUtils.SP_PREFIX}.KEY_USER"
    private const val KEY_USER_NAME = "${SpUtils.SP_PREFIX}.KEY_USER_NAME"

    /**
     * KEY_JSON_IS_FIRST_OPEN
     */
    var isFirstOpen: Boolean
        get() = SpUtils.getBoolean(KEY_IS_FIRST_OPEN, true)
        set(value) = SpUtils.saveValue(KEY_IS_FIRST_OPEN, value)

    /**
     * KEY_USER_TOKEN
     */
    var userToken: String
        get() = SpUtils.getString(KEY_USER_TOKEN)
        set(value) = SpUtils.saveValue(KEY_USER_TOKEN, value)

    /**
     * KEY_JSON_USER
     */
    fun saveUser(userJson: String) {
        SpUtils.saveValue(KEY_USER, userJson)
    }

    fun getUser(): User? {
        val userJson = SpUtils.getString(KEY_USER)
        return if (!userJson.isNullOrEmpty()) {
            GsonUtils.get().fromJson(userJson, User::class.java) as User
        } else null
    }

    fun clearUser() {
        SpUtils.remove(KEY_USER)
    }


}
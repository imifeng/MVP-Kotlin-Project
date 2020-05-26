package com.kotlin.project.mvp.model.bean

import com.kotlin.project.manager.SharedPreferencesManager
import com.kotlin.project.utils.GsonUtils

data class User(
    var token: String,
    var id: Int,
    var login: String,
    var avatar_url: String
) {

    fun save(user: User?) {
        user?.let {
            SharedPreferencesManager.userToken = it.token
            SharedPreferencesManager.saveUser(GsonUtils.get().toJson(it))
        } ?: SharedPreferencesManager.clearUser()
    }

}
package com.kotlin.project.mvp.model.bean

import com.google.gson.annotations.SerializedName

/**
 * @author Finn
 * @date 2020
 */
class RepoBean(
    val id: Int,
    val name: String? = null,

    @SerializedName("private")
    val privateX: Boolean? = null,
    val owner: OwnerBean? = null,
    val html_url: String? = null,
    val description: String? = null
) {

    data class OwnerBean(
        val id: Int,
        val name: String? = null,
        val avatar_url: String? = null,
        val url: String? = null,
        val html_url: String? = null
    )
}
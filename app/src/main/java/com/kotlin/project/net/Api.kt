package com.kotlin.project.net

/**
 * @author Finn
 * @date 2020
 */
interface Api {
    companion object {
        val baseHost: String
            get() = "https://api.github.com/"
    }
}
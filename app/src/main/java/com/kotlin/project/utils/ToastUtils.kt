package com.kotlin.project.utils

import android.widget.Toast
import com.kotlin.project.MApplication

object ToastUtils {

    fun showToast(msg: String?) {
        msg?.let {
            Toast.makeText(
                MApplication.applicationContext,
                it, Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun showToast(msgResId: Int) {
        Toast.makeText(
            MApplication.applicationContext,
            msgResId, Toast.LENGTH_SHORT
        ).show()
    }
}
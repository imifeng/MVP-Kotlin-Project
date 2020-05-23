package com.kotlin.project.utils

import android.widget.Toast
import com.kotlin.project.MApplication

object ToastUtil {

    fun showToast(msg: String) {
        Toast.makeText(
            MApplication.applicationContext,
            msg, Toast.LENGTH_SHORT
        ).show()
    }

    fun showToast(msgResId: Int) {
        Toast.makeText(
            MApplication.applicationContext,
            msgResId, Toast.LENGTH_SHORT
        ).show()
    }
}
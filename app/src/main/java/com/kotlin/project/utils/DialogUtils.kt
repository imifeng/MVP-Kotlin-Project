package com.kotlin.project.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils

object DialogUtils {

    private var alertDialog: AlertDialog? = null

    //only single button
    fun showDialog(context: Context?,
                   title: String,
                   message: String,
                   btnText: String,
                   btnTextColor: Int) {
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(message)) {
            return
        }
        try {
            alertDialog?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
        } catch (e: Exception) {
        }

        if (context == null) {
            return
        }

        alertDialog = AlertDialog.Builder(context).create().apply {
            setTitle(title)
            setMessage(message)
            setButton(DialogInterface.BUTTON_POSITIVE, btnText) { dialogInterface, _ -> dialogInterface.dismiss() }
            show()
            getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(btnTextColor)
        }

    }

    //double button
    fun showDialog(
        context: Context?,
        title: String,
        message: String,
        okTitle: String,
        cancelTitle: String,
        okColor: Int,
        cancelColor: Int,
        listenerOk: DialogInterface.OnClickListener,
        listenerCancel: DialogInterface.OnClickListener
    ) {
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(message)) {
            return
        }
        try {

            alertDialog?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
        } catch (e: Exception) {
        }

        if (context == null) {
            return
        }

        alertDialog = AlertDialog.Builder(context).create().apply {
            setTitle(title)
            setMessage(message)
            setButton(DialogInterface.BUTTON_POSITIVE, okTitle, listenerOk)
            setButton(DialogInterface.BUTTON_NEGATIVE, cancelTitle, listenerCancel)
            show()

            getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(okColor)
            getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(cancelColor)
        }

    }

    fun hideDialog() {
        try {
            alertDialog?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
        } catch (e: Exception) {
        }

    }


}

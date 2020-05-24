package com.kotlin.project.manager

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context

/**
 * @author Finn
 * @date 2020
 */
object ProgressDialogManager {

    private var progressDialog: ProgressDialog? = null

    fun showProgress(context: Context?) {
        if (progressDialog != null && progressDialog!!.isShowing || context == null) {
            return
        }
        if (context is Activity && context.isFinishing) {
            return
        }
        try {
            progressDialog = ProgressDialog.show(context, null, "Loading...", true, true)
//            progressDialog?.window?.setBackgroundDrawable(new ColorDrawable (Color.TRANSPARENT))
//            progressDialog?.setContentView(R.layout.progress)
        } catch (e: Exception) {
            LogUtils.showError(e)
        }
    }

    fun dismissProgress() {
        progressDialog?.let {
            if (it.isShowing) {
                try {
                    it.dismiss()
                } catch (e: Exception) {
                    LogUtils.showError(e)
                }
                progressDialog = null
            }
        }
    }
}
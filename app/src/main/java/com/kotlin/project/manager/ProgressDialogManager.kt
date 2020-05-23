package com.kotlin.project.manager

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context

/**
 * @author Finn
 * @date 2020
 */
class ProgressDialogManager {

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
        if (progressDialog != null && progressDialog!!.isShowing) {
            try {
                progressDialog!!.dismiss()
            } catch (e: Exception) {
                LogUtils.showError(e)
            }
            progressDialog = null
        }
    }

    companion object {
        @Volatile
        private var progressDialogManager: ProgressDialogManager? = null
        val instance: ProgressDialogManager?
            get() {
                if (progressDialogManager == null) {
                    synchronized(ProgressDialogManager::class.java) {
                        if (progressDialogManager == null) {
                            progressDialogManager =
                                ProgressDialogManager()
                        }
                    }
                }
                return progressDialogManager
            }
    }
}
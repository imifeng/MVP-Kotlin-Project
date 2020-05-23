package com.kotlin.project.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout

object DisplayCutoutUtils {

    private const val TAG = "DisplayCutoutUtils"

    // adapt status bar view height
    fun adaptStatusBarHeight(context: Context, view: View) {
        val pH = getStatusBarHeight(context)
        val params = view.layoutParams
        params.height = pH
        view.layoutParams = params
    }


    // adapt cutout ViewGroup:FrameLayout
    fun displayAdaptCutoutFrameLayoutMargin(context: Context, view: View) {
        val pH = getStatusBarHeight(context)
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = pH
        view.layoutParams = params
    }

    // adapt cutout ViewGroup:LinearLayout
    fun displayAdaptCutoutLinearLayoutMargin(context: Context, view: View) {
        val pH = getStatusBarHeight(context)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = pH
        view.layoutParams = params
    }

    // adapt cutout ViewGroup:RelativeLayout
    fun displayAdaptCutoutRelativeLayoutMargin(context: Context, view: View) {
        val pH = getStatusBarHeight(context)
        val params = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = pH
        view.layoutParams = params
    }

    // adapt cutout ViewGroup:RelativeLayout
    fun displayAdaptCutoutRelativeLayoutMargin(context: Context, view: View, extraMargin: Int) {
        if (view.layoutParams is RelativeLayout.LayoutParams) {
            val pH = getStatusBarHeight(context)
            (view.layoutParams as RelativeLayout.LayoutParams).topMargin = pH + extraMargin
        }
    }

    /**
     * get statusBar height
     *
     * @param context c
     * @return h
     */
    private fun getStatusBarHeight(context: Context): Int {
        try {
            var result = 0
            val resourceId =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
            LogUtils.showLog(TAG, "getStatusBarHeight==========>$result")
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }
}
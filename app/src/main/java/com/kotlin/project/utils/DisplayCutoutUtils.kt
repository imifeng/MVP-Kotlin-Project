package com.kotlin.project.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout

private const val TAG = "DisplayCutoutUtils"

// adapt status bar view height
fun View.adaptStatusBarHeight() {
    val pH = getStatusBarHeight(context)
    val params = this.layoutParams
    params.height = pH
    layoutParams = params
}


// adapt cutout ViewGroup:FrameLayout
fun View.displayAdaptCutoutFrameLayoutMargin() {
    val pH = getStatusBarHeight(context)
    val params = FrameLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.topMargin = pH
    layoutParams = params
}

// adapt cutout ViewGroup:LinearLayout
fun View.displayAdaptCutoutLinearLayoutMargin() {
    val pH = getStatusBarHeight(context)
    val params = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.topMargin = pH
    layoutParams = params
}

// adapt cutout ViewGroup:RelativeLayout
fun View.displayAdaptCutoutRelativeLayoutMargin() {
    val pH = getStatusBarHeight(context)
    val params = RelativeLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.topMargin = pH
    layoutParams = params
}

// adapt cutout ViewGroup:RelativeLayout
fun View.displayAdaptCutoutRelativeLayoutMargin(extraMargin: Int) {
    if (layoutParams is RelativeLayout.LayoutParams) {
        val pH = getStatusBarHeight(context)
        (layoutParams as RelativeLayout.LayoutParams).topMargin = pH + extraMargin
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
package com.kotlin.project.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.WindowManager
import com.kotlin.project.MApplication
import com.kotlin.project.R

object AppUtils {

    fun getAppVersion(context: Context): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName.toString()
    }

    fun isPad(): Boolean {
        return (MApplication.applicationContext.resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    fun getSuitWithSize(size: Int, margin: Int): Int {
        val screenWidth: Int =
            MApplication.applicationContext.resources.displayMetrics.widthPixels
        return if (screenWidth >= size + 2 * margin) {
            size
        } else {
            screenWidth - 2 * margin
        }
    }

    private fun getInnerMaxSize(): Int {
        return getSuitWithSize(
            MApplication.applicationContext.resources.getDimensionPixelSize(R.dimen.tablet_width),
            MApplication.applicationContext.resources.getDimensionPixelSize(R.dimen.tablet_width_margin)
        )
    }

    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    fun getScreenWidth(context: Context, isPad: Boolean): Int {
        if (isPad and isPad()){
            return getInnerMaxSize()
        }
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm.heightPixels
    }

    fun getStatusBarHeight(activity: Activity): Int {
        val rectangle = Rect()
        val window = activity.window.decorView.getWindowVisibleDisplayFrame(rectangle)
        return rectangle.top
    }

}
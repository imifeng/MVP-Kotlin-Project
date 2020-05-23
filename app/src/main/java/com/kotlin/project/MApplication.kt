package com.kotlin.project

import android.R
import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import android.os.Build
import com.kotlin.project.utils.GlideImageUtils

class MApplication : Application() {

    //Companion Object中定义的成员类似于Java中的静态成员，因为Kotlin中没有static成员
    companion object {
        lateinit var app: MApplication
            private set

        val applicationContext: Context
            get() {
                return app.applicationContext
            }

    }
    init {
        app = this
    }

    override fun onCreate() {
        super.onCreate()
        try {
            // init
        } catch (e: Exception) {
            LogUtils.showError(e)
        }
    }

    fun isContextExisted(context: Context): Boolean {
        if (context != null) {
            if (context is Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!context.isDestroyed && !context.isFinishing) {
                        return true
                    }
                } else {
                    if (!context.isFinishing) {
                        return true
                    }
                }
            } else if (context is Application) {
                return true
            }
        }
        return false
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (R.attr.level) {
            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
                GlideImageUtils.clearMemory(this)
            }
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        GlideImageUtils.clearMemory(this)
    }

}
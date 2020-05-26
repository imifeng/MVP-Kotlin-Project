package com.kotlin.project.utils

import android.view.MotionEvent
import android.view.View

object OnClickUtils {

    fun setOnClickListener(mView: View, onClickListener: View.OnClickListener?) {
        var startX = 0f
        var startY = 0f

        mView.setOnTouchListener { view, motionEvent ->
            try {
                val x = motionEvent.rawX
                val y = motionEvent.rawY
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        view.alpha = 0.6f

                        startX = x
                        startY = y
                    }
                    MotionEvent.ACTION_UP -> {
                        view.alpha = 1.0f

                        if (Math.abs(x - startX) < view.width / 2 && Math.abs(y - startY) < view.height / 2) {
                            onClickListener?.onClick(view)
                        }
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        view.alpha = 1.0f
                    }
                }
            } catch (e: Exception) {
                LogUtils.showError(e)
            }
            true
        }
    }

}
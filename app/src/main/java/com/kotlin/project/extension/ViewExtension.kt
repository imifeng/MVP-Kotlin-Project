package com.kotlin.project.extension

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.kotlin.project.ui.listener.OnSingleClickListener

/**
 * Sets the background color for an element given a primary color res
 *
 * @param primaryColorRes
 */
fun View.setBackgroundColorRes(@ColorRes primaryColorRes: Int) {
    val primaryColor =
        ContextCompat.getColor(context, primaryColorRes)
    this.setBackgroundColor(primaryColor)
}

fun View.setOnSingleClickListener(listener: View.OnClickListener){
    setOnClickListener(OnSingleClickListener(listener))
}

fun View.setOnSingleClickListener(listener: (View) -> Unit){
    setOnClickListener(OnSingleClickListener(listener))
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

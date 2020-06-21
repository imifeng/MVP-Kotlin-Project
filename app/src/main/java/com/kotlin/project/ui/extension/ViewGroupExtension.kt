package com.kotlin.project.ui.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes id: Int): View {
    return LayoutInflater.from(this.context).inflate(id, this, false)
}
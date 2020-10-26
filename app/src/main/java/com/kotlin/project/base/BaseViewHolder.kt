package com.kotlin.project.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view) {

    abstract fun bindData(position: Int, item: Any?)
}
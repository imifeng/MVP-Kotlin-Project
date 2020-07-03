package com.kotlin.project.ui.listener

import android.view.View

class OnSingleClickListener : View.OnClickListener{
    private val listener: View.OnClickListener
    private var prevTime = 0L

    constructor(listener: View.OnClickListener){
        this.listener = listener
    }

    constructor(listener: (View) -> Unit){
        this.listener = View.OnClickListener { listener.invoke(it) }
    }

    companion object{
        private const val DELAY = 500L
    }

    override fun onClick(v: View?) {
        val time = System.currentTimeMillis()
        if(time >=  DELAY){
            prevTime = time
            listener.onClick(v)
        }
    }
}
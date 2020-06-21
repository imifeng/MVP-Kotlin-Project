package com.kotlin.project.base

import android.content.res.Resources
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.jaeger.library.StatusBarUtil

/**
 * @author Finn
 * @date 2020
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutId
        if (view != 0) {
            setContentView(view)
        }
        StatusBarUtil.setTranslucentForImageView(this, 50, null)
        initView()
        initData()
        initEvent()
    }

    abstract val layoutId: Int

    protected open fun initView(){}
    protected open fun initData(){}
    protected open fun initEvent(){}

    protected abstract fun onBack()

    /**
     * Listen the back key click event
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * Set font size, language
     * @return
     */
    override fun getResources(): Resources {
        //Set font size, language
        return super.getResources()
    }
}
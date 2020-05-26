package com.kotlin.project.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kotlin.project.utils.OnClickUtils

abstract class BaseFragment : Fragment() {

    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView?.let {
            val parent = it.parent as ViewGroup
            parent?.removeView(it)
        }?: run {
            rootView = inflater.inflate(getLayoutId(), container, false)
            initView(rootView)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initEvent()
    }

    protected abstract fun getLayoutId(): Int


    /**
     * 初始化视图
     *
     * @param view
     */
    protected open fun initView(view: View?) {

    }

    protected open fun initData(){}

    protected open fun initEvent(){}

    protected open fun setOnClickListener(view: View, onClickListener: View.OnClickListener?) {
        OnClickUtils.setOnClickListener(view, onClickListener)
    }

}
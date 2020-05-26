package com.kotlin.project.base

import android.os.Bundle
import autodispose2.AutoDisposeConverter
import com.kotlin.project.manager.ProgressDialogManager
import com.kotlin.project.net.RxLifecycleUtils
import com.kotlin.project.utils.ToastUtils

/**
 * @author Finn
 * @date 2020
 */
abstract class BaseMvpActivity<V : BaseView, P : BasePresenter<V>> : BaseActivity(),
    BaseView {

    private var presenter: P? = null

    open fun getPresenter(): P? {
        return presenter
    }

    abstract fun createPresenter(): P


//    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = createPresenter()
        presenter?.attachView(this as V)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
    </T> */
    override fun <T> bindAutoDispose(): AutoDisposeConverter<T> {
        return RxLifecycleUtils.bindLifecycle(this)
    }

    override fun onError(throwable: Throwable?) {
        throwable?.message?.let { ToastUtils.showToast(it) }
    }

    override fun showLoading() {
        ProgressDialogManager.showProgress(this)
    }

    override fun hideLoading() {
        ProgressDialogManager.dismissProgress()
    }
}
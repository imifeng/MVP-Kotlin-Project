package com.kotlin.project.base

import android.os.Bundle
import autodispose2.AutoDisposeConverter
import com.kotlin.project.manager.ProgressDialogManager
import com.kotlin.project.http.RxLifecycleUtils
import com.kotlin.project.utils.ToastUtils

abstract class BaseMvpFragment<V : BaseView, P : BasePresenter<V>> : BaseFragment(), BaseView {


    private var presenter: P? = null

    open fun getPresenter(): P? {
        return presenter
    }

    abstract fun createPresenter(): P

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
        ToastUtils.showToast(throwable?.message)
    }

    override fun showLoading() {
        ProgressDialogManager.showProgress(activity)
    }

    override fun hideLoading() {
        ProgressDialogManager.dismissProgress()
    }

}
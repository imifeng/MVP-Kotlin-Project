package com.kotlin.project.base

import com.kotlin.project.http.NetObserver
import com.kotlin.project.http.NetRequestListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @author Finn
 * @date 2020
 */

open class BasePresenter<V : BaseView> {
    var pView: V? = null
        private set

    /**
     * 绑定view，一般在初始化中调用该方法
     *
     * @param view view
     */
    fun attachView(view: V) {
        this.pView = view
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     */
    fun detachView() {
        this.pView = null
    }

    /**
     * requestNetwork
     *
     * @param observable
     * @param listener
     * @param compose
     * @param <T>
    </T> */
    private fun <T> requestNetwork(
        observable: Observable<T>,
        listener: NetRequestListener<T>?,
        compose: ObservableTransformer<T, T>
    ) {
        observable.compose(compose)
            .to(pView?.bindAutoDispose()) //绑定 AutoDispose，跟随view生命周期
            .subscribe(object : NetObserver<T>() {
                override fun onSuccess(response: T) {
                    listener?.onSuccess(response)
                    pView?.hideLoading()
                }

                override fun onFailure(e: Throwable?) {
                    listener?.onFailed(e)
                    pView?.hideLoading()
                }
            })
    }

    fun <T> requestNetwork(
        observable: Observable<T>,
        observer: NetRequestListener<T>?
    ) {
        requestNetwork(observable, observer, ioMainScheduler())
    }

    private fun <T> ioMainScheduler(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream: Observable<T> ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
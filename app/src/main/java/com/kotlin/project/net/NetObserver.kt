package com.kotlin.project.net

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author Finn
 * @date 2020
 * 封装Observer，统一处理
 */
abstract class NetObserver<T> : Observer<T> {
    private var disposable: Disposable? = null

    abstract fun onSuccess(response: T)

    abstract fun onFailure(e: Throwable?)

    override fun onSubscribe(d: @NonNull Disposable) {
        disposable = d
    }

    override fun onNext(response: @NonNull T) {
        onSuccess(response)
    }

    override fun onError(e: @NonNull Throwable) {
        onFailure(e)

        //事件完成取消订阅
        disposable?.let {
            if(!it.isDisposed)
            it.dispose()
        }
    }

    override fun onComplete() {
        disposable?.let {
            if(!it.isDisposed)
                it.dispose()
        }
    }
}
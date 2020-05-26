package com.kotlin.project.net

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import autodispose2.AutoDispose
import autodispose2.AutoDisposeConverter
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider

/**
 * @author Finn
 * @date 2020
 * 封装AutoDispose
 */
class RxLifecycleUtils private constructor() {
    companion object {
        fun <T> bindLifecycle(lifecycleOwner: LifecycleOwner?): AutoDisposeConverter<T> {
            return AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider
                    .from(lifecycleOwner, Lifecycle.Event.ON_DESTROY)
            )
        }
    }

    init {
        throw IllegalStateException("Can't instance the RxLifecycleUtils")
    }
}
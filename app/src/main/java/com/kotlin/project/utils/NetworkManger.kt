package com.kotlin.project.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import com.kotlin.project.MApplication

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkManger {

    companion object {
        private var INSTANCE: NetworkManger? = null

        fun getInstance(): NetworkManger {
            if (INSTANCE == null) {
                INSTANCE = NetworkManger()
            }
            return INSTANCE as NetworkManger
        }
    }

    private val connectivityManager by lazy {
        MApplication.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    private val networkCallbackImpl by lazy { NetworkCallbackImpl() }

    fun registerNetworkCallback(listener: NetworkConnectedListener) {
        this.connectedListener = listener
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            networkCallbackImpl
        )

        /**
         * 添加网络监听回调
         */
        networkCallbackImpl.addNetworkConnectedListener(object : NetworkConnectedListener {
            override fun networkConnected(isConnected: Boolean) {
                connectedListener?.networkConnected(isConnected)
            }
        })

        networkCallbackImpl.addNetworkCapabilitiesListener(object : NetworkCapabilitiesListener {
            override fun networkCapabilitiesChanged(type: NetworkType) {
                capabilitiesListener?.networkCapabilitiesChanged(type)
            }
        })
    }

    fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallbackImpl)
        this.connectedListener = null
        this.capabilitiesListener = null
    }

    var connectedListener: NetworkConnectedListener? = null

    var capabilitiesListener: NetworkCapabilitiesListener? = null

    /**
     * 添加网络类型监听回调
     */
    fun addNetworkCapabilitiesListener(listener: NetworkCapabilitiesListener) {
        this.capabilitiesListener = listener
    }

}
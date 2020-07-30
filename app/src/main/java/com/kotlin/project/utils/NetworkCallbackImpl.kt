package com.kotlin.project.utils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {

    // 在框架连接并声明可以使用新网络时调用
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        // 网络已连接
        networkConnectedListener?.networkConnected(true)
    }

    // 当网络断开连接或不再满足此请求或回调时调用
    override fun onLost(network: Network) {
        super.onLost(network)
        // 网络已断开
        networkConnectedListener?.networkConnected(false)
    }

    // 当与此请求相对应的网络更改功能但仍满足请求的条件时调用
    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    // 网络类型 wifi
                    networkCapabilitiesListener?.networkCapabilitiesChanged(NetworkType.NETWORK_WIFI)
                }
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    // 蜂窝网络(CMWAP)
                    networkCapabilitiesListener?.networkCapabilitiesChanged(NetworkType.NETWORK_WAP)
                }
                else -> {
                    // 其他网络
                    networkCapabilitiesListener?.networkCapabilitiesChanged(NetworkType.NETWORK_DEFAULT)
                }
            }
        }
    }

    private var networkConnectedListener: NetworkConnectedListener? = null

    private var networkCapabilitiesListener: NetworkCapabilitiesListener? = null

    fun addNetworkConnectedListener(listener: NetworkConnectedListener) {
        this.networkConnectedListener = listener
    }

    fun addNetworkCapabilitiesListener(listener: NetworkCapabilitiesListener) {
        this.networkCapabilitiesListener = listener
    }

    fun removeNetworkCallbackListener() {
        this.networkConnectedListener = null
        this.networkCapabilitiesListener = null
    }
}

enum class NetworkType {
    NETWORK_WIFI,
    NETWORK_WAP,
    NETWORK_DEFAULT
}

/**
 * 添加网络监听回调
 */
interface NetworkConnectedListener {
    fun networkConnected(isConnected: Boolean)
}

/**
 * 添加网络类型监听回调
 */
interface NetworkCapabilitiesListener {
    fun networkCapabilitiesChanged(type: NetworkType)
}
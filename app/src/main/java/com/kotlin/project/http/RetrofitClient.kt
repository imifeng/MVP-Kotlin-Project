package com.kotlin.project.http

import LogUtils
import android.text.TextUtils
import com.kotlin.project.BuildConfig
import com.kotlin.project.manager.SharedPreferencesManager
import com.kotlin.project.http.LoggingInterceptor.Logger
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Finn
 * @date 2020
 * 封装Retrofit
 */
class RetrofitClient {
    private val retrofit: Retrofit

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
    </T> */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    /**
     * 设置拦截器
     *
     * @return
     */
    private val interceptor: Interceptor
        get() = LoggingInterceptor(object : Logger {
            override fun log(message: String?) {
                message?.let {
                    LogUtils.d("RetrofitLog", it)
                }
            }
        })

    /**
     * 设置Header
     *
     * @return
     */
    // 可以对请求头参数作统一处理，通过下面的`addHeader()`方法
    class HeadIntercept : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response? {
            return chain?.let {
                val requestBuilder = it.request().newBuilder()
                requestBuilder.addHeader("Content-Type", "application/json; charset=utf-8")

                //这里把token添加到请求头，进行api验证token；根据api验证token方式来决定是否需要
                val authenticate = SharedPreferencesManager.getUser()?.token
                if (!TextUtils.isEmpty(authenticate)) {
                    requestBuilder.addHeader("Authorization", "Bearer $authenticate")
                }
                //
                requestBuilder.addHeader("app_version", BuildConfig.VERSION_NAME)
                requestBuilder.addHeader("platform", "Android")
                it.proceed(requestBuilder.build())
            }
        }
    }

    companion object {
        val instance: RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }
    }

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeadIntercept()) //设置Header
            .addInterceptor(interceptor) //设置拦截器
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        okHttpClient.dispatcher().maxRequestsPerHost = 20
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Api.baseHost)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}
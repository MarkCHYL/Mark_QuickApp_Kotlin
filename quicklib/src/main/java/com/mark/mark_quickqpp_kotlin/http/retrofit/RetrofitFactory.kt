package com.mark.mark_quickqpp_kotlin.http.retrofit

import android.os.Looper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mark.mark_quickqpp_kotlin.http.constant.URLConstant
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Des
 * 网络请求
 * @作者 Mark
 * @时间 2018/12/25
 * @EMail 2285581945@qq.com
 */
abstract class RetrofitFactory<T> {
    private val time_out:Long = 15//请求超时时间
    var apiServer:T

    init {
        val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val builder = chain.request().newBuilder()
                    //添加请求头header
                    if (getToken().isNotEmpty()){
                        builder.header("userToken",getToken())
                    }
                    val build = builder.build()
                    chain.proceed(build)
                }
                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                    message ->
                    if (message.contains("{")||message.contains("=")
                            ||message.contains("http") ||message.contains("userToken"))
                        Logger.e("$message")
                }).setLevel(HttpLoggingInterceptor.Level.BODY))//设置打印日志的内容
                .connectTimeout(time_out,TimeUnit.SECONDS)
                .readTimeout(time_out,TimeUnit.SECONDS)
                .build()

        apiServer = Retrofit.Builder()
                .baseUrl(URLConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(buildGson()))// 添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Retrofit到RxJava的转换器
                .build()
                .create(getApiService())
    }

    private fun buildGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

    }

    abstract fun getApiService():Class<T>
    abstract fun getToken(): String

    fun getService():T{
        return apiServer
    }
}
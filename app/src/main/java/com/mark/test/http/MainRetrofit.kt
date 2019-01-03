package com.mark.test.http

import com.mark.mark_quickqpp_kotlin.http.retrofit.RetrofitFactory

/**
 * FileName: com.beijing.zhagen.meiqi.http.retrofit.MainRetrofit.java
 * Author: mouxuefei
 * date: 2018/3/20
 * version: V1.0
 * desc:
 */

object MainRetrofit : RetrofitFactory<MainApi>() {
    override fun getApiService(): Class<MainApi> {
        return MainApi::class.java
    }

    override fun getToken(): String {
        return ""
    }
}
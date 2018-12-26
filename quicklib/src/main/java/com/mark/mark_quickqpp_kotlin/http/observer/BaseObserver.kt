package com.mark.mark_quickqpp_kotlin.http.observer

import com.google.gson.JsonParseException
import com.mark.mark_quickqpp_kotlin.showToastBottom
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * @Des 不封装BaseModel,服务器状态码统一返回的observer
 *
 * @作者 Mark
 * @时间 2018/12/25
 * @EMail 2285581945@qq.com
 */
abstract class BaseObserver<T> : Observer<T> {
    var mDisposable: Disposable? = null
    override fun onSubscribe(d: Disposable) {
        mDisposable = d
        OnBegin()
    }

    override fun onNext(value: T) {
        onHandleSuccess(value)
    }

    override fun onError(e: Throwable) {
        requestError()
        showErrorToast(e)
    }

    private fun showErrorToast(e: Throwable) {
        Logger.e("exception=${e.toString()}")
        if (e is SocketTimeoutException || e is ConnectException) {
            showToastBottom("链接失败，请检查网路状况！")
        } else if (e is JsonParseException) {
            showToastBottom("数据解析失败")
        } else {
            showToastBottom("请求失败")
        }
    }

    open fun requestError() {

    }

    protected abstract fun onHandleSuccess(t: T?)

    open fun OnBegin() {

    }
}
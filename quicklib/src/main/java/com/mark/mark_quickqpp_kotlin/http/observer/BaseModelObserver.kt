package com.mark.mark_quickqpp_kotlin.http.observer

import com.google.gson.JsonParseException
import com.mark.mark_quickqpp_kotlin.http.constant.CodeStatus
import com.mark.mark_quickqpp_kotlin.http.entity.BaseBean
import com.mark.mark_quickqpp_kotlin.showToastBottom
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * @Des  配置了baseModel,状态码统一处理的observer
 *
 * @作者 Mark
 * @时间 2018/12/25
 * @EMail 2285581945@qq.com
 */
abstract class BaseModelObserver<T : BaseBean> : Observer<T> {
    open val onBegin: (() -> Unit)? = null
    open val failError: (() -> Unit)? = null
    open val onHandleSuccess:((t:T?) -> Unit)? = null

    override fun onSubscribe(d: Disposable) {
        onBegin?.invoke()
    }

    override fun onNext(t: T) {
        if (t.code == CodeStatus.SUCCESS){
            onHandleSuccess?.invoke(t)
        }else{
            onHandleError(t.msg!!)
        }
    }

    override fun onError(e: Throwable) {
        showErrorToast(e)
        failError?.invoke()
    }

    override fun onComplete() {

    }
    open fun codeError(code:Int){}

    private fun onHandleError(msg: String) {
        if (!msg.isEmpty()){
           showToastBottom(msg)
        }else{
            showToastBottom("未知错误")
        }
    }

    private fun showErrorToast(e: Throwable) {
        Logger.e("exception=${e.toString()}")
        if (e is SocketTimeoutException || e is ConnectException) {
            showToastBottom("连接失败,请检查网络状况!")
        } else if (e is JsonParseException) {
            showToastBottom("数据解析失败")
        } else {
            showToastBottom("请求失败")
        }
    }
}
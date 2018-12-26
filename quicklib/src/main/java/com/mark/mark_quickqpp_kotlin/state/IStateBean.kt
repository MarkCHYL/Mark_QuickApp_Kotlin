package com.mark.mark_quickqpp_kotlin.state

/**
 * @Des
 *
 * @作者 Mark
 * @时间 2018/12/25
 * @EMail 2285581945@qq.com
 */
interface IStateBean<out T, out DATA : IListBean<T>> {
    val code: Int
    val result: DATA?
    fun isOK(): Boolean = 1 == code
}
package com.mark.mark_quickqpp_kotlin.scheduler

/**
 * @Des
 *
 * @作者 Mark
 * @时间 2018/12/25
 * @EMail 2285581945@qq.com
 */
object SchedulerUtils{
    fun <T> ioToMain():IoMainScheduler<T>{
        return IoMainScheduler()
    }
}
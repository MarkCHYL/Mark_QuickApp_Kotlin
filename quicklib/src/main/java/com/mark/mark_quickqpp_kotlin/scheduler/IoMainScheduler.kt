package com.mark.mark_quickqpp_kotlin.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Des
 *
 * @作者 Mark
 * @时间 2018/12/25
 * @EMail 2285581945@qq.com
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())
package com.mark.mark_quickqpp_kotlin.scheduler

import io.reactivex.*
import org.reactivestreams.Publisher

/**
 * @Des
 *
 * @作者 Mark
 * @时间 2018/12/25
 * @EMail 2285581945@qq.com
 */
abstract class BaseScheduler<T> protected constructor(private val subscribeOnScheduler: Scheduler,
                                                      private val observeOnScheduler: Scheduler) :
        SingleTransformer<T, T>,
        MaybeTransformer<T, T>,
        CompletableTransformer,
        FlowableTransformer<T, T>,
        ObservableTransformer<T, T> {
    override fun apply(upstream: Completable): CompletableSource {
        return upstream.retry(2).subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.retry(2).subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream.retry(2).subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.retry(2).subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.retry(2).subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
    }
}
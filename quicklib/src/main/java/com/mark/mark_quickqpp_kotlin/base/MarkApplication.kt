package com.mark.mark_quickqpp_kotlin.base

import android.app.Application
import android.content.Context
import android.os.Handler
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

/**
 *
 * @作者 Mark
 * @时间 2018/12/25
 */
class MarkApplication : Application() {
    private var refWatcher: RefWatcher? = null

    companion object {
        var mContext: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context?): RefWatcher? {
            val markApplication = context?.applicationContext as MarkApplication
            return markApplication.refWatcher
        }

        var appHandler: Handler? = null
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        //LeakCanary初始化
        refWatcher = setUpLeakCanary()
        //初始化生命周期
        appHandler = Handler()
        //日志框架初始化
        initLogger()
    }

    /**
     * 日志框架初始化
     */
    private fun initLogger() {

    }

    /**
     * LeakCanary初始化
     */
    private fun setUpLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)){
            RefWatcher.DISABLED
        }else{
            LeakCanary.install(this)
        }

    }
}
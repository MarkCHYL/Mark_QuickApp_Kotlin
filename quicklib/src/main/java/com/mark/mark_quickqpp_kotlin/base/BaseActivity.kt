package com.mark.mark_quickqpp_kotlin.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.jaeger.library.StatusBarUtil
import com.mark.mark_quickqpp_kotlin.R
import com.mark.mark_quickqpp_kotlin.register
import com.mark.mark_quickqpp_kotlin.unregister
import com.mark.mark_quickqpp_kotlin.utils.ActivityUtils.removeActivity
import com.mark.mark_quickqpp_kotlin.utils.ProgressDialogUtils
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

/**
 * Activity的基类
 * @作者 Mark
 * @时间 2018/12/24
 */
abstract class BaseActivity : AppCompatActivity() {
    open var mContext: Context by Delegates.notNull()//非空属性：Delegates。notNull()
    open var progressDialog: ProgressDialogUtils? = null
    private var refWatcher:RefWatcher? = null //检查内存泄露的
    open fun initData(){}
    open fun onSetContentViewNext(savedInstanceState:Bundle?){}
    @LayoutRes
    abstract fun getContentView():Int//布局
    abstract fun initView()
    open fun useEventBus():Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//强制竖屏
        mContext = this
        setContentView(getContentView())
        setStatusBar()
        if (useEventBus()){
            register(this)
        }
        initView()
        progressDialog = ProgressDialogUtils(this,R.style.dialog_transparent_style)
        onSetContentViewNext(savedInstanceState)
        pushActivity(this)
    }

    private fun pushActivity(baseActivity: BaseActivity) {

    }

    protected open fun setStatusBar() {
        StatusBarUtil.setColor(this,ContextCompat.getColor(this, R.color.color_black),0)
    }


    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        if (hasEnterTransitionAnim()) {
            overridePendingTransitionEnter()
        }
    }

    override fun finish() {
        super.finish()
        if (hasFinishTransitionAnim()) {
            overridePendingTransitionExit()
        }
    }

    open fun hasFinishTransitionAnim(): Boolean {
        return true
    }

    open fun hasEnterTransitionAnim(): Boolean {
        return true
    }

    private fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left)
    }

    private fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right)
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_UP) {
            val v = currentFocus
            //如果不是落在EditText区域，则需要关闭输入法
            if (HideKeyboard(v, ev)) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private fun HideKeyboard(view: View?, event: MotionEvent): Boolean {
        if (view != null && view is EditText) {

            val location = intArrayOf(0, 0)
            view.getLocationInWindow(location)

            //获取现在拥有焦点的控件view的位置，即EditText
            val left = location[0]
            val top = location[1]
            val bottom = top + view.height
            val right = left + view.width
            //判断我们手指点击的区域是否落在EditText上面，如果不是，则返回true，否则返回false
            val isInEt = (event.x > left && event.x < right && event.y > top
                    && event.y < bottom)
            return !isInEt
        }
        return false
    }

    override fun onDestroy() {
        removeActivity(this)
        super.onDestroy()
        if (useEventBus()) {
            unregister(this)
        }
        refWatcher?.watch(this)
    }

}
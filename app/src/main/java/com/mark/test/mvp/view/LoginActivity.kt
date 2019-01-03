package com.mark.test.mvp.view

import com.mark.mark_quickqpp_kotlin.utils.CommonUtil
import com.mark.test.R
import com.mark.test.mvp.contract.ILoginContract
import com.mark.test.mvp.presenter.LoginPresenter
import com.sihaiwanlian.baselib.mvp.BaseMvpTitleActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @Desc
 *
 * @作者 Mark
 * @时间 2018/12/26
 * @EMail 2285581945@qq.com
 */
class LoginActivity : BaseMvpTitleActivity<ILoginContract.View, ILoginContract.Presenter>(), ILoginContract.View {
    override fun childView(): Int = R.layout.activity_login

    override var mPresenter: ILoginContract.Presenter = LoginPresenter()


    override fun initData() {
        super.initData()
        setActivityTitle(R.string.app_name)

        initListner()
    }

    private fun initListner() {
        btn_login.setOnClickListener {
            CommonUtil.startActivtiy(this, MainActivity::class.java)
        }
    }
}
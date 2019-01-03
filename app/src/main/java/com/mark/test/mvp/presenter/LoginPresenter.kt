package com.mark.test.mvp.presenter

import com.exmple.corelib.mvp.BasePresenterKt
import com.mark.test.mvp.contract.ILoginContract
import com.mark.test.mvp.model.LoginModel

/**
 * @Desc
 *
 * @作者 Mark
 * @时间 2018/12/26
 * @EMail 2285581945@qq.com
 */
class LoginPresenter : BasePresenterKt<ILoginContract.View>(), ILoginContract.Presenter {
    override var mModel: ILoginContract.Model? = LoginModel()
}
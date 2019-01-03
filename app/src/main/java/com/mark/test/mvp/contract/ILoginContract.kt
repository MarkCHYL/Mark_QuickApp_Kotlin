package com.mark.test.mvp.contract

import com.exmple.corelib.mvp.IModel
import com.exmple.corelib.mvp.IPresenter
import com.exmple.corelib.mvp.IView

/**
 * @Desc
 *
 * @作者 Mark
 * @时间 2018/12/26
 * @EMail 2285581945@qq.com
 */
interface ILoginContract {
    interface View:IView<Presenter>{}
    interface Presenter:IPresenter<View,Model>{}
    interface Model:IModel{}
}
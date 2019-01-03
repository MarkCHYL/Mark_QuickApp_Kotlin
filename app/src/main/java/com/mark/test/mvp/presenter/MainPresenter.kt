package com.mark.test.mvp.presenter

import com.exmple.corelib.mvp.BasePresenterKt
import com.mark.mark_quickqpp_kotlin.mSubscribe
import com.mark.test.mvp.contract.IMainContact
import com.mark.test.mvp.model.MainModel

/**
 * @FileName: MainPresenter.java
 * @author: villa_mou
 * @date: 06-16:35
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class MainPresenter:BasePresenterKt<IMainContact.View>(), IMainContact.Presenter {
    override var mModel: IMainContact.Model? = MainModel()
    override fun getDataByNet() {
        mModel?.getMainData()?.mSubscribe(mView,mModel,"正在获取数据中...") {
            mView?.getDataSuccess()
        }
    }
}


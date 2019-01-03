package com.mark.test.mvp.model

import com.exmple.corelib.mvp.BaseModelKt
import com.mark.test.http.MainDataBean
import com.mark.test.http.MainRetrofit
import com.mark.test.mvp.contract.IMainContact
import io.reactivex.Observable

/**
 * @FileName: MainModel.java
 * @author: villa_mou
 * @date: 07-16:19
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class MainModel : BaseModelKt(), IMainContact.Model {
    override fun getMainData(): Observable<MainDataBean> {
        return MainRetrofit.apiServer.getMainData()
    }
}
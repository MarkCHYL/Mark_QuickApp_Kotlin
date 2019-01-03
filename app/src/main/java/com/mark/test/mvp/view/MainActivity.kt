package com.mark.test.mvp.view

import com.exmple.corelib.mvp.BaseMvpListActivity
import com.exmple.corelib.utils.CustomLoadMoreView
import com.mark.test.R
import com.mark.test.adapter.DemoAdapter
import com.mark.test.mvp.contract.IMainContact
import com.mark.test.mvp.presenter.MainPresenter

class MainActivity : BaseMvpListActivity<IMainContact.View, IMainContact.Presenter>(), IMainContact.View {

    override var mPresenter: IMainContact.Presenter = MainPresenter()
    override val setRefreshEnable = true
    override val setRecyclerViewBgColor = R.color.white
    override fun initData() {
        super.initData()
        val data = ArrayList<String>()
        data.add("")
        data.add("")
        data.add("")
        val demoAdapter = DemoAdapter(data = data)
        demoAdapter.setLoadMoreView(CustomLoadMoreView())
        mRecyclerView.adapter = demoAdapter
        demoAdapter.setOnLoadMoreListener({ demoAdapter.loadMoreEnd() }, mRecyclerView)
    }

    override fun onRetry() {

    }

    override fun onRefresh() {
        mRefreshLayout.finishRefresh(false)
    }

    override fun loadMoreFail(isRefresh: Boolean) {

    }


    override fun getDataSuccess() {

    }
}

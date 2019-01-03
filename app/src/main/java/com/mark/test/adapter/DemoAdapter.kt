package com.mark.test.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mark.test.R

/**
 * @FileName: ListAdapter.java
 * @author: villa_mou
 * @date: 07-10:51
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
open class DemoAdapter(var data: ArrayList<String>):BaseQuickAdapter<String,BaseViewHolder>(R.layout.adapter_item,data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {

    }
}
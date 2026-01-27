package com.darcy.kmpdemo.ui.base.impl.paging

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer

/**
 * 分页数据 reducer
 */
interface IPagingReducer<S, R> : IReducer<S> {
    // 更新分页数据
    fun onPaging(state: S, pageNumber: Int, response: R): S

    // 处理其他意图
    fun onReduce(intent: IIntent, state: S): S
}
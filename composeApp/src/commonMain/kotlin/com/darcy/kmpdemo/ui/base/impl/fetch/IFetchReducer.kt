package com.darcy.kmpdemo.ui.base.impl.fetch

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer

/**
 * 加载数据的 reducer
 */
interface IFetchReducer<S, R> : IReducer<S> {
    // 刷新数据
    fun onFetch(state: S, result: R): S

    // 处理其他意图
    fun onReduce(intent: IIntent, state: S): S
}
package com.darcy.kmpdemo.ui.base.impl.paging

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenStateReducer

abstract class PagingReducer<S, R> : IReducer<S> {
    override fun reduce(intent: IIntent, state: S): S {
        return when (intent) {
            is PagingIntent.RefreshByLoadNewPage<*> -> {
                @Suppress("UNCHECKED_CAST")
                onPaging(state, intent.pageNumber, intent.response as R)
            }

            else -> {
                onReduce(intent, state)
            }
        }
    }

    // 更新分页数据
    protected abstract fun onPaging(state: S, pageNumber: Int, response: R): S

    abstract fun onReduce(intent: IIntent, state: S): S
}
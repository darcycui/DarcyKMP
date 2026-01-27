package com.darcy.kmpdemo.ui.base.impl.paging

import com.darcy.kmpdemo.ui.base.IIntent

abstract class AbstractPagingReducer<S, R> : IPagingReducer<S, R> {
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
}
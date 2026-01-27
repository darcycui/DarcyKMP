package com.darcy.kmpdemo.ui.base.impl.fetch

import com.darcy.kmpdemo.ui.base.IIntent

abstract class AbstractFetchReducer<S, R> : IFetchReducer<S, R> {
    override fun reduce(intent: IIntent, state: S): S {
        return when (intent) {
            is FetchIntent.RefreshByFetchData<*> -> {
                @Suppress("UNCHECKED_CAST")
                onFetch(state, intent.result as R)
            }

            else -> {
                onReduce(intent, state)
            }
        }
    }
}
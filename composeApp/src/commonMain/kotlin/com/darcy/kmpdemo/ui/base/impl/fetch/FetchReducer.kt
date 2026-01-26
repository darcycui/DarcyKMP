package com.darcy.kmpdemo.ui.base.impl.fetch

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer

abstract class FetchReducer<S, R> : IReducer<S> {
    override fun reduce(intent: IIntent, state: S): S {
        return when (intent) {
            is FetchIntent.RefreshByFetchData<*> -> {
                @Suppress("UNCHECKED_CAST")
                onRefreshByFetchData(state, intent.result as R)
            }

            else -> {
                onReduce(intent, state)
            }
        }
    }

    abstract fun onRefreshByFetchData(state: S, result: R): S

    abstract fun onReduce(intent: IIntent, state: S): S
}
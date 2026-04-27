package com.darcy.kmpdemo.ui.base.impl.fetch

import com.darcy.kmpdemo.ui.base.IIntent

sealed class FetchIntent : IIntent {
    data object ActionFetchData : FetchIntent()

    data class RefreshByFetchData<T>(
        val result: T
    ) : FetchIntent()
}
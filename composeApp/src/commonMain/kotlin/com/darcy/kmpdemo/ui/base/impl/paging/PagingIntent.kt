package com.darcy.kmpdemo.ui.base.impl.paging

import com.darcy.kmpdemo.ui.base.IIntent

sealed class PagingIntent : IIntent {
    data class ActionLoadNewPage(
        val pageNumber: Int,
    ) : PagingIntent()

    data class RefreshByLoadNewPage<R>(
        val pageNumber: Int,
        val response: R,
    ) : PagingIntent()
}
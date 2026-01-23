package com.darcy.kmpdemo.ui.screen.loaddata.intent

import com.darcy.kmpdemo.ui.base.IIntent

sealed class LoadDataIntent : IIntent {

    // 加载数据
    data object ActionLoadData : LoadDataIntent()

    // 加载数据 刷新UI
    data class RefreshByLoadData(
        val result: String
    ) : LoadDataIntent()
}
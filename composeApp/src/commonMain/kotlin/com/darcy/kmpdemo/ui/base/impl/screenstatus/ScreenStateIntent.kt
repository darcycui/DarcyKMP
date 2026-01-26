package com.darcy.kmpdemo.ui.base.impl.screenstatus

import com.darcy.kmpdemo.ui.base.IIntent

sealed class ScreenStateIntent: IIntent {
    // 页面状态
    data class ScreenStateChange(
        val screenState: ScreenState
    ) : ScreenStateIntent()

}
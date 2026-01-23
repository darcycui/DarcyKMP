package com.darcy.kmpdemo.ui.base.impl

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.ScreenStatus

sealed class ScreenStatusIntent: IIntent {
    // 页面状态
    data class ScreenStateChange(
        val screenState: ScreenStatus
    ) : ScreenStatusIntent()

}
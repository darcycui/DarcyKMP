package com.darcy.kmpdemo.ui.base.impl.tips

import com.darcy.kmpdemo.ui.base.IIntent

sealed class TipsIntent : IIntent {
    data class ShowTips(
        val title: String,
        val tips: String,
        val code: Int,
        val middleButtonText: String,
        val negativeButtonText: String = "",
        val positiveButtonText: String = "",
    ) : TipsIntent()

    data object DismissTips : TipsIntent()
}
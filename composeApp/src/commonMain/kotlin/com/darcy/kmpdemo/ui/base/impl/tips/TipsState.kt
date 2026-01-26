package com.darcy.kmpdemo.ui.base.impl.tips

import com.darcy.kmpdemo.ui.base.IState

data class TipsState(
    val title: String = "",
    val tips: String = "",
    val code: Int = 0,
    val negativeButtonText: String = "",
    val positiveButtonText: String = "",
    val middleButtonText: String = "",

    val showTips: Boolean = false,
) : IState

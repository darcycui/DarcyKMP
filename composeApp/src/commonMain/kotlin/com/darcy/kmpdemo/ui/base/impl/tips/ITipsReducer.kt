package com.darcy.kmpdemo.ui.base.impl.tips

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer

/**
 * 提示 reducer
 */
interface ITipsReducer<S> : IReducer<S> {

    // 显示提示
    fun onShowTips(state: S, intent: TipsIntent.ShowTips): S

    // 隐藏提示
    fun onDismissTips(state: S): S

    // 处理其他意图
    fun onReduce(intent: IIntent, state: S): S
}
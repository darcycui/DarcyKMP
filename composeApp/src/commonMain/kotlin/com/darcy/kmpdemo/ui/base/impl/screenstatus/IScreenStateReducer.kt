package com.darcy.kmpdemo.ui.base.impl.screenstatus

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer

/**
 * 屏幕状态 reducer
 */
interface IScreenStateReducer<S> : IReducer<S> {
    // 处理其他意图
    fun onReduce(intent: IIntent, state: S): S

    // 更新屏幕状态
    fun onScreenState(state: S, newScreenState: ScreenState): S
}
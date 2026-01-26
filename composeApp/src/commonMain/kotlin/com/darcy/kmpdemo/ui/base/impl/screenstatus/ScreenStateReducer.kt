package com.darcy.kmpdemo.ui.base.impl.screenstatus

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer

abstract class ScreenStateReducer<S> : IReducer<S> {
    override fun reduce(intent: IIntent, state: S): S {
        return when (intent) {
            is ScreenStateIntent.ScreenStateChange -> {
                onScreenState(state, intent.screenState)
            }

            else -> {
                onReduce(intent, state)
            }
        }
    }

    // 更新屏幕状态
    protected abstract fun onScreenState(state: S, newScreenState: ScreenState): S


    // 处理特有意图
    protected abstract fun onReduce(intent: IIntent, state: S): S
}
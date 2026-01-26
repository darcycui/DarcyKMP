package com.darcy.kmpdemo.ui.base.impl.screenstatus

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer

abstract class ScreenStateReducer<S> : IReducer<S> {
    override fun reduce(intent: IIntent, state: S): S {
        return when (intent) {
            is ScreenStateIntent.ScreenStateChange -> {
                onScreenStatus(state, intent.screenState)
            }

            else -> {
                onReduce(intent, state)
            }
        }
    }

    // 子类需实现此方法来更新屏幕状态
    protected abstract fun onScreenStatus(state: S, newScreenState: ScreenState): S


    // 子类需实现此方法来处理特有意图
    protected abstract fun onReduce(intent: IIntent, state: S): S
}
package com.darcy.kmpdemo.ui.base.impl

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.ScreenStatus
import com.darcy.kmpdemo.ui.screen.loaddata.intent.LoadDataIntent
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState

abstract class ScreenStatusReducer<T> : IReducer<T> {
    override fun reduce(intent: IIntent, state: T): T {
        return when (intent) {
            is ScreenStatusIntent.ScreenStateChange -> {
                onScreenStatus(state, intent.screenState)
            }

            else -> {
                onReduce(intent, state)
            }
        }
    }

    // 子类需实现此方法来更新屏幕状态
    protected abstract fun onScreenStatus(state: T, newScreenState: ScreenStatus): T


    // 子类需实现此方法来处理特有意图
    protected abstract fun onReduce(intent: IIntent, state: T): T
}

class ScreenStatusReducer2 : ScreenStatusReducer<ScreenStatusState>() {

    override fun onScreenStatus(
        state: ScreenStatusState,
        newScreenState: ScreenStatus
    ): ScreenStatusState {
        return state.apply { screenState = newScreenState }
    }

    override fun onReduce(
        intent: IIntent,
        state: ScreenStatusState
    ): ScreenStatusState {
        return state // 对于 ScreenStatusState，不需要特殊处理
    }
}
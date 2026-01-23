package com.darcy.kmpdemo.ui.base.impl

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.ScreenStatus
import com.darcy.kmpdemo.ui.screen.loaddata.intent.LoadDataIntent
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState

class ScreenStatusReducer : IReducer<ScreenStatusState> {
    override fun reduce(
        intent: IIntent,
        state: ScreenStatusState
    ): ScreenStatusState {
        return when (intent) {
            is ScreenStatusIntent.ScreenStateChange -> {
                state.copys(
                    screenState = intent.screenState
                )
            }

            else -> state
        }
    }
}

abstract class BaseScreenStatusReducer<T> : IReducer<T> {
    override fun reduce(intent: IIntent, state: T): T {
        return when (intent) {
            is ScreenStatusIntent.ScreenStateChange -> {
                updateScreenState(state, intent.screenState)
            }

            else -> {
                handleSpecificIntent(intent, state)
            }
        }
    }

    // 子类需实现此方法来处理特有意图
    protected abstract fun handleSpecificIntent(intent: IIntent, state: T): T

    // 子类需实现此方法来更新屏幕状态
    protected abstract fun updateScreenState(state: T, newScreenState: ScreenStatus): T
}

class ScreenStatusReducer2 : BaseScreenStatusReducer<ScreenStatusState>() {
    override fun handleSpecificIntent(
        intent: IIntent,
        state: ScreenStatusState
    ): ScreenStatusState {
        return state // 对于 ScreenStatusState，不需要特殊处理
    }

    override fun updateScreenState(
        state: ScreenStatusState,
        newScreenState: ScreenStatus
    ): ScreenStatusState {
        return state.copys(screenState = newScreenState)
    }
}

class LoadDataReducer2 : BaseScreenStatusReducer<LoadDataState>() {
    override fun handleSpecificIntent(intent: IIntent, state: LoadDataState): LoadDataState {
        return when (intent) {
            is LoadDataIntent.RefreshByLoadData -> {
                state.copy(content = intent.result)
            }

            else -> state
        }
    }

    override fun updateScreenState(
        state: LoadDataState,
        newScreenState: ScreenStatus
    ): LoadDataState {
        return state.copys(screenState = newScreenState) as LoadDataState
    }
}
package com.darcy.kmpdemo.ui.screen.loaddata.reducer

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.ScreenStatus
import com.darcy.kmpdemo.ui.base.impl.ScreenStatusReducer
import com.darcy.kmpdemo.ui.screen.loaddata.intent.LoadDataIntent
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState

class LoadDataReducer: ScreenStatusReducer<LoadDataState>() {

    override fun onScreenStatus(
        state: LoadDataState,
        newScreenState: ScreenStatus
    ): LoadDataState {
        return state.apply {
            screenState = newScreenState
        }
    }

    override fun onReduce(intent: IIntent, state: LoadDataState): LoadDataState {
        return when (intent) {
            is LoadDataIntent.RefreshByLoadData -> {
                state.copy(content = intent.result)
            }

            else -> state
        }
    }
}
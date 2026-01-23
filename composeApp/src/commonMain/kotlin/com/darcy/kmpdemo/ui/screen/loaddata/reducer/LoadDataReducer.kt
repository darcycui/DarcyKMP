package com.darcy.kmpdemo.ui.screen.loaddata.reducer

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.ScreenStatusReducer
import com.darcy.kmpdemo.ui.screen.loaddata.intent.LoadDataIntent
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState

class LoadDataReducer: IReducer<LoadDataState> {

    private val screenStatusReducer = ScreenStatusReducer()
    override fun reduce(
        intent: IIntent,
        state: LoadDataState
    ): LoadDataState {
        return when(intent) {
            is LoadDataIntent.RefreshByLoadData -> {
                state.copy(
                    content = intent.result
                )
            }
            else -> {
                state
            }
        }
    }
}
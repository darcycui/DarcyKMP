package com.darcy.kmpdemo.ui.screen.loaddata.reducer

import com.darcy.kmpdemo.bean.http.LoadDataResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStatePagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.loaddata.intent.LoadDataIntent
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState

class LoadDataReducer : ScreenStatePagingTipsCombinedReducer<LoadDataState, LoadDataResponse>() {

    override fun onScreenState(
        state: LoadDataState,
        newScreenState: ScreenState
    ): LoadDataState {
        return state.copy(screenState = newScreenState)
    }

    override fun onPaging(
        state: LoadDataState,
        pageNumber: Int,
        response: LoadDataResponse
    ): LoadDataState {
        return state.copy(
            content = "${response.name} : ${response.age}",
            pagingState = state.pagingState.copy(
                currentPageNumber = pageNumber,
            )
        )
    }

    override fun onShowTips(
        state: LoadDataState,
        intent: TipsIntent.ShowTips
    ): LoadDataState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = true,
                title = intent.title,
                tips = intent.tips,
                middleButtonText = intent.middleButtonText
            )
        )
    }

    override fun onDismissTips(state: LoadDataState): LoadDataState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
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
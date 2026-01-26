package com.darcy.kmpdemo.ui.screen.loaddata.reducer

import com.darcy.kmpdemo.bean.http.LoadDataResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState

class LoadDataReducer : ScreenStateFetchPagingTipsCombinedReducer<LoadDataState, LoadDataResponse>() {

    override fun onScreenState(
        state: LoadDataState,
        newScreenState: ScreenState
    ): LoadDataState {
        return state.copy(screenState = newScreenState)
    }

    override fun onRefreshByFetchData(
        state: LoadDataState,
        result: LoadDataResponse
    ): LoadDataState {
        return state.copy(
            content = "${result.name} : ${result.age}",
            screenState = ScreenState.Success
        )
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
        return state
    }
}
package com.darcy.kmpdemo.ui.screen.loaddata.reducer

import com.darcy.kmpdemo.bean.http.LoadDataResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.paging.PagingReducer
import com.darcy.kmpdemo.ui.screen.loaddata.intent.LoadDataIntent
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState

class LoadDataReducer : PagingReducer<LoadDataState, LoadDataResponse>() {

    override fun onScreenStatus(
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

    override fun onReduce(intent: IIntent, state: LoadDataState): LoadDataState {
        return when (intent) {
            is LoadDataIntent.RefreshByLoadData -> {
                state.copy(content = intent.result)
            }

            else -> state
        }
    }
}
package com.darcy.kmpdemo.ui.screen.loaddata.state

import com.darcy.kmpdemo.ui.base.IState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.paging.PagingState

data class LoadDataState(
    val content: String = "default",
    var screenState: ScreenState = ScreenState.Initial,
    val pagingState: PagingState = PagingState(),
) : IState {
}
package com.darcy.kmpdemo.ui.screen.phone.conversations.state

import com.darcy.kmpdemo.bean.http.response.ConversationResponse
import com.darcy.kmpdemo.ui.base.IState
import com.darcy.kmpdemo.ui.base.impl.paging.PagingState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsState

data class ConversationState(
    val items: List<ConversationResponse> = emptyList(),
    val screenState: ScreenState = ScreenState.Initial,
    val pagingState: PagingState = PagingState(),
    val tipsState: TipsState = TipsState(),
) : IState

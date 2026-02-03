package com.darcy.kmpdemo.ui.screen.phone.chatlist.state

import com.darcy.kmpdemo.bean.ui.ChatListItemBean
import com.darcy.kmpdemo.ui.base.IState
import com.darcy.kmpdemo.ui.base.impl.paging.PagingState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsState

data class ChatListState(
    val items: List<ChatListItemBean> = emptyList(),
    val screenState: ScreenState = ScreenState.Initial,
    val pagingState: PagingState = PagingState(),
    val tipsState: TipsState = TipsState(),
) : IState

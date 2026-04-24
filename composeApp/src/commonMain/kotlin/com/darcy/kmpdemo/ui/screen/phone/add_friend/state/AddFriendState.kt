package com.darcy.kmpdemo.ui.screen.phone.add_friend.state

import com.darcy.kmpdemo.ui.base.IState
import com.darcy.kmpdemo.ui.base.impl.paging.PagingState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsState

data class AddFriendState(
    val screenState: ScreenState = ScreenState.Initial,
    val tipsState: TipsState = TipsState(),
) : IState

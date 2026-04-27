package com.darcy.kmpdemo.ui.screen.phone.accept_friend.state

import com.darcy.kmpdemo.bean.http.response.ApplyFriendResponse
import com.darcy.kmpdemo.ui.base.IState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsState

data class AcceptFriendState(
    val screenState: ScreenState = ScreenState.Initial,
    val tipsState: TipsState = TipsState(),
    val applys: List<ApplyFriendResponse> = listOf(),
    val statusText: String = "同意"
) : IState

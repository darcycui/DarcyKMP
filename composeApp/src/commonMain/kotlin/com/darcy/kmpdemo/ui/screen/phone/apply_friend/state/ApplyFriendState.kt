package com.darcy.kmpdemo.ui.screen.phone.apply_friend.state

import com.darcy.kmpdemo.bean.http.response.SearchUserResponse
import com.darcy.kmpdemo.ui.base.IState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsState

data class ApplyFriendState(
    val screenState: ScreenState = ScreenState.Initial,
    val tipsState: TipsState = TipsState(),
    val userInfo: SearchUserResponse = SearchUserResponse(),
    val statusText: String = "加好友"
) : IState

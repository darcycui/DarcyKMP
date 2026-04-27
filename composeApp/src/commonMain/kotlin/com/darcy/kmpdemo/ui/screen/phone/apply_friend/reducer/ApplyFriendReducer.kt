package com.darcy.kmpdemo.ui.screen.phone.apply_friend.reducer

import com.darcy.kmpdemo.bean.http.response.SearchUserResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.apply_friend.intent.ApplyFriendIntent
import com.darcy.kmpdemo.ui.screen.phone.apply_friend.state.ApplyFriendState

class ApplyFriendReducer :
    ScreenStateFetchPagingTipsCombinedReducer<ApplyFriendState, SearchUserResponse>() {
    override fun onReduce(
        intent: IIntent,
        state: ApplyFriendState
    ): ApplyFriendState {
        return when (intent) {
            is ApplyFriendIntent.RefreshBySearchUser -> {
                state.copy(
                    userInfo = intent.response
                )
            }

            is ApplyFriendIntent.RefreshByApplyFriend -> {
                state.copy(
                    statusText = intent.response
                )
            }

            else -> {
                state
            }
        }
    }

    override fun onScreenState(
        state: ApplyFriendState,
        newScreenState: ScreenState
    ): ApplyFriendState {
        return state.copy(screenState = newScreenState)
    }

    override fun onFetch(
        state: ApplyFriendState,
        result: SearchUserResponse
    ): ApplyFriendState {
        TODO("Not yet implemented")
    }

    override fun onPaging(
        state: ApplyFriendState,
        pageNumber: Int,
        response: SearchUserResponse
    ): ApplyFriendState {
        TODO("Not yet implemented")
    }

    override fun onShowTips(
        state: ApplyFriendState,
        intent: TipsIntent.ShowTips
    ): ApplyFriendState {

        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = true,
                title = intent.title,
                tips = intent.tips,
                code = intent.code,
                middleButtonText = intent.middleButtonText,
                positiveButtonText = intent.positiveButtonText,
                negativeButtonText = intent.negativeButtonText,
            )
        )
    }

    override fun onDismissTips(state: ApplyFriendState): ApplyFriendState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
    }
}
package com.darcy.kmpdemo.ui.screen.phone.add_friend.reducer

import com.darcy.kmpdemo.bean.http.response.SearchUserResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.add_friend.intent.AddFriendIntent
import com.darcy.kmpdemo.ui.screen.phone.add_friend.state.AddFriendState

class AddFriendReducer :
    ScreenStateFetchPagingTipsCombinedReducer<AddFriendState, SearchUserResponse>() {
    override fun onReduce(
        intent: IIntent,
        state: AddFriendState
    ): AddFriendState {
        return when (intent) {
            is AddFriendIntent.RefreshBySearchUser -> {
                state.copy(
                    userInfo = intent.response
                )
            }

            is AddFriendIntent.RefreshByApplyFriend -> {
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
        state: AddFriendState,
        newScreenState: ScreenState
    ): AddFriendState {
        return state.copy(screenState = newScreenState)
    }

    override fun onFetch(
        state: AddFriendState,
        result: SearchUserResponse
    ): AddFriendState {
        TODO("Not yet implemented")
    }

    override fun onPaging(
        state: AddFriendState,
        pageNumber: Int,
        response: SearchUserResponse
    ): AddFriendState {
        TODO("Not yet implemented")
    }

    override fun onShowTips(
        state: AddFriendState,
        intent: TipsIntent.ShowTips
    ): AddFriendState {

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

    override fun onDismissTips(state: AddFriendState): AddFriendState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
    }
}
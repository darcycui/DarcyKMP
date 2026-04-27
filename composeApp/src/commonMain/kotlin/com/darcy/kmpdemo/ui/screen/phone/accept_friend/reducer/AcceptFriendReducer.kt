package com.darcy.kmpdemo.ui.screen.phone.accept_friend.reducer

import com.darcy.kmpdemo.bean.http.response.ApplyFriendResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.accept_friend.intent.AcceptFriendIntent
import com.darcy.kmpdemo.ui.screen.phone.accept_friend.state.AcceptFriendState

class AcceptFriendReducer :
    ScreenStateFetchPagingTipsCombinedReducer<AcceptFriendState, List<ApplyFriendResponse>>() {
    override fun onReduce(
        intent: IIntent,
        state: AcceptFriendState
    ): AcceptFriendState {
        return when (intent) {
            is AcceptFriendIntent.RefreshByAcceptFriend -> {
                state.copy(
                    applys = state.applys.map {
                        if (it.id == intent.response.id) {
                            intent.response
                        } else {
                            it
                        }
                    }
                )
            }
            else -> {
                state
            }
        }
    }

    override fun onScreenState(
        state: AcceptFriendState,
        newScreenState: ScreenState
    ): AcceptFriendState {
        return state.copy(screenState = newScreenState)
    }

    override fun onFetch(
        state: AcceptFriendState,
        result: List<ApplyFriendResponse>
    ): AcceptFriendState {
        return state.copy(
            applys = result
        )
    }

    override fun onPaging(
        state: AcceptFriendState,
        pageNumber: Int,
        response: List<ApplyFriendResponse>
    ): AcceptFriendState {
        TODO("Not yet implemented")
    }

    override fun onShowTips(
        state: AcceptFriendState,
        intent: TipsIntent.ShowTips
    ): AcceptFriendState {

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

    override fun onDismissTips(state: AcceptFriendState): AcceptFriendState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
    }
}
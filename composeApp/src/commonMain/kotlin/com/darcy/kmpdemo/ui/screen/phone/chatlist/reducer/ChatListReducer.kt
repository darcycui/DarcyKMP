package com.darcy.kmpdemo.ui.screen.phone.chatlist.reducer

import com.darcy.kmpdemo.bean.http.ChatListResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.chatlist.state.ChatListState

class ChatListReducer :
    ScreenStateFetchPagingTipsCombinedReducer<ChatListState, ChatListResponse>() {
    override fun onReduce(
        intent: IIntent,
        state: ChatListState
    ): ChatListState {
        return state
    }

    override fun onScreenState(
        state: ChatListState,
        newScreenState: ScreenState
    ): ChatListState {
        return state.copy(screenState = newScreenState)
    }

    override fun onFetch(
        state: ChatListState,
        result: ChatListResponse
    ): ChatListState {
        return state.copy(
            items = result.items
        )
    }

    override fun onPaging(
        state: ChatListState,
        pageNumber: Int,
        response: ChatListResponse
    ): ChatListState {
        return state.copy(
            items = state.items + response.items
        )
    }

    override fun onShowTips(
        state: ChatListState,
        intent: TipsIntent.ShowTips
    ): ChatListState {
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

    override fun onDismissTips(state: ChatListState): ChatListState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
    }
}
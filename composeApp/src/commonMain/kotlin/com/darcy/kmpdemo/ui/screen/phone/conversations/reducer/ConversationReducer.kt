package com.darcy.kmpdemo.ui.screen.phone.conversations.reducer

import com.darcy.kmpdemo.bean.http.response.ConversationResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.conversations.state.ConversationState

class ConversationReducer :
    ScreenStateFetchPagingTipsCombinedReducer<ConversationState, List<ConversationResponse>>() {
    override fun onReduce(
        intent: IIntent,
        state: ConversationState
    ): ConversationState {
        return state
    }

    override fun onScreenState(
        state: ConversationState,
        newScreenState: ScreenState
    ): ConversationState {
        return state.copy(screenState = newScreenState)
    }

    override fun onFetch(
        state: ConversationState,
        result: List<ConversationResponse>
    ): ConversationState {
        return state.copy(
            items = result
        )
    }

    override fun onPaging(
        state: ConversationState,
        pageNumber: Int,
        response: List<ConversationResponse>
    ): ConversationState {
        return state.copy(
            items = state.items + response
        )
    }

    override fun onShowTips(
        state: ConversationState,
        intent: TipsIntent.ShowTips
    ): ConversationState {
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

    override fun onDismissTips(state: ConversationState): ConversationState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
    }
}
package com.darcy.kmpdemo.ui.screen.phone.chat.privatechat.reducer

import com.darcy.kmpdemo.bean.http.response.PrivateMessageResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.chat.privatechat.state.ChatState

class ChatReducer : ScreenStateFetchPagingTipsCombinedReducer<ChatState, List<PrivateMessageResponse>>(){
    override fun onReduce(
        intent: IIntent,
        state: ChatState
    ): ChatState {
        TODO("Not yet implemented")
    }

    override fun onScreenState(
        state: ChatState,
        newScreenState: ScreenState
    ): ChatState {
        return state.copy(screenState = newScreenState)
    }

    override fun onFetch(
        state: ChatState,
        result: List<PrivateMessageResponse>
    ): ChatState {
        return state.copy(
            items = result
        )
    }

    override fun onPaging(
        state: ChatState,
        pageNumber: Int,
        response: List<PrivateMessageResponse>
    ): ChatState {
        return state.copy(
            items = state.items + response
        )
    }

    override fun onShowTips(
        state: ChatState,
        intent: TipsIntent.ShowTips
    ): ChatState {
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

    override fun onDismissTips(state: ChatState): ChatState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
    }
}
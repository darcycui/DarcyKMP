package com.darcy.kmpdemo.ui.screen.phone.mine.reducer

import com.darcy.kmpdemo.bean.http.MineResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.mine.intent.MineIntent
import com.darcy.kmpdemo.ui.screen.phone.mine.state.MineState

class MineReducer : ScreenStateFetchPagingTipsCombinedReducer<MineState, MineResponse>() {
    override fun onReduce(
        intent: IIntent,
        state: MineState
    ): MineState {
        return when (intent) {
            is MineIntent.RefreshAvatar -> {
                state.copy(bean = state.bean.copy(avatar = intent.avatarPath))
            }

            else -> state
        }
    }

    override fun onScreenState(
        state: MineState,
        newScreenState: ScreenState
    ): MineState {
        return state.copy(screenState = newScreenState)
    }

    override fun onFetch(
        state: MineState,
        result: MineResponse
    ): MineState {
        return state.copy(bean = result.bean)
    }

    override fun onPaging(
        state: MineState,
        pageNumber: Int,
        response: MineResponse
    ): MineState {
        return state
    }


    override fun onShowTips(
        state: MineState,
        intent: TipsIntent.ShowTips
    ): MineState {
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

    override fun onDismissTips(state: MineState): MineState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
    }
}
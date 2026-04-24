package com.darcy.kmpdemo.ui.screen.phone.register.reducer

import com.darcy.kmpdemo.bean.http.response.RegisterResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.register.state.RegisterState

class RegisterReducer :
    ScreenStateFetchPagingTipsCombinedReducer<RegisterState, RegisterResponse>() {
    override fun onReduce(
        intent: IIntent,
        state: RegisterState
    ): RegisterState {
        return state
    }

    override fun onScreenState(
        state: RegisterState,
        newScreenState: ScreenState
    ): RegisterState {
        return state.copy(screenState = newScreenState)
    }

    override fun onFetch(
        state: RegisterState,
        result: RegisterResponse
    ): RegisterState {
        TODO("Not yet implemented")
    }

    override fun onPaging(
        state: RegisterState,
        pageNumber: Int,
        response: RegisterResponse
    ): RegisterState {
        TODO("Not yet implemented")
    }

    override fun onShowTips(
        state: RegisterState,
        intent: TipsIntent.ShowTips
    ): RegisterState {

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

    override fun onDismissTips(state: RegisterState): RegisterState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
    }
}
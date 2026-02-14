package com.darcy.kmpdemo.ui.screen.phone.login.reducer

import com.darcy.kmpdemo.bean.http.UsersResponse
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.combined.ScreenStateFetchPagingTipsCombinedReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.login.state.LoginState

class LoginReducer :
    ScreenStateFetchPagingTipsCombinedReducer<LoginState, UsersResponse>() {
    override fun onReduce(
        intent: IIntent,
        state: LoginState
    ): LoginState {
        return state
    }

    override fun onScreenState(
        state: LoginState,
        newScreenState: ScreenState
    ): LoginState {
        return state.copy(screenState = newScreenState)
    }

    override fun onFetch(
        state: LoginState,
        result: UsersResponse
    ): LoginState {
        return state.copy(
            items = result.items
        )
    }

    override fun onPaging(
        state: LoginState,
        pageNumber: Int,
        response: UsersResponse
    ): LoginState {
        return state.copy(
            items = state.items + response.items
        )
    }

    override fun onShowTips(
        state: LoginState,
        intent: TipsIntent.ShowTips
    ): LoginState {
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

    override fun onDismissTips(state: LoginState): LoginState {
        return state.copy(
            tipsState = state.tipsState.copy(
                showTips = false
            )
        )
    }
}
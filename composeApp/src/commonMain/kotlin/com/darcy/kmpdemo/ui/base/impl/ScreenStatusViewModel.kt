package com.darcy.kmpdemo.ui.base.impl

import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer

abstract class ScreenStatusViewModel<S>: BaseViewModel<ScreenStatusState>() {
    override fun initState(): ScreenStatusState {
        return ScreenStatusState()
    }

    override fun initReducers(): List<IReducer<ScreenStatusState>> {
        return listOf(
            ScreenStatusReducer()
        )
    }

    override fun dispatch(intent: IIntent) {
        super.dispatch(intent)
    }
}
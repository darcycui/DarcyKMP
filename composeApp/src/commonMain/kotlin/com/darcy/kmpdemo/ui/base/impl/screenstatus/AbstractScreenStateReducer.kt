package com.darcy.kmpdemo.ui.base.impl.screenstatus

import com.darcy.kmpdemo.ui.base.IIntent

abstract class AbstractScreenStateReducer<S> : IScreenStateReducer<S> {
    override fun reduce(intent: IIntent, state: S): S {
        return when (intent) {
            is ScreenStateIntent.ScreenStateChange -> {
                onScreenState(state, intent.screenState)
            }

            else -> {
                onReduce(intent, state)
            }
        }
    }
}
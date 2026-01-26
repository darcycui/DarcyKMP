package com.darcy.kmpdemo.ui.base.impl.tips

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer

abstract class TipsReducer<S> : IReducer<S> {
    override fun reduce(intent: IIntent, state: S): S {
        return when (intent) {
            is TipsIntent.ShowTips -> {
                onShowTips(state, intent)
            }

            is TipsIntent.DismissTips -> {
                onDismissTips(state)
            }

            else -> {
                onReduce(intent, state)
            }
        }
    }

    abstract fun onShowTips(state: S, intent: TipsIntent.ShowTips): S

    abstract fun onDismissTips(state: S): S

    abstract fun onReduce(intent: IIntent, state: S): S
}
package com.darcy.kmpdemo.ui.base.impl.tips

import com.darcy.kmpdemo.ui.base.IIntent

abstract class AbstractTipsReducer<S> : ITipsReducer<S> {
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
}
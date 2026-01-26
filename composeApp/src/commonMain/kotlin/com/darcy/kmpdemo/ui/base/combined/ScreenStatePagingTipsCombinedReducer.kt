package com.darcy.kmpdemo.ui.base.combined

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.paging.PagingIntent
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenStateIntent
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent

abstract class ScreenStatePagingTipsCombinedReducer<S, R> : IReducer<S> {
    override fun reduce(intent: IIntent, state: S): S {
        return when (intent) {
            is ScreenStateIntent.ScreenStateChange -> {
                onScreenState(state, intent.screenState)
            }

            is PagingIntent.RefreshByLoadNewPage<*> -> {
                @Suppress("UNCHECKED_CAST")
                onPaging(state, intent.pageNumber, intent.response as R)
            }

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

    // 更新屏幕状态
    abstract fun onScreenState(state: S, newScreenState: ScreenState): S

    // 更新分页数据
    abstract fun onPaging(state: S, pageNumber: Int, response: R): S

    // 显示提示
    abstract fun onShowTips(state: S, intent: TipsIntent.ShowTips): S

    // 隐藏提示
    abstract fun onDismissTips(state: S): S

    // 处理其他意图
    abstract fun onReduce(intent: IIntent, state: S): S
}
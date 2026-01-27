package com.darcy.kmpdemo.ui.base.combined

import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.base.impl.fetch.IFetchReducer
import com.darcy.kmpdemo.ui.base.impl.paging.IPagingReducer
import com.darcy.kmpdemo.ui.base.impl.paging.PagingIntent
import com.darcy.kmpdemo.ui.base.impl.screenstatus.IScreenStateReducer
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenStateIntent
import com.darcy.kmpdemo.ui.base.impl.tips.ITipsReducer
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
/**
 * Created by darcy
 * 2026/01/27
 *
 * 屏幕状态、数据加载、分页、提示的组合 reducer
 */
abstract class ScreenStateFetchPagingTipsCombinedReducer<S, R> : IScreenStateReducer<S>,
    IFetchReducer<S, R>,
    IPagingReducer<S, R>,
    ITipsReducer<S> {
    override fun reduce(intent: IIntent, state: S): S {
        return when (intent) {
            is ScreenStateIntent.ScreenStateChange -> {
                onScreenState(state, intent.screenState)
            }

            is FetchIntent.RefreshByFetchData<*> -> {
                @Suppress("UNCHECKED_CAST")
                onFetch(state, intent.result as R)
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
}
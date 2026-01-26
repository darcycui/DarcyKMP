package com.darcy.kmpdemo.ui.base.impl.screenstatus

import com.darcy.kmpdemo.ui.base.IState

sealed class ScreenState : IState {
    data object Initial : ScreenState()
    data object Loading : ScreenState()
    data object Empty : ScreenState()
    data class Error(val errorCode: Int, val errorMessage: String) : ScreenState()
    data object Success : ScreenState()
    data object NoData : ScreenState()
    data object Refresh : ScreenState()
    data object LoadMore : ScreenState()
    data object NoMoreData : ScreenState()
}
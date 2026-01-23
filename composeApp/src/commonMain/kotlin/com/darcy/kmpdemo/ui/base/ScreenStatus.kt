package com.darcy.kmpdemo.ui.base

sealed class ScreenStatus : IState {
    data object Initial : ScreenStatus()
    data object Loading : ScreenStatus()
    data object Empty : ScreenStatus()
    data class Error(val errorCode: Int, val errorMessage: String) : ScreenStatus()
    data object Success : ScreenStatus()
    data object NoData : ScreenStatus()
    data object Refresh : ScreenStatus()
    data object LoadMore : ScreenStatus()
    data object NoMoreData : ScreenStatus()
}
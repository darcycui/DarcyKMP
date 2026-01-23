package com.darcy.kmpdemo.ui.base

/**
 * 状态 reducer
 * 纯函数 只用于修改状态
 */
interface IReducer<S> {
    fun reduce(intent: IIntent, state: S): S
}
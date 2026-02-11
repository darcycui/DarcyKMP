package com.darcy.kmpdemo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darcy.kmpdemo.log.logD
import com.darcy.kmpdemo.log.logI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 基类 ViewModel
 * 封装了 ViewModel 的基本功能, 包括状态更新、意图分发、异步操作等功能
 * 本项目中使用的 ViewModel 推荐继承自该类
 * 状态 [IState]: 对应持续性的 UI 页面文本、列表等
 * 事件 [IEvent]: 对应一次性 UI，例如导航、显示 Toast 等
 */
abstract class BaseViewModel<S : IState> : ViewModel() {
    /**
     * 可变 Flow:用于在 ViewModel 内部改变状态
     */
    protected val _uiState: MutableStateFlow<S> = MutableStateFlow(initState())

    /**
     * 不可变 Flow:用于在 UI 层收集状态
     */
    val uiState: StateFlow<S>
        get() = _uiState.asStateFlow()

    /**
     * 可变 Flow:用于在 ViewModel 层发送事件
     */
    private val _event = MutableSharedFlow<IEvent>()

    /**
     * 不可变 Flow:用于在 UI 层收集事件
     */
    val event: SharedFlow<IEvent>
        get() = _event

    /**
     * 发送事件
     */
    protected suspend fun sendEvent(event: IEvent) {
        _event.emit(event)
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        logD("BaseViewModel 发生异常：${throwable.message}")
        throwable.printStackTrace()
    }
    /**
     * 协程作用域 用于开启协程
     */
    private val scope = CoroutineScope(
        viewModelScope.coroutineContext + SupervisorJob() + exceptionHandler
    )

    /**
     * 状态 reducers 用于改变状态
     */
    protected val reducers: List<IReducer<S>> by lazy {
        initReducers()
    }

    /**
     * 开启协程 执行 io线程 操作
     */
    protected fun io(block: suspend () -> Unit) {
        scope.launch(Dispatchers.Default) {
            block()
        }
    }

    /**
     * 开启协程 执行 主线程 操作
     */
    protected fun main(block: suspend () -> Unit) {
        scope.launch(Dispatchers.Main) {
            block()
        }
    }

    /**
     * 更新状态 这将触发 Composable重组 (UI刷新)
     */
    protected fun updateState(intent: IIntent) {
//        logD("更新前：state=${_uiState.value}")
        val newState = reducers.fold(_uiState.value) { currentState, reducer ->
            reducer.reduce(intent, currentState)
        }
        _uiState.update {
            newState
        }
//        logI("更新后：state=${_uiState.value}")
    }

    init {
        logD("初始化ViewModel $this：state=${_uiState.value}")
        init()
    }

    /**
     * 初始状态
     */
    protected abstract fun initState(): S

    /**
     * 初始化 reducers
     */
    protected abstract fun initReducers(): List<IReducer<S>>

    /**
     * 初始化 ViewModel 的动作，例如初始化数据、设置默认值等 (可选)
     */
    open fun init() {
    }

    /**
     * 分发意图 Intent
     */
    open fun dispatch(intent: IIntent) {
        updateState(intent)
    }
}
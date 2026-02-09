package com.darcy.kmpdemo.ui.screen.phone.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.darcy.kmpdemo.exception.BaseException
import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.base.impl.paging.PagingIntent
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenStateIntent
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.screen.phone.chatlist.reducer.ChatListReducer
import com.darcy.kmpdemo.ui.screen.phone.chatlist.state.ChatListState
import com.darcy.kmpdemo.ui.screen.phone.chatlist.usecase.FetchChatListUseCase
import kotlin.reflect.KClass

class ChatListViewModel(
    private val fetchChatListUseCase: FetchChatListUseCase = FetchChatListUseCase(),
) : BaseViewModel<ChatListState>() {
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return ChatListViewModel() as T
            }
        }
    }

    override fun initState(): ChatListState {
        return ChatListState()
    }

    override fun initReducers(): List<IReducer<ChatListState>> {
        return listOf(ChatListReducer())
    }

    override fun dispatch(intent: IIntent) {
        when (intent) {
            is FetchIntent.ActionLoadData -> {
                // 获取数据
                actionFetchChatList()
            }

            is PagingIntent.ActionLoadNewPage -> {
                // 分页
            }

            else -> {
                super.dispatch(intent)
            }
        }
    }

    private fun actionFetchChatList() {
        io {
            dispatch(ScreenStateIntent.ScreenStateChange(ScreenState.Loading))
            val response = fetchChatListUseCase()
//            dispatchFailure(BaseException(404, "加载错误"))
            response.onSuccess {
                dispatch(ScreenStateIntent.ScreenStateChange(ScreenState.Success))
                dispatch(FetchIntent.RefreshByFetchData(it))
//                dispatch(TipsIntent.ShowTips(
//                    title = "提示",
//                    tips = "加载成功",
//                    code = 200,
//                    middleButtonText = "确定",
//                ))
            }.onFailure {
                dispatchFailure(it)
            }
        }
    }

    private fun dispatchFailure(throwable: Throwable) {
        val code = if (throwable is BaseException) throwable.errorCode else -1
        val message = throwable.message ?: "Unknown Error"
        dispatch(ScreenStateIntent.ScreenStateChange(ScreenState.Error(code, message)))
    }
}
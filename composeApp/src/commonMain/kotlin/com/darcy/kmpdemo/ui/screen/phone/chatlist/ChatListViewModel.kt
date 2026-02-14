package com.darcy.kmpdemo.ui.screen.phone.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.darcy.kmpdemo.bean.http.ChatListResponse
import com.darcy.kmpdemo.bean.ui.ChatListItemBean
import com.darcy.kmpdemo.exception.BaseException
import com.darcy.kmpdemo.repository.ConversationDaoRepository
import com.darcy.kmpdemo.repository.ConversationUserCrossRefDaoRepository
import com.darcy.kmpdemo.storage.database.tables.ConversationEntity
import com.darcy.kmpdemo.storage.database.tables.ConversationUserCrossRef
import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.base.impl.paging.PagingIntent
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenStateIntent
import com.darcy.kmpdemo.ui.screen.phone.chatlist.intent.ChatListIntent
import com.darcy.kmpdemo.ui.screen.phone.chatlist.reducer.ChatListReducer
import com.darcy.kmpdemo.ui.screen.phone.chatlist.state.ChatListState
import com.darcy.kmpdemo.ui.screen.phone.chatlist.usecase.FetchChatListUseCase
import kotlin.reflect.KClass

class ChatListViewModel(
    private val fetchChatListUseCase: FetchChatListUseCase = FetchChatListUseCase(),
    private val conversationDaoRepository: ConversationDaoRepository = ConversationDaoRepository(),
    private val conversationUserCrossRefDaoRepository: ConversationUserCrossRefDaoRepository = ConversationUserCrossRefDaoRepository(),
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
            is FetchIntent.ActionLoadData -> { // 获取数据
                actionFetchChatList()
            }

            is ChatListIntent.ActionCreateConversation -> { // 创建会话
                actionCreateConversation(intent.userIdFrom, intent.userIdTo, intent.conversation)
            }

            is ChatListIntent.ActionDeleteConversation -> { // 删除会话
                actionDeleteConversation(intent.conversationId)
            }

            is ChatListIntent.ActionUpdateConversation -> { // 更新会话
                actionUpdateConversation(intent.conversationId, intent.conversation)
            }

            is ChatListIntent.ActionQueryUsersByConversationId -> { // 查询会话中的用户
                actionQueryUsersByConversationId(intent.conversationId)
            }

            is ChatListIntent.ActionQueryConversationsByUserId -> { // 查询用户会话
                actionQueryConversationsByUserId(intent.userId)
            }

            is PagingIntent.ActionLoadNewPage -> {
                // 分页
            }

            else -> {
                super.dispatch(intent)
            }
        }
    }

    private fun actionQueryConversationsByUserId(userId: Long) {
        io {
            val crossRef =
                conversationUserCrossRefDaoRepository.getConversationsByUserId(userId)
            val uiBeanList = crossRef.conversations.map { item ->
                ChatListItemBean(
                    id = item.conversationId ?: -1L,
                    title = item.name,
                    subTitle = "",
                    avatar = item.avatar,
                )
            }
            dispatch(FetchIntent.RefreshByFetchData(ChatListResponse(uiBeanList)))
        }
    }

    private fun actionQueryUsersByConversationId(conversationId: Long) {
        io {
            val crossRef =
                conversationUserCrossRefDaoRepository.getUsersByConversationId(conversationId)
//            val uiBeanList = crossRef.users.map {
//                ChatDetailItemBean(
//                )
//            }
            //dispatch(ChatListIntent.RefreshByQueryData(users))
        }
    }

    private fun actionUpdateConversation(
        conversationId: Long,
        conversation: ConversationEntity
    ) {
        io {
            conversationDaoRepository.updateConversation(conversation)
        }
    }

    private fun actionDeleteConversation(conversationId: Long) {
        io {
            conversationDaoRepository.deleteConversationById(conversationId)
        }
    }

    private fun actionCreateConversation(
        userIdFrom: Long,
        userIdTo: Long,
        conversation: ConversationEntity
    ) {
        io {
            conversationDaoRepository.createConversation(conversation)
            val conversationName = "会话$userIdFrom-$userIdTo"
            val conversationId =
                conversationDaoRepository.getConversationByName(conversationName).conversationId ?: -1L
            conversationUserCrossRefDaoRepository.insert(
                ConversationUserCrossRef(
                    conversationId = conversationId,
                    userId = userIdFrom
                )
            )
            conversationUserCrossRefDaoRepository.insert(
                ConversationUserCrossRef(
                    conversationId = conversationId,
                    userId = userIdTo
                )
            )
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
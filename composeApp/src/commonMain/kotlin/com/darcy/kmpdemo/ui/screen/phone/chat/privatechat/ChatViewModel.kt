package com.darcy.kmpdemo.ui.screen.phone.chat.privatechat

import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.screen.phone.chat.privatechat.intent.ChatIntent
import com.darcy.kmpdemo.ui.screen.phone.chat.privatechat.reducer.ChatReducer
import com.darcy.kmpdemo.ui.screen.phone.chat.privatechat.repository.ChatRepository
import com.darcy.kmpdemo.ui.screen.phone.chat.privatechat.state.ChatState

class ChatViewModel(
    private val chatRepository: ChatRepository = ChatRepository(),
) : BaseViewModel<ChatState>() {
    override fun initState(): ChatState {
        return ChatState()
    }

    override fun initReducers(): List<IReducer<ChatState>> {
        return listOf(
            ChatReducer(),
        )
    }

    override fun dispatch(intent: IIntent) {
        when (intent) {
            is FetchIntent.ActionFetchData -> {
                actionFetchMessages()
            }

            is ChatIntent.ActionSendMessage -> {
                actionSendMessage()
            }

            else -> {
                super.dispatch(intent)
            }
        }
    }

    private fun actionFetchMessages() {
        // todo: http拉取最新消息
//        chatRepository.fetchMessages(
//            conversationId = ,
//            page = 0,
//            size = 10,
//            onSuccessList = {
//
//            },
//            onError = {
//
//            }
//        )
    }

    private fun actionSendMessage() {
        // todo: websocket发送消息
        chatRepository
    }
}
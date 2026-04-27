package com.darcy.kmpdemo.ui.screen.phone.conversations.intent

import com.darcy.kmpdemo.bean.http.response.ConversationResponse
import com.darcy.kmpdemo.storage.database.tables.ConversationEntity
import com.darcy.kmpdemo.ui.base.IIntent

sealed class ConversationIntent : IIntent {
    data class ActionCreateConversation(
        val userIdFrom: Long,
        val userIdTo: Long,
        val conversation: ConversationEntity
    ) : ConversationIntent()

    data class ActionDeleteConversation(
        val conversationId: Long,
    ) : ConversationIntent()

    data class ActionUpdateConversation(
        val conversationId: Long,
        val conversation: ConversationEntity
    ) : ConversationIntent()

    data class ActionQueryUsersByConversationId(
        val conversationId: Long
    ) : ConversationIntent()

    data class ActionQueryConversationsByUserId(
        val userId: Long
    ) : ConversationIntent()

    data class GoChatPage(
        val response: ConversationResponse
    ) : ConversationIntent()
}
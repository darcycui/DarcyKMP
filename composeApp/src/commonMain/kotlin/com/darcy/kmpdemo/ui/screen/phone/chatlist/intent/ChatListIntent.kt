package com.darcy.kmpdemo.ui.screen.phone.chatlist.intent

import com.darcy.kmpdemo.storage.database.tables.ConversationEntity
import com.darcy.kmpdemo.storage.database.tables.UserEntity
import com.darcy.kmpdemo.ui.base.IIntent

sealed class ChatListIntent : IIntent {
    data class ActionCreateConversation(
        val userIdFrom: Long,
        val userIdTo: Long,
        val conversation: ConversationEntity
    ) : ChatListIntent()

    data class ActionDeleteConversation(
        val conversationId: Long,
    ) : ChatListIntent()

    data class ActionUpdateConversation(
        val conversationId: Long,
        val conversation: ConversationEntity
    ) : ChatListIntent()

    data class ActionQueryUsersByConversationId(
        val conversationId: Long
    ) : ChatListIntent()

    data class ActionQueryConversationsByUserId(
        val userId: Long
    ) : ChatListIntent()
}
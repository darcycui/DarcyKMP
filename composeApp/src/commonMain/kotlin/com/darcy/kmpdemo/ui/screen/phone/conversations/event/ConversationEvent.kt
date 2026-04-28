package com.darcy.kmpdemo.ui.screen.phone.conversations.event

import com.darcy.kmpdemo.ui.base.IEvent

sealed class ConversationEvent : IEvent {
    data class GoChatPage(
        val conversationId: Long,
        val userId: Long,
        val userName: String,
        val userAvatar: String
    ) : ConversationEvent()
}
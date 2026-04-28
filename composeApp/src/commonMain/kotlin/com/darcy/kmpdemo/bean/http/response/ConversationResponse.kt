package com.darcy.kmpdemo.bean.http.response

import kotlinx.serialization.Serializable

@Serializable
data class ConversationResponse(
    val conversationId: String = "",
    val user: UserResponse = UserResponse(),
    val conversationType: Int = 0,
    val target: UserResponse = UserResponse(),
    val lastMsgId: String = "",
    val lastMsgContent: String = "",
    val lastMsgType: Int = 0,
    val lastMsgSenderId: Long = 0L,
    val lastMsgTime: String = "",
    val unreadCount: Int = 0,
    val isMuted: Boolean = false,
    val isPinned: Boolean = false,
    val isTop: Boolean = false,
    val draft: String = "",
    val draftTime: String = "",
    val extData: Map<String, String> = emptyMap(),
    val id: Long = 0,
    val createdAt: String = "",
    val updatedAt: String = ""
)
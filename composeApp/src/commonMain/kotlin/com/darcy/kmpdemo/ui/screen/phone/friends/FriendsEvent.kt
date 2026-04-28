package com.darcy.kmpdemo.ui.screen.phone.friends

import com.darcy.kmpdemo.ui.base.IEvent

sealed class FriendsEvent : IEvent {
    data object GoAddFriend : FriendsEvent()
    data object GoAcceptFriend : FriendsEvent()
    data class GoChat(
        val conversationId: Long,
        val userId: Long,
        val userName: String,
        val userAvatar: String
    ) : FriendsEvent()
}